import javax.swing.*;
import java.awt.*;

public class Board extends JLabel {

    private static final long serialVersionUID = 1L;


    public Board() {
        Dimension panelDimension = new Dimension(800, 800);
        setSize(panelDimension);
        setMinimumSize(panelDimension);
        setMaximumSize(panelDimension);
        setPreferredSize(panelDimension);
    }

    @Override
    public void paint(Graphics g) {
        Image img = createImage(getSize().width, getSize().height);

        Graphics2D g2 = (Graphics2D) img.getGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.white);
        g2.fillRect(0, 0, 800, 800);

        for (int i = 0; i < ChessModel.Y_BOARD_SIZE; i++) {
            for (int j = 0; j < ChessModel.X_BOARD_SIZE; j++) {
                if(((i % 2 == 1) && (j % 2 == 0)) || ((i % 2 == 0) && (j % 2 == 1))) {
                    g2.setColor(new Color(0, 190, 0));
                    g2.fillRect(100 * j, 100 * i, 100, 100);
                }
            }
        }
        g.drawImage(img, 0, 0, this);

    }

/*
    @Override
    public void update(Graphics g) {
        paint(g);
    }*/

}
