import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Button {
    public BufferedImage image;
    public Rectangle hitBox;
    public boolean isVisable;

    public boolean isOver(int x, int y){
        return hitBox.contains(x, y);
    }

    public void update(Graphics g){
        if (isVisable){
            draw(g);
        }
    }

    public int getCenterX(){
        return hitBox.x + hitBox.width / 2;
    }

    public void draw(Graphics g){
        g.drawImage(image, hitBox.x, hitBox.y, hitBox.width, hitBox.height, null);
    }
}
