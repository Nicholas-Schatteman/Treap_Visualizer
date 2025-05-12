public class Treap {
    private TreapNode head;
    private LinkedList operations;
    private boolean isMaxHeap;

    public Treap(){
        this(false);
    }

    public Treap(boolean isMaxHeap){
        this.isMaxHeap = isMaxHeap;
    }

    public TreapNode getHead(){
        return head;
    }

    public void insert(int priority, int value){
        insertNode(new TreapNode(priority, value));
    }

    public void insertNode(TreapNode tree){
        if (head == null){
            head = tree;
        }
        else{
            head = head.insertNode(tree, isMaxHeap);
        }
    }
}
