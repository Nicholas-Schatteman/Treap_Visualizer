

public class LinkedList<T> {
    private LinkedListNode<T> first;
    private LinkedListNode<T> start;
    private LinkedListNode<T> last;
    private LinkedListNode<T> current;
    private LinkedListNode<T> searchNode;
    private boolean isFinished = false;

    public void insert(LinkedList<T> operations){
        insertNode(operations.first);
    }

    public void insert(T operation){
        insertNode(new LinkedListNode<T>(operation));
    }

    public void insertNode(LinkedListNode<T> newNode){
        if (first == null){
            first = newNode;
            current = newNode;
            last = newNode;
        }
        else{
            last.setNext(newNode);
            if (isFinished){
                isFinished = false;
                current = current.getNext();
            }
            while (last.getNext() != null){
                last = last.getNext();
            }
        }    
    }

    public T next(){
        if (!hasNext()){
            throw new ArrayIndexOutOfBoundsException("No next value to return");
        }
        else if (current.getNext() == null){
            isFinished = true;
            return current.value;
        }
        else{
            T returnVal = current.value;
            current = current.getNext();
            return returnVal;
        }
    }

    public boolean hasNext(){
        if (first == null){
            return false;
        }
        else{
            return !isFinished;
        }
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

    public T getCurrent(){
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
