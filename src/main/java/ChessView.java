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

    public void changeFigureLocation(int x1, int y1, int x2, int y2){

        int x,y;
        x = x1 * 100;
        y = y1 * 100;

        for(int i=0; i < (jLayeredPane.getComponentCount() - 2); i++){
//            System.out.println(jLayeredPane.getComponent(i).getX());
//            System.out.println(jLayeredPane.getComponent(i).getY());
            if((x==jLayeredPane.getComponent(i).getX()) && (y==jLayeredPane.getComponent(i).getY())){
                jLayeredPane.getComponent(i).setLocation(x2*100,y2*100);
                break;
            }
        }
    }

    public void deleteFigure(int x2, int y2) {

        int x,y;
        x = x2 * 100;
        y = y2 * 100;

        for(int i=0; i < (jLayeredPane.getComponentCount() - 2); i++){
//            System.out.println(jLayeredPane.getComponent(i).getX());
//            System.out.println(jLayeredPane.getComponent(i).getY());
            if((x==jLayeredPane.getComponent(i).getX()) && (y==jLayeredPane.getComponent(i).getY())){
                jLayeredPane.remove(i);
                break;
            }
        }
    }
}
