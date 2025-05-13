import java.awt.Graphics;

public class GraphicsUtility {
    final public static double HEIGHT_TO_SIZE = 1.3913;
    final public static double HEIGHT_TO_CWIDTH = 0.7739;

    public static void drawString(Graphics g, String data, double x, double y, double height, double screenX, double screenY, double zoomFactor){
        g.setFont(g.getFont().deriveFont((float)(height * zoomFactor * HEIGHT_TO_SIZE)));
        g.drawString(data, (int)(x * zoomFactor - screenX), (int)(y * zoomFactor - screenY));
    }

    public static void drawStringXCentered(Graphics g, String data, double x, double y, double height, double screenX, double screenY, double zoomFactor){
        g.setFont(g.getFont().deriveFont((float)(height * zoomFactor * HEIGHT_TO_SIZE)));
        g.drawString(data, (int)((x - data.length() * HEIGHT_TO_CWIDTH * height / 2) * zoomFactor - screenX), (int)(y * zoomFactor - screenY));
    }

    public static void fillCircle(Graphics g, double x, double y, double diamiter, double screenX, double screenY, double zoomFactor){
        g.fillOval((int)((x - diamiter / 2) * zoomFactor - screenX), (int)((y - diamiter / 2) * zoomFactor - screenY), (int)(diamiter * zoomFactor), (int)(diamiter * zoomFactor));
    }

    public static void fillRectangleC(Graphics g, double x, double y, double width, double height, double screenX, double screenY, double zoomFactor){
        g.fillRect((int)((x - width / 2) * zoomFactor - screenX), (int)((y - height / 2) * zoomFactor - screenY), (int)(width * zoomFactor), (int)(height * zoomFactor));
    }

    public static void drawLine(Graphics g, double x1, double y1, double x2, double y2, double screenX, double screenY, double zoomFactor){
        g.drawLine((int)(x1 * zoomFactor - screenX), (int)(y1 * zoomFactor - screenY), (int)(x2 * zoomFactor - screenX), (int)(y2 * zoomFactor - screenY));
    }
}
