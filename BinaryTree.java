import java.awt.Color;
import java.awt.Graphics;

public class BinaryTree {
    private BinaryTreeNode head;
    private BinaryTreeNode current;
    private BinaryTreeLL highlightedNodes;

    private Treap tree;

    public BinaryTree(boolean isMaxHeap){
        tree = new Treap(isMaxHeap);
        highlightedNodes = new BinaryTreeLL();
    }

    public void insert(int priority, int value){
        tree.insert(priority, value);
    }

    public BinaryTreeNode getHead() {
        return head;
    }

    public int getCurrentValue() {
        return current.getValue();
    }
    
    public boolean hasCurrent(){
        return current != null;
    }

    public void enactOperation(){
        highlightedNodes.restart();
        while (highlightedNodes.hasNext()){
            highlightedNodes.next().setColor(Color.BLACK);
        }
        highlightedNodes = new BinaryTreeLL();
        
        int operation = tree.getOperations().next();
        System.out.println(operation);
        switch (operation) {//TODO: Finish operations
            case OperationLL.START:
                current = head;
                //highlightedNodes.insert(head);
                break;

            case OperationLL.FIRST_INSERT:
                head = tree.getNodeOperations().next();
                break;

            case OperationLL.CHECK_VALUE_LEFT:
                if (current.getLeft() != null){
                    current = current.getLeft();
                }
                //highlightedNodes.insert(current);
                break;
        
            case OperationLL.CHECK_VALUE_RIGHT:
            if (current.getRight() != null){
                current = current.getRight();
            }
                //highlightedNodes.insert(current);
                break;

            case OperationLL.INSERT_LEFT:
                current.insertNodeLeft(tree.getNodeOperations().next());
                head.updatePosition();
                break;

            case OperationLL.INSERT_RIGHT:
                current.insertNodeRight(tree.getNodeOperations().next());
                head.updatePosition();
                break;

            case OperationLL.ROTATE_LEFT:
                //highlightedNodes.insert(current);
                //highlightedNodes.insert(current.getLeft());
                //highlightedNodes.insert(current.getRight());
                current.rotateLeft();
                current = current.getParent();
                if (current.getParent() == null){
                    head = current;
                }
                else{
                    current = current.getParent();
                }
                head.updatePosition();
                break;

            case OperationLL.ROTATE_RIGHT:
                current.rotateRight();
                current = current.getParent();
                if (current.getParent() == null){
                    head = current;
                }
                else{
                    current = current.getParent();
                }
                head.updatePosition();
                break;
        
            case OperationLL.VALIDATE_PRIORITY:
                //highlightedNodes.insert(current);
                if (current.getLeft() != null){
                    //highlightedNodes.insert(current.getLeft());
                }
                if (current.getRight() != null){
                    //highlightedNodes.insert(current.getRight());
                }
                break;

            default:
                break;
        }
        if (current != null){
            highlightedNodes.insert(current);
        }
        while (highlightedNodes.hasNext()){
            highlightedNodes.next().setColor(Color.RED);
        }
    }

    public void updatePosition(){
        if (head != null){
            head.updatePosition();
        }
    }

    public void update(Graphics g, double screenX, double screenY, double zoomFactor){
        if (head != null){
            head.update(g, screenX, screenY, zoomFactor);
        }
    }
}
