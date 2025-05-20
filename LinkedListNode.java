public class LinkedListNode<T> {
    public T value;
    private LinkedListNode<T> next;

    public LinkedListNode(T value){
        this.value = value;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }
}