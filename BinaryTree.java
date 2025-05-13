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
                highlightedNodes.insert(head);
                break;

            case OperationLL.FIRST_INSERT:
                head = tree.getNodeOperations().next();
                break;

            case OperationLL.CHECK_VALUE_LEFT:
                if (current.getLeft() != null){
                    current = current.getLeft();
                }
                highlightedNodes.insert(current);
                break;
        
            case OperationLL.CHECK_VALUE_RIGHT:
            if (current.getRight() != null){
                current = current.getRight();
            }
                highlightedNodes.insert(current);
                break;

            case OperationLL.INSERT_LEFT:
                current.insertNodeLeft(tree.getNodeOperations().next());
                current.updatePosition();
                break;

            case OperationLL.INSERT_RIGHT:
            current.insertNodeRight(tree.getNodeOperations().next());
                current.updatePosition();
                break;

            case OperationLL.ROTATE_LEFT:
                current.rotateLeft();
                current.updatePosition();
                break;

            case OperationLL.ROTATE_RIGHT:
                current.rotateRight();
                current.updatePosition();
                break;
        
            case OperationLL.VALIDATE_PRIORITY:
                if (tree.getOperations().getPrevious() == OperationLL.VALIDATE_PRIORITY && current.getParent() != null){
                    current = current.getParent();
                }

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
