import javax.swing.*;
import java.awt.*;

public class ChessView extends JFrame {

    public final static int NUMBER_FIGURES = 32;

    private static final long serialVersionUID = 1L;

    private Board theBoard;
    private MarkFigure theMarkFigure;

    private JLayeredPane jLayeredPane = null;
    private JLabel[] figuresList = new JLabel[NUMBER_FIGURES];

    public ChessView(Board theBoard, MarkFigure theMarkFigure){

        this.theBoard = theBoard;
        this.theMarkFigure = theMarkFigure;
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(805, 825);
        this.setResizable(false);
        this.setTitle("Chess on MVC");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width/2-400),20);
    }

    private JLayeredPane getJLayeredPane(JLabel[] figures){
        if (jLayeredPane == null){
            jLayeredPane = new JLayeredPane();
            jLayeredPane.add(theBoard,new Integer(4));
            jLayeredPane.add(theMarkFigure,new Integer(5));
        }
        for(int i=0; i<NUMBER_FIGURES; i++) {
            if(figures[i] == null){
                break;
            }
            jLayeredPane.add(figures[i], new Integer(6));
        }
        return jLayeredPane;
    }

    public void displayFigures(ChessFigures[][] figuresTable){

        JLabel downloadPicture;
        int k = 0;
        for(int i = 0; i < ChessModel.Y_BOARD_SIZE; i++){
            for(int j = 0; j < ChessModel.X_BOARD_SIZE; j++) {
                if(figuresTable[j][i] != ChessFigures.EMPTY) {
                    downloadPicture = new DrawFigures().drawIcon(figuresTable[j][i]);
                    downloadPicture.setBounds((j * 100), i * 100, 100, 100);
                    figuresList[k] = downloadPicture;
                    k++;
                }
            }
        }
        setLayeredPane(getJLayeredPane(figuresList));
    }
}
