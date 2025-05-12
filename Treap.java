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
        //printArray(inLineTraversal());
    }

    public void printArray(int[] s){
        if (s.length != 0){
            String string = s[0] + "";
            for (int i = 1; i < s.length; i++){
                string += ", " + s[i];
            }
            //System.out.println(string);
        }
    }

    public int[] inLineTraversal(){
        return head.inLineTraversal();
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
