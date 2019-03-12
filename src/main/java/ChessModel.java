import java.awt.*;

public class ChessModel {

    public static final int X_BOARD_SIZE = 8;
    public static final int Y_BOARD_SIZE = 8;

    private ChessView theView;

    private ChessFigures[][] figuresTable = new ChessFigures[X_BOARD_SIZE][Y_BOARD_SIZE];
    private ChessFigures[] blackRowFigures = new ChessFigures[X_BOARD_SIZE];
    private ChessFigures[] whiteRowFigures = new ChessFigures[X_BOARD_SIZE];

    private boolean whitePlayer = true;
    private boolean blackPlayer = false;

    private Point currentField = new Point(-1,-1);
    private Point beforeField = new Point(-1,-1);

    public ChessModel(ChessView theView) {

        this.theView = theView;
        figuresPositionStart();
        theView.displayFigures(figuresTable);
    }

    public ChessFigures getFiguresTable(int x, int y){

        ChessFigures value = figuresTable[x][y];
        return value;
    }

    private void figuresPositionStart(){

        blackRowFigures[0] = ChessFigures.BLACK_TOWER;
        blackRowFigures[1] = ChessFigures.BLACK_HORSE;
        blackRowFigures[2] = ChessFigures.BLACK_RUNNER;
        blackRowFigures[3] = ChessFigures.BLACK_QUEEN;
        blackRowFigures[4] = ChessFigures.BLACK_KING;
        blackRowFigures[5] = ChessFigures.BLACK_RUNNER;
        blackRowFigures[6] = ChessFigures.BLACK_HORSE;
        blackRowFigures[7] = ChessFigures.BLACK_TOWER;

        whiteRowFigures[0] = ChessFigures.WHITE_TOWER;
        whiteRowFigures[1] = ChessFigures.WHITE_HORSE;
        whiteRowFigures[2] = ChessFigures.WHITE_RUNNER;
        whiteRowFigures[3] = ChessFigures.WHITE_QUEEN;
        whiteRowFigures[4] = ChessFigures.WHITE_KING;
        whiteRowFigures[5] = ChessFigures.WHITE_RUNNER;
        whiteRowFigures[6] = ChessFigures.WHITE_HORSE;
        whiteRowFigures[7] = ChessFigures.WHITE_TOWER;

        /*
        W tablicy figuresTable[Y][X] najpierw wiersz oś Y, później kolumna oś X !!!
         */
        for(int i = 0; i < Y_BOARD_SIZE; i++){
            for(int j = 0; j < X_BOARD_SIZE; j++){

                if ((i < 2) || (i > 5)) {

                    switch(i){
                        case 0:
                            figuresTable[j][i] = blackRowFigures[j];
                            break;
                        case 1:
                            figuresTable[j][i] = ChessFigures.BLACK_PAWN;
                            break;
                        case 6:
                            figuresTable[j][i] = ChessFigures.WHITE_PAWN;
                            break;
                        case 7:
                            figuresTable[j][i] = whiteRowFigures[j];
                            break;
                    }
                }
                else{
                    figuresTable[j][i] = ChessFigures.EMPTY;
                }
            }
        }
    }

    private void nextPlayer(){

        if(whitePlayer){
            whitePlayer = false;
            blackPlayer = true;
            System.out.println("White Player");
        }
        else{
            whitePlayer = true;
            blackPlayer = false;
            System.out.println("Black Player");
        }
    }

    public Point getCurrentField(){
        return currentField;
    }

    public void setCurrentField(Point currentField){
        this.currentField.x = currentField.x / 100;
        this.currentField.y = currentField.y / 100;
    }

    public Point getBeforeField(){
        return beforeField;
    }

    public void setBeforeField(){
        this.beforeField = new Point(currentField.x, currentField.y);

    }
}
