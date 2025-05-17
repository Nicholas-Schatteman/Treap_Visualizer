import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;

abstract public class Button {
    protected Rectangle hitBox;
    private Cursor overCursor;
    protected boolean isVisable;

    public Button(Rectangle hitBox, Cursor overCursor){
        this.hitBox = hitBox;
        this.overCursor = overCursor;
    }

    public Button(Rectangle hitBox, int cursorType){
        this(hitBox, new Cursor(cursorType));
    }

    public boolean isOver(Point p){
        return hitBox.contains(p);
    }

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


    public void setCursor(Cursor c){
        overCursor = c;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    public void setVisable(boolean isVisable) {
        this.isVisable = isVisable;
    }

    public Cursor getCursor(){
        return overCursor;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public Cursor getOverCursor() {
        return overCursor;
    }

    abstract public Button runPress(Point p);

    abstract public void runPress(BinaryTree tree);

    abstract public void draw(Graphics g);
}
