public class Treap {
    private TreapNode head;
    private LinkedList<Integer> operations;
    private LinkedList<BinaryTreeNode> nodeOperations;
    private boolean isMaxHeap;

    public Treap(){
        this(false);
    }

    public Treap(boolean isMaxHeap){
        this.isMaxHeap = isMaxHeap;
        operations = new LinkedList<Integer>();
        nodeOperations = new LinkedList<BinaryTreeNode>();
    }

    public TreapNode getHead(){
        return head;
    }

    public LinkedList<Integer> getOperations(){
        return operations;
    }

    public LinkedList<BinaryTreeNode> getNodeOperations() {
        return nodeOperations;
    }

    public void insert(int priority, int value){
        insertNode(new TreapNode(priority, value));
    }

    public void insertNode(TreapNode tree){
        if (head == null){
            operations.insert(OperationLL.FIRST_INSERT);
            nodeOperations.insert(new BinaryTreeNode(tree.priority, tree.value));
            head = tree;
        }
        else{
            nodeOperations.insert(new BinaryTreeNode(tree.priority, tree.value));
            head = head.insertNode(tree, operations, isMaxHeap);
        }
    }
}
