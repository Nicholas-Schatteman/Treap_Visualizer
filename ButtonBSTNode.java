import java.awt.Graphics;
import java.awt.Rectangle;

public class ButtonBSTNode {
    private ButtonBSTNode left;
    private ButtonBSTNode right;
    private Button button;

    public ButtonBSTNode(Button b){
        button = b;
    }

    public ButtonBSTNode getLeft(){
        return left;
    }

    public ButtonBSTNode getRight(){
        return right;
    }

    public Button getButton(){
        return button;
    }

    

    public void insert(Button b){
        insert(new ButtonBSTNode(b));
    }

    public void insert(ButtonBSTNode node){
        ButtonBSTNode current;
        ButtonBSTNode next = this;
        boolean isFound = false;

        while (next != null && !isFound) {
            current = next;
            if (node.button.getCenterX() < current.button.getCenterX()){
                if (current.left == null){
                    current.left = node;
                    isFound = true;
                }
                else{
                    next = current.left;
                }
            }
            else{
                if (current.right == null){
                    current.right = node;
                    isFound = true;
                }
                else{
                    next = current.right;
                }
            }
        }
    }

    public void update(Graphics g, Rectangle border){
        if (left != null){
            left.update(g, border);
        }
        this.button.update(g, border);
        if (right != null){
            right.update(g, border);
        }
    }
}