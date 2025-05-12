import java.awt.Polygon;
import java.awt.Graphics;

abstract public class Button {
    Polygon hitBox;
    boolean isVisable;

    public boolean isOver(int x, int y){
        return hitBox.contains(x, y);
    }

    public void update(Graphics g){
        if (isVisable){
            draw(g);
        }
    }

    abstract public void draw(Graphics g);
}
