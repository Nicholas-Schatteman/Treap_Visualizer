import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class ButtonBST {
    ButtonBSTNode head;

    public Button search(Point p){
        ButtonBSTNode current = head;
        while (current != null){
            if (current.getButton().isOver(p)){
                return current.getButton();
            }
            else if (p.getX() < current.getButton().getCenterX()){
                current = current.getLeft();
            }
            else{
                current = current.getRight();
            }
        }

        return null;
    }

    public void insert(Button b){
        insert(new ButtonBSTNode(b));
    }

    public void insert(ButtonBSTNode node){
        if (head == null){
            head = node;
        }
        else {
            head.insert(node);
        }
    }

    public void update(Graphics g, Rectangle border){
        head.update(g, border);
    }
}
