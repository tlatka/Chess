import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessController {

    private ChessModel theModel;
    private ChessView theView;
    private MarkFigure theMarkFigure;

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

        checkFieldBoard(mouseClickPosition);
    }

    private void checkFieldBoard (Point clickPosition){

        int a = 0, b = 0;
        a = clickPosition.x / 100;
        b = clickPosition.y / 100;

        if(theModel.getFiguresTable(a,b) != ChessFigures.EMPTY){
            theModel.setCurrentField(clickPosition);
            if((theModel.getCurrentField().x != theModel.getBeforeField().x) || (theModel.getCurrentField().y != theModel.getBeforeField().y)){
                theMarkFigure.calculateAndSetRectanglePosition(clickPosition);
                theMarkFigure.setFocusFigure(true);
                theMarkFigure.repaint();
                theModel.setBeforeField();
                System.out.println("Repaint");
            }
            else{
                theMarkFigure.toggleFocusFigure();
                theMarkFigure.repaint();
                System.out.println("To samo pole");
            }
        }
        else{
            System.out.println("Puste pole.");
        }
    }

}
