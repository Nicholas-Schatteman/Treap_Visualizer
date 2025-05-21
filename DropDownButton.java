import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

abstract public class DropDownButton extends Button{
    LinkedList<Button> dropDownButtons;
    BufferedImage image;

    public DropDownButton(Rectangle hitBox, BufferedImage image){
        super(hitBox, Cursor.DEFAULT_CURSOR, SW);
        this.image = image;
        dropDownButtons = new LinkedList<Button>();
    }

    public void insert(Button b){
        dropDownButtons.insert(b);
    }

    @Override
    public void offPress() {
        while (dropDownButtons.hasNext()) {
            dropDownButtons.next().setVisable(false);
        }
        dropDownButtons.restart();
    }

    @Override
    public void runPress(BinaryTree tree) {}

    //Could be done in either run function
    @Override
    public Button runPress(Point p) {
        while (dropDownButtons.hasNext()) {
            dropDownButtons.next().setVisable(true);
        }
        dropDownButtons.restart();
        return null;
    }
}
