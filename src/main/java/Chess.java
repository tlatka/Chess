
public class Chess {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Board theBoard = new Board();
        MarkFigure theMarkFigure = new MarkFigure();
        ChessView theView = new ChessView(theBoard, theMarkFigure);
        ChessModel theModel = new ChessModel(theView);
        ChessController theController = new ChessController(theView, theModel, theMarkFigure);
        theView.setVisible(true);

    }
}
