import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

abstract public class DropDownButton extends Button{
    private LinkedList<Button> dropDownButtons;
    private Rectangle closedhitBox;
    private Rectangle openHitBox;
    private BufferedImage image;
    private boolean isOpen;

    public DropDownButton(Rectangle closedhitBox, Rectangle openHitBox, BufferedImage image){
        super(closedhitBox, Cursor.DEFAULT_CURSOR, SW);
        this.closedhitBox = closedhitBox;
        this.openHitBox = openHitBox;
        this.image = image;
        dropDownButtons = new LinkedList<Button>();
        isOpen = false;
    }

    public void insert(Button b){
        dropDownButtons.insert(b);
    }

    @Override
    public boolean isOver(Point p, Rectangle border) {
        boolean isOver = super.isOver(p, border);
        if (!isOpen && isOver){
            isOpen = true;
            hitBox = openHitBox;
            while (dropDownButtons.hasNext()) {
                dropDownButtons.next().setVisable(true);
            }
            dropDownButtons.restart();
        }
        else if (isOpen && !isOver){
            isOpen = false;
            hitBox = closedhitBox;
            while (dropDownButtons.hasNext()) {
                dropDownButtons.next().setVisable(false);
            }
            dropDownButtons.restart();
        }
        return isOver;
    }

    @Override
    public void offPress() {}

    @Override
    public void runPress(BinaryTree tree) {}

    @Override
    public Button runPress(Point p) {return null;}
}
