import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;

abstract public class Button {
    protected Rectangle hitBox;
    protected Rectangle border;
    private Cursor overCursor;
    private int relativeCorner;
    protected boolean isVisable;

    final public static int NW = 0;
    final public static int NE = 1;
    final public static int SW = 2;
    final public static int SE = 3;

    public Button(Rectangle hitBox, Cursor overCursor, int orientation){
        this.hitBox = hitBox;
        this.overCursor = overCursor;
        relativeCorner = orientation;
    }

    public Button(Rectangle hitBox, Cursor overCursor){
        this(hitBox, overCursor, 0);
    }

    public Button(Rectangle hitBox, int cursorType, int orientation){
        this(hitBox, new Cursor(cursorType),orientation);
    }

    public Button(Rectangle hitBox, int cursorType){
        this(hitBox, new Cursor(cursorType));
    }

    public boolean isOver(Point p){
        return hitBox.contains(p);
    }

    public boolean isOver(int x, int y){
        double xPos = -1;
        double yPos = -1;
        
        switch (relativeCorner) {
            case NW:
                xPos = x - hitBox.x;
                yPos = y - hitBox.y;
                break;
        
            case NE:
                xPos = x + hitBox.x - border.getWidth();
                yPos = y - hitBox.y;
                break;

            case SW:
                xPos = x - hitBox.x;
                yPos = y + hitBox.y - border.getHeight();
                break;


            case SE:
                xPos = x + hitBox.x - border.getWidth();
                yPos = y + hitBox.y - border.getHeight();
                break;

            default:
                break;
        }

        return 0 <= xPos && xPos <= hitBox.getWidth() && 0 <= yPos && yPos <= hitBox.getHeight();
    }

    public void update(Graphics g, Rectangle border){
        setBorder(border);
        if (isVisable){
            draw(g);
        }
    }

    public int getCenterX(){
        return hitBox.x + hitBox.width / 2;
    }

    public void setBorder(Rectangle border) {
        this.border = border;
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

    abstract public void offPress();

    abstract public Button runPress(Point p);

    abstract public void runPress(BinaryTree tree);

    abstract public void draw(Graphics g);
}
