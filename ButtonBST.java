import java.awt.Graphics;
import java.awt.Point;

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

    public boolean pressSearch(Point p){
        Button b = search(p);

        if (b == null){
            return false;
        }
        else{
            b.runPress();
            return true;
        }
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

    public void update(Graphics g){
        head.update(g);
    }
}
