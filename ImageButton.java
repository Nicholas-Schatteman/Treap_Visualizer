import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Cursor;

abstract public class ImageButton extends Button{
    private BufferedImage image;

    public ImageButton(Rectangle hitBox, Cursor overCursor){
        super(hitBox, overCursor);
    }

    public ImageButton(Rectangle hitBox, int cursorType){
        super(hitBox, cursorType);
    }

    @Override
    public boolean isOver(Point p) {
        return isOver((int)p.getX(), (int)p.getY());
    }

    @Override
    public boolean isOver(int x, int y) {
        double xPos = x + hitBox.x - border.getWidth();
        double yPos = y + hitBox.y - border.getHeight();
        return 0 <= xPos && xPos <= hitBox.getWidth() && 0 <= yPos && yPos <= hitBox.getHeight();
    }
    
    @Override
    public void draw(Graphics g){
        g.drawImage(image, (int)border.getWidth() - hitBox.x, (int)border.getHeight() - hitBox.y, hitBox.width, hitBox.height, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
