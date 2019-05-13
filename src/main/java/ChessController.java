import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.abs;

public class ChessController {

    private ChessModel theModel;
    private ChessView theView;
    private MarkFigure theMarkFigure;
    private ChessFigures currentClickField;
    private ChessFigures focusFigure;

    private Point mouseClickPosition = new Point(-100,-100);

    public ChessController(ChessView theView, ChessModel theModel, MarkFigure theMarkFigure){
        this.theView = theView;
        this.theModel = theModel;
        this.theMarkFigure = theMarkFigure;

        this.theMarkFigure.addCalculateListener(new CalculateListener());
    }

    private class CalculateListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1){
                mouseClickPosition.x = e.getX();
                mouseClickPosition.y = e.getY();
                gameEvent();
            }
        }
    }

    private void gameEvent(){

        currentClickField = currentClickField(mouseClickPosition);
        gameLogic(mouseClickPosition);
    }

    private boolean correctPlayer(Point clickPosition) {

        boolean state = false;
        System.out.println("Player: " + theModel.getPlayer().toString());

        ChessFigures figure = theModel.getFiguresTable((clickPosition.x / 100),(clickPosition.y / 100));
        String color = figure.toString().substring(0,5);

        System.out.println("Color: " + color);
        if (color.equals(theModel.getPlayer().toString())) {
            state = true;
        }

        return state;
    }

    private ChessFigures currentClickField(Point clickPosition){

        int a = clickPosition.x / 100;
        int b = clickPosition.y / 100;

        theModel.setBeforeClickField();
        theModel.setCurrentClickField(new Point(a,b));

        return theModel.getFiguresTable(a,b);
    }

    private void gameLogic(Point clickPosition){

        if(theMarkFigure.getFocusFigure()) {
            if (thisSameField()) {
                theMarkFigure.setFocusFigure(false);
                theMarkFigure.repaint();
                System.out.println("This same field.");
            } else {
                if (currentClickField != ChessFigures.EMPTY) {
                    theMarkFigure.setFocusFigure(false);
                    theMarkFigure.repaint();
                    if(checkCorrectnessMove()) {
                        theModel.moveFiguresTable(theModel.getBeforeClickField().x, theModel.getBeforeClickField().y, theModel.getCurrentClickField().x, theModel.getCurrentClickField().y);
                        theView.deleteFigure(theModel.getCurrentClickField().x, theModel.getCurrentClickField().y);
                        theView.changeFigureLocation(theModel.getBeforeClickField().x, theModel.getBeforeClickField().y, theModel.getCurrentClickField().x, theModel.getCurrentClickField().y);
                        System.out.println("Move figure :) Next player");
                    } else {
                        theModel.setCurrentClickField(new Point(-1,-1));
                        System.out.println("Bad move!");
                    }
                } else{
                    theMarkFigure.setFocusFigure(false);
                    theMarkFigure.repaint();
                    if(checkCorrectnessMove()) {
                        theModel.moveFiguresTable(theModel.getBeforeClickField().x, theModel.getBeforeClickField().y, theModel.getCurrentClickField().x, theModel.getCurrentClickField().y);
                        theView.changeFigureLocation(theModel.getBeforeClickField().x, theModel.getBeforeClickField().y, theModel.getCurrentClickField().x, theModel.getCurrentClickField().y);
                        System.out.println("Move figure :) Next player");
                    } else {
                        theModel.setCurrentClickField(new Point(-1,-1));
                        System.out.println("Bad move!");
                    }
                }
            }
            focusFigure = ChessFigures.EMPTY;
        }
        else{
            if (currentClickField != ChessFigures.EMPTY) {
                theMarkFigure.calculateAndSetRectanglePosition(clickPosition);
                theMarkFigure.setFocusFigure(true);
                focusFigure = currentClickField;
                theMarkFigure.repaint();
                System.out.println("Repaint");
            } else {
                System.out.println("Click to field with figure !");
            }
        }
    }

    private boolean thisSameField(){

        if ((theModel.getCurrentClickField().x == theModel.getBeforeClickField().x) && (theModel.getCurrentClickField().y == theModel.getBeforeClickField().y)) {
            return true;
        } else{
            return false;
        }
    }

    private ChessFigures onlyFigure(ChessFigures figure){

        ChessFigures figureWithoutColor = null;
        if((figure == ChessFigures.WHITE_PAWN) || (figure == ChessFigures.BLACK_PAWN)){
            figureWithoutColor = ChessFigures.PAWN;
        }
        if((figure == ChessFigures.WHITE_TOWER) || (figure == ChessFigures.BLACK_TOWER)){
            figureWithoutColor = ChessFigures.TOWER;
        }
        if((figure == ChessFigures.WHITE_HORSE) || (figure == ChessFigures.BLACK_HORSE)){
            figureWithoutColor = ChessFigures.HORSE;
        }
        if((figure == ChessFigures.WHITE_RUNNER) || (figure == ChessFigures.BLACK_RUNNER)){
            figureWithoutColor = ChessFigures.RUNNER;
        }
        if((figure == ChessFigures.WHITE_QUEEN) || (figure == ChessFigures.BLACK_QUEEN)){
            figureWithoutColor = ChessFigures.QUEEN;
        }
        if((figure == ChessFigures.WHITE_KING) || (figure == ChessFigures.BLACK_KING)){
            figureWithoutColor = ChessFigures.KING;
        }
        return figureWithoutColor;
    }

    private boolean checkCorrectnessMove(){

        boolean state = false;

        String color = focusFigure.toString().substring(0,5);

        switch (onlyFigure(focusFigure)){

            case PAWN:
                if (currentClickField == ChessFigures.EMPTY) {
                    if (color.equals("WHITE")) {
                        if (((theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) == 1) && (theModel.getBeforeClickField().x == theModel.getCurrentClickField().x)) {
                            state = true;
                        }
                    }
                    if (color.equals("BLACK")) {
                        if (((theModel.getCurrentClickField().y) - theModel.getBeforeClickField().y == 1) && (theModel.getBeforeClickField().x == theModel.getCurrentClickField().x)) {
                            state = true;
                        }
                    }
                } else {
                    if (color.equals("WHITE")) {
                        if (((theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) == 1) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) == 1)) {
                            state = true;
                        }
                    }
                    if (color.equals("BLACK")) {
                        if (((theModel.getCurrentClickField().y) - theModel.getBeforeClickField().y == 1) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) == 1)) {
                            state = true;
                        }
                    }
                }
                break;

            case TOWER:
                if(((abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) >= 1) && (theModel.getBeforeClickField().x == theModel.getCurrentClickField().x)) ||
                        ((theModel.getBeforeClickField().y == theModel.getCurrentClickField().y) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) >= 1))) {
                    state = true;
                }
                break;

            case HORSE:
                if(((abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) == 2) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) == 1)) ||
                        ((abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) == 1) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) == 2))) {
                    state = true;
                }
                break;

            case RUNNER:
                if((abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y)) == (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x))) {
                    state = true;
                }
                break;

            case QUEEN:
                if(((abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) >= 1) && (theModel.getBeforeClickField().x == theModel.getCurrentClickField().x)) ||
                        ((theModel.getBeforeClickField().y == theModel.getCurrentClickField().y) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) >= 1)) ||
                        (abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y)) == (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x))) {
                    state = true;
                }
                break;

            case KING:
                if(((abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) == 1) && (theModel.getBeforeClickField().x == theModel.getCurrentClickField().x)) ||
                        ((theModel.getBeforeClickField().y == theModel.getCurrentClickField().y) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) == 1)) ||
                        ((abs(theModel.getBeforeClickField().y - theModel.getCurrentClickField().y) == 1) && (abs(theModel.getBeforeClickField().x - theModel.getCurrentClickField().x) == 1))) {
                    state = true;
                }
                break;
        }
        return state;
    }
}
