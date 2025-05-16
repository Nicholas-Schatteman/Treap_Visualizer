public class OperationLL {
    private OperationLLNode first;
    private OperationLLNode start;
    private OperationLLNode last;
    private OperationLLNode current;
    private OperationLLNode searchNode;

    private boolean isAtExtreme = true;

    final static public int START = 0;
    final static public int FIRST_INSERT = 1;
    final static public int CHECK_VALUE_LEFT = 2;
    final static public int CHECK_VALUE_RIGHT = 3;
    final static public int INSERT_LEFT = 4;
    final static public int INSERT_RIGHT = 5;
    final static public int ROTATE_LEFT = 6;
    final static public int ROTATE_RIGHT = 7;
    final static public int VALIDATE_PRIORITY = 8;

    public void insert(OperationLL operations){
        insertNode(operations.getFirst());
    }

    public void insert(int operation){
        insertNode(new OperationLLNode(operation));
    }

    public void insertNode(OperationLLNode newNode){
        if (first == null){
            first = newNode;
            current = newNode;
            last = newNode;
        }
        else{
            last.setNext(newNode);
            while (last.getNext() != null){
                last = last.getNext();
            }
        }    
    }

    public int next(){
        if (!hasNext()){
            throw new ArrayIndexOutOfBoundsException("No next value to return");
        }
        else if (current.getNext() == null){
            isAtExtreme = true;
            return current.value;
        }
        else if (isAtExtreme){
            isAtExtreme = false;
            current = current.getNext();
            return current.getPrevious().value;
        }
        else{
            current = current.getNext();
            return current.getPrevious().value;
        }
    }

    public int previous(){
        if (!hasPrevious()){
            throw new ArrayIndexOutOfBoundsException("No previous value to return");
        }
        else if (current.getPrevious() == null){
            isAtExtreme = true;
            return current.value;
        }
        else if (isAtExtreme){
            isAtExtreme = false;
            current = current.getPrevious();
            return current.getNext().value;
        }
        else{
            current = current.getPrevious();
            return current.getNext().value;
        }
    }

    public boolean hasNext(){
        return current.getNext() != null || !isAtExtreme;
    }

    public boolean hasPrevious(){
        return current.getPrevious() != null || !isAtExtreme;
    }

    public boolean isAtStart(){
        return current == start;
    }

    public boolean isBeforeSearch(){
        return current.getNext().getNext() == searchNode;
    }

    public OperationLLNode getFirst() {
        return first;
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
