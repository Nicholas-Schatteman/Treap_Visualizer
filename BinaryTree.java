import java.awt.Color;
import java.awt.Graphics;
import java.time.Clock;

public class BinaryTree {
    private BinaryTreeNode head;
    private BinaryTreeNode current;
    private LinkedList<BinaryTreeNode> highlightedNodes;
    private long timeSince;

    final public double TIME_TO_MOVE = 600;

    private Treap tree;

    public BinaryTree(boolean isMaxHeap){
        tree = new Treap(isMaxHeap);
        highlightedNodes = new LinkedList<BinaryTreeNode>();
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

    public boolean hasNext(){
        return tree.getOperations().hasNext();
    }

    public boolean hasPrevious(){
        return tree.getOperations().hasPrevious();
    }

    public void resetTreap(){
        head = null;
        current = null;
        resetTreeNodeLL();
        tree.getOperations().restart();
        tree.getNodeOperations().restart();
    }

    public void setSearchOperation(){
        tree.getOperations().setSearchCurrent();
    }

    public boolean isBeforeSearch(){
        return tree.getOperations().isBeforeSearch();
    }

    private void resetTreeNodeLL(){
        highlightedNodes.restart();
        while (highlightedNodes.hasNext()){
            highlightedNodes.next().setColor(Color.BLACK);
        }
        highlightedNodes = new LinkedList<BinaryTreeNode>();
    }

    public void nextOperation(){
        int operation = tree.getOperations().next();

        switch (operation) {//TODO: Finish operations
            case OperationLL.START:
                resetTreeNodeLL();
                current = head;
                highlightedNodes.insert(current);
                break;

            case OperationLL.END:
                resetTreeNodeLL();
                break;

            case OperationLL.FIRST_INSERT:
                resetTreeNodeLL();
                head = tree.getNodeOperations().next().duplicate();
                break;

            case OperationLL.CHECK_VALUE_LEFT:
                resetTreeNodeLL();
                if (current.getLeft() != null){
                    current = current.getLeft();
                }
                highlightedNodes.insert(current);
                break;
        
            case OperationLL.CHECK_VALUE_RIGHT:
                resetTreeNodeLL();
                if (current.getRight() != null){
                    current = current.getRight();
                }
                highlightedNodes.insert(current);
                break;

            case OperationLL.INSERT_LEFT:
                updateStartPosition();
                resetTreeNodeLL();
                current.insertNodeLeft(tree.getNodeOperations().next().duplicate());
                highlightedNodes.insert(current.getLeft());
                break;

            case OperationLL.INSERT_RIGHT:
                updateStartPosition();
                resetTreeNodeLL();
                current.insertNodeRight(tree.getNodeOperations().next().duplicate());
                highlightedNodes.insert(current.getRight());
                break;

            case OperationLL.ROTATE_LEFT:
                updateStartPosition();
                current.rotateLeft();
                current = current.getParent();
                if (current.getParent() == null){
                    head = current;
                }
                else{
                    current = current.getParent();
                }
                break;

            case OperationLL.ROTATE_RIGHT:
                updateStartPosition();
                current.rotateRight();
                current = current.getParent();
                if (current.getParent() == null){
                    head = current;
                }
                else{
                    current = current.getParent();
                }
                break;
        
            case OperationLL.VALIDATE_PRIORITY:
                resetTreeNodeLL();
                highlightedNodes.insert(current);
                if (current.getLeft() != null){
                    highlightedNodes.insert(current.getLeft());
                }
                if (current.getRight() != null){
                    highlightedNodes.insert(current.getRight());
                }
                break;

            default:
                break;
        }
        while (highlightedNodes.hasNext()){
            highlightedNodes.next().setColor(Color.RED);
        }
    }

    public double timeFunction(){
        double time = (double)(Clock.systemUTC().millis() - timeSince) / TIME_TO_MOVE;
        if (time < 1){
            return time;
        }
        else{
            return 1;
        }
    }

    public boolean isMoving(){
        return timeFunction() != 1;
    }

    public void updateStartPosition(){
        if (head != null){
            head.startX = 0;
            head.startY = 0;
            head.updateStartPosition();
            timeSince = Clock.systemUTC().millis();
        }
    }

    public void updatePosition(){
        if (head != null){
            head.updatePosition(timeFunction());
        }
    }

    public void update(Graphics g, double screenX, double screenY, double zoomFactor){
        if (head != null){
            head.update(g, screenX, screenY, zoomFactor);
        }
    }
}
