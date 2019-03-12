import javax.swing.*;

public class DrawFigures{

    JLabel drawIcon(ChessFigures figure) {

        String figureToString = figure.toString();
//        System.out.println(figureToString);
        String imgURL = "out/production/resources/" + figureToString + ".png";
        ImageIcon icon = new ImageIcon(imgURL);
        JLabel iconLabel = new JLabel(icon);
        return iconLabel;
    }
}

