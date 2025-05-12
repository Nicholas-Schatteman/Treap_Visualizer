public class LinkedList {
    private LinkedListNode first;
    private LinkedListNode start;
    private LinkedListNode last;
    private LinkedListNode current;
    private LinkedListNode searchNode;

    final static public int CHECK_VALUE_LEFT = 1;
    final static public int CHECK_VALUE_RIGHT = 2;
    final static public int ROTATE_LEFT = 3;
    final static public int ROTATE_RIGHT = 4;
    final static public int VALIDATE_PRIORITY = 5;

    public void insert(int operation){
        insertNode(new LinkedListNode(operation));
    }

    public void insertNode(LinkedListNode newNode){
        if (first == null){
            first = newNode;
            current = newNode;
            last = newNode;
        }
        else{
            last.next = newNode;
            last = newNode;
        }    
    }

    public int next(){
        if (current != null){
            int returnVal = current.value;
            current = current.next;
            return returnVal;
        }
        else{
            return -1;
        }
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

    public int getCurrent(){
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
