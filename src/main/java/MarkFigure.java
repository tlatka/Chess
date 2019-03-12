import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


public class MarkFigure extends JLabel {

    private int x = 0, y = 0;
    private Point rectanglePosition = new Point(x,y);
    private boolean focusFigure = false;

    public MarkFigure(){

        Dimension panelDimension = new Dimension(800, 800);
        setSize(panelDimension);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if(focusFigure) {
            g2d.setColor(Color.red);
            drawRectangles(g2d);
        }
    }

    private void drawRectangles(Graphics2D g2) {

        g2.fillRect(rectanglePosition.x, rectanglePosition.y, 90, 90);
    }

    public void addCalculateListener(MouseListener mousePressPosition){

        addMouseListener(mousePressPosition);
    }

    public Point getRectamglePosition(){

        return rectanglePosition;
    }

    public void calculateAndSetRectanglePosition(Point clickPosition){

        x = ((int)(clickPosition.x / 100) * 100) + 5;
        y = ((int)(clickPosition.y / 100) * 100) + 5;
        this.rectanglePosition.x = x;
        this.rectanglePosition.y = y;

    }

    public void toggleFocusFigure(){

        if(focusFigure) {
            this.focusFigure = false;
        }
        else{
            this.focusFigure = true;
        }
    }

    public void setFocusFigure(boolean focusFigure){

        this.focusFigure = focusFigure;
    }
}
