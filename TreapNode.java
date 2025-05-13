public class TreapNode{
    private TreapNode parent;
    private TreapNode left;
    private TreapNode right;
    public int priority;
    public int value;

    public TreapNode(int priority, int value){
        this.priority = priority;
        this.value = value;

    }

    public void insert(int priority, int value, OperationLL operations, boolean isMaxHeap){
        insertNode(new TreapNode(priority, value), operations, isMaxHeap);
    }

    public TreapNode insertNode(TreapNode tree, OperationLL operations, boolean isMaxHeap){
        operations.insert(OperationLL.START);
        TreapNode head = this;

        if (tree == null){}
        else{
            boolean foundPlace = false;
            TreapNode current = this;

            while (!foundPlace){

                if (tree.value < current.value){
                    if (current.getLeft() == null){
                        current.insertNodeLeft(tree);
                        operations.insert(OperationLL.INSERT_LEFT);
                        foundPlace = true;
                    }
                    else{
                        current = current.getLeft();
                        operations.insert(OperationLL.CHECK_VALUE_LEFT);
                    }
                }
                else{
                    if (current.getRight() == null){
                        current.insertNodeRight(tree);
                        operations.insert(OperationLL.INSERT_RIGHT);
                        foundPlace = true;
                    }
                    else{
                        current = current.getRight();
                        operations.insert(OperationLL.CHECK_VALUE_RIGHT);
                    }
                }
            }
            
            TreapNode lostNode;
            boolean isValidHeap = false;
            if (isMaxHeap){
                while (!isValidHeap && current != null) {
                    operations.insert(OperationLL.VALIDATE_PRIORITY);

                    if (current.getLeft() != null && current.getLeft().priority > current.priority){
                        operations.insert(OperationLL.ROTATE_LEFT);
                        if (current == head){
                            head = current.getLeft();
                        }
                        lostNode = current.rotateRight();
                        current.getParent().insertNode(lostNode, operations, isMaxHeap);
                        current = current.getParent().getParent();
                    }
                    else if (current.getRight() != null && current.getRight().priority > current.priority){
                        operations.insert(OperationLL.ROTATE_RIGHT);

                        if (current == head){
                            head = current.getRight();
                        }
                        lostNode = current.rotateLeft();
                        current.getParent().insertNode(lostNode, operations, isMaxHeap);
                        current = current.getParent().getParent();
                    }
                    else{
                        isValidHeap = true;
                    }
                }
            }
            else{
                while (!isValidHeap && current != null) {
                    operations.insert(OperationLL.VALIDATE_PRIORITY);

                    if (current.getLeft() != null && current.getLeft().priority < current.priority){
                        operations.insert(OperationLL.ROTATE_LEFT);

                        if (current == head){
                            head = current.getLeft();
                        }
                        lostNode = current.rotateRight();
                        current.getParent().insertNode(lostNode, operations, isMaxHeap);
                        current = current.getParent().getParent();
                    }
                    else if (current.getRight() != null && current.getRight().priority < current.priority){
                        operations.insert(OperationLL.ROTATE_RIGHT);

                        if (current == head){
                            head = current.getRight();
                        }
                        lostNode = current.rotateLeft();
                        current.getParent().insertNode(lostNode, operations, isMaxHeap);
                        current = current.getParent().getParent();
                    }
                    else{
                        isValidHeap = true;
                    }
                }
            }
        }
        return head;
    }

    public TreapNode getParent(){
        return parent;
    }

    public TreapNode getLeft(){
        return left;
    }

    public TreapNode getRight(){
        return right;
    }

    public void insertNodeLeft(TreapNode node){
        left = node;
        node.parent = this;
    }

    public void insertNodeRight(TreapNode node){
        right = node;
        node.parent = this;
    }

    public TreapNode rotateLeft(){
        TreapNode lostNode = right.left;

        if (parent == null){
            right.parent = null;
            right.insertNodeLeft(this);
            this.right = null;
        }
        else if (parent.left == this){
            parent.insertNodeLeft(right);
            right.insertNodeLeft(this);
            this.right = null;
        }
        else{
            parent.insertNodeRight(right);
            right.insertNodeLeft(this);
            this.right = null;
        }

        return lostNode;
    }

    public TreapNode rotateRight(){
        TreapNode lostNode = left.right;

        if (parent == null){
            left.parent = null;
            left.insertNodeRight(this);
            this.left = null;
        }
        else if (parent.left == this){
            parent.insertNodeLeft(left);
            left.insertNodeRight(this);
            this.left = null;
        }
        else{
            parent.insertNodeRight(left);
            left.insertNodeRight(this);
            this.left = null;
        }

        return lostNode;
    }
}