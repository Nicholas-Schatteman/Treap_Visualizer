public class OperationLLNode {
    public int value;
    private OperationLLNode next;
    private OperationLLNode previous;

    public OperationLLNode(int value){
        this.value = value;
    }

    public OperationLLNode getNext() {
        return next;
    }

    public OperationLLNode getPrevious() {
        return previous;
    }

    public void setNext(OperationLLNode next) {
        this.next = next;
        next.previous = this;
    }
}
