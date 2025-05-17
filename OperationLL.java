public class OperationLL {
    private OperationLLNode first;
    private OperationLLNode start;
    private OperationLLNode last;
    private OperationLLNode current;
    private OperationLLNode searchNode;

    private boolean isFinished = false;

    final static public int START = 0;
    final static public int END = 1;
    final static public int FIRST_INSERT = 2;
    final static public int CHECK_VALUE_LEFT = 3;
    final static public int CHECK_VALUE_RIGHT = 4;
    final static public int INSERT_LEFT = 5;
    final static public int INSERT_RIGHT = 6;
    final static public int ROTATE_LEFT = 7;
    final static public int ROTATE_RIGHT = 8;
    final static public int VALIDATE_PRIORITY = 9;

    public void insert(OperationLL operations){
        insertNode(operations.first);
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
            isFinished = true;
            return current.value;
        }
        else{
            int returnVal = current.value;
            current = current.getNext();
            return returnVal;
        }
    }

    public boolean hasNext(){
        return !isFinished;
    }

    public boolean hasPrevious(){
        return !(first == current);
    }

    public boolean isAtStart(){
        return current == start;
    }

    public boolean isBeforeSearch(){
        return current.getNext() == searchNode;
    }

    public int getCurrent(){
        return current.value;
    }

    public void restart(){
        current = first;
        isFinished = false;
    }

    public void setStartLast(){
        start = last;
    }

    public void setSearchCurrent(){
        if (isFinished){
            searchNode = null;
        }
        else{
            searchNode = current;
        }
    }
}
