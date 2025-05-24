import java.awt.Point;
import java.awt.Rectangle;

public class ButtonSpacePartition {
    private ButtonSpacePartitionNode head;
    private Rectangle area;
    private int resolution;

    public ButtonSpacePartition(Rectangle area, int resolution){
        head = new ButtonSpacePartitionNode();
        this.area = area;
        this.resolution = resolution;
    }

    public ButtonSpacePartition(LinkedList<Button> buttons, int resolution){
        Point p1 = new Point();
        Point p2 = new Point();
        Rectangle currentButtonArea;
        while (buttons.hasNext()) {
            currentButtonArea = buttons.next().hitBox;
            
            if (currentButtonArea.x < p1.x){
                p1.x = currentButtonArea.x;
            }
            if (currentButtonArea.y < p1.y){
                p1.y = currentButtonArea.y;
            }
            if (currentButtonArea.x + currentButtonArea.width > p2.x){
                p2.x = currentButtonArea.x + currentButtonArea.width;
            }
            if (currentButtonArea.y + currentButtonArea.height > p2.y){
                p2.y = currentButtonArea.y + currentButtonArea.height;
            }
        }
        buttons.restart();

        head = new ButtonSpacePartitionNode();
        area = new Rectangle(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
        this.resolution = resolution;

        while (buttons.hasNext()) {
            insert(buttons.next());
        }
    }

    public boolean canInsert(Rectangle r){
        return area.contains(r);
    }

    public LinkedList<Button> search(Point p, Rectangle border){
        return head.search(p, border, area, resolution);
    }

    public void insert(Button b){
        if (!canInsert(b.hitBox)){
            //TODO: remove after troubleshooting
            throw new Error("This button is outside tracked area.");
        }
        head.insert(b, b.hitBox, area, resolution);
    }
}