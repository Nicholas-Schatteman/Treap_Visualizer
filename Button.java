import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;

abstract public class Button {
    protected Rectangle hitBox;
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

    public boolean isOver(Point p, Rectangle border){
        //return hitBox.contains(p.x, p.y);
        return isOver(p.x, p.y, border);
    }

    public boolean isOver(int x, int y, Rectangle border){
        double xPos = -1;
        double yPos = -1;
        
        switch (relativeCorner) {
            case NW:
                xPos = x - hitBox.x;
                yPos = y - hitBox.y;
                break;
        
            case NE:
                xPos = x + hitBox.x - border.width;
                yPos = y - hitBox.y;
                break;

            case SW:
                xPos = x - hitBox.x;
                yPos = y + hitBox.y - border.height;
                break;


            case SE:
                xPos = x + hitBox.x - border.width;
                yPos = y + hitBox.y - border.height;
                break;

            default:
                break;
        }

        return 0 <= xPos && xPos <= hitBox.width && 0 <= yPos && yPos <= hitBox.height;
    }

    public void update(Graphics g, Rectangle border){
        if (isVisable){
            Rectangle placement;
            switch (relativeCorner) {
                case NW:
                    placement = new Rectangle(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
                    draw(g, placement);
                    break;
            
                case NE:
                    placement = new Rectangle(border.width - hitBox.x, hitBox.y, hitBox.width, hitBox.height);
                    draw(g, placement);
                    break;

                case SW:
                    placement = new Rectangle(hitBox.x, border.height - hitBox.y, hitBox.width, hitBox.height);
                    draw(g, placement);
                    break;

                case SE:
                    placement = new Rectangle(border.width - hitBox.x, border.height - hitBox.y, hitBox.width, hitBox.height);
                    draw(g, placement);
                    break;

                default:
                    break;
            }
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

    abstract public void offPress();

    abstract public Button runPress(Point p);

    abstract public void runPress(BinaryTree tree);

    abstract public void draw(Graphics g, Rectangle placement);
}
