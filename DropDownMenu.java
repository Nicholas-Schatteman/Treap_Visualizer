import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

abstract public class DropDownMenu implements StaticVisualElement{
    private LinkedList<StaticVisualElement> dropDownButtons;
    protected Rectangle closedhitBox;
    private Rectangle openHitBox;
    private boolean isVisable;
    private boolean isOpen;

    public DropDownMenu(Rectangle closedhitBox, Rectangle openHitBox){
        this.closedhitBox = closedhitBox;
        this.openHitBox = openHitBox;
        dropDownButtons = new LinkedList<StaticVisualElement>();
        isOpen = false;
    }

    public void insert(StaticVisualElement e){
        dropDownButtons.insert(e);
    }

    public void setVisable(boolean isVisable) {
        this.isVisable = isVisable;

        if (!isVisable){
            isOpen = false;
            while (dropDownButtons.hasNext()) {
                dropDownButtons.next().setVisable(false);
            }
            dropDownButtons.restart();
        }
    }

    public boolean isOver(Point p, Rectangle border) {
        boolean isOver;

        if (isVisable){
            double xPos;
            double yPos;

            if (!isOpen){
                xPos = p.x - closedhitBox.x;
                yPos = p.y + closedhitBox.y - border.height;
                isOver = (0 <= xPos && xPos <= closedhitBox.width && 0 <= yPos && yPos <= closedhitBox.height);

                if (isOver){
                    isOpen = true;
                    while (dropDownButtons.hasNext()) {
                        dropDownButtons.next().setVisable(true);
                    }
                    dropDownButtons.restart();
                }
            }
            else{
                xPos = p.x - openHitBox.x;
                yPos = p.y + openHitBox.y - border.height;
                isOver = (0 <= xPos && xPos <= openHitBox.width && 0 <= yPos && yPos <= openHitBox.height);

                if (!isOver){
                    isOpen = false;
                    while (dropDownButtons.hasNext()) {
                        dropDownButtons.next().setVisable(false);
                    }
                    dropDownButtons.restart();
                }
            }
        }

        else{
            isOver = false;
        }

        return isOver;
    }

    public void update(Graphics g, Rectangle border){
        if (isVisable){
            draw(g, new Rectangle(closedhitBox.x, border.height - closedhitBox.y, closedhitBox.width, closedhitBox.height));
        }
    }

    abstract protected void draw(Graphics g, Rectangle placement);
}
