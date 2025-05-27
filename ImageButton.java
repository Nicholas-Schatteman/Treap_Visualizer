import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Cursor;

abstract public class ImageButton extends Button{
    private BufferedImage image;

    public ImageButton(Rectangle hitBox, Cursor overCursor){
        super(hitBox, overCursor, SE);
    }

    public ImageButton(Rectangle hitBox, int cursorType){
        super(hitBox, cursorType, SE);
    }
    
    @Override
    protected void draw(Graphics g, Rectangle placement){
        g.drawImage(image, (int)placement.x, (int)placement.y, placement.width, placement.height, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
