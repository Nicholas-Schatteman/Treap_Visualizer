import java.awt.image.BufferedImage;
import java.awt.Graphics;
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
    public void draw(Graphics g){
        g.drawImage(image, hitBox.x, hitBox.y, hitBox.width, hitBox.height, null);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
