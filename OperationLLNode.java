public class OperationLLNode {
    public int value;
    private OperationLLNode next;

    public OperationLLNode(int value){
        this.value = value;
    }

    public OperationLLNode getNext() {
        return next;
    }

    public void setNext(OperationLLNode next) {
        this.next = next;
    }
}
