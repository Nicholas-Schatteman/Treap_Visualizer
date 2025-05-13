public class BinaryTreeLL {
    private BinaryTreeLLNode first;
    private BinaryTreeLLNode start;
    private BinaryTreeLLNode last;
    private BinaryTreeLLNode current;
    private BinaryTreeLLNode searchNode;

    public void insert(BinaryTreeLLNode node){
        insertNode(node);
    }

    public void insert(BinaryTreeNode node){
        insertNode(new BinaryTreeLLNode(node));
    }

    public void insert(int priority, int value){
        insert(new BinaryTreeNode(priority, value));
    }

    public void insertNode(BinaryTreeLLNode newNode){
        if (first == null){
            first = newNode;
            current = newNode;
            last = newNode;
        }
        else{
            last.next = newNode;
            while (last.next != null){
                last = last.next;
            }
        }    
    }

    public BinaryTreeNode next(){
        BinaryTreeNode returnVal = current.value;
        current = current.next;
        return returnVal;
    }

    public boolean hasNext(){
        return current != null;
    }

    public boolean isAtStart(){
        return current == start;
    }

    public boolean isBeforeSearch(){
        return current.next == searchNode;
    }

    public BinaryTreeLLNode getFirst() {
        return first;
    }

    public BinaryTreeNode getCurrent(){
        return current.value;
    }

    public void restart(){
        current = first;
    }

    public void setStartCurrent(){
        start = current;
    }

    public void setStartLast(){
        start = last;
    }

    public void setSearchCurrent(){
        searchNode = current;
    }
}
