import java.util.Map;

public class TreapNode{
    private TreapNode parent;
    private TreapNode left;
    private TreapNode right;
    public int priority;
    public int value;
    private int count;

    public TreapNode(int priority, int value){
        this.priority = priority;
        this.value = value;

    }

    public void insert(int priority, int value){
        boolean foundEqual = false;
        TreapNode current = this;
        TreapNode next = this;

        while (next != null && !foundEqual){
            current = next;

            if (value < current.value){
                next = current.left;
                if (next == null){
                    current.insertNodeLeft(new TreapNode(priority, value));
                }
            }
            else if (value > current.value){
                next = current.right;
                if (next == null){
                    current.insertNodeLeft(new TreapNode(priority, value));
                }
            }
            else{
                current.count++;
                foundEqual = true;
            }
        }
    }

    public TreapNode insertNode(TreapNode tree, boolean isMaxHeap){
        TreapNode head = this;

        if (tree == null){}
        else{
            boolean foundPlace = false;
            TreapNode current = this;

            while (!foundPlace){

                if (tree.value < current.value){
                    if (current.getLeft() == null){
                        current.insertNodeLeft(tree);
                        foundPlace = true;
                    }
                    else{
                        current = current.getLeft();
                    }
                }
                else{
                    if (current.getRight() == null){
                        current.insertNodeRight(tree);
                        foundPlace = true;
                    }
                    else{
                        current = current.getRight();
                    }
                }
            }
            
            TreapNode lostNode;
            boolean isValidHeap = false;
            if (isMaxHeap){
                while (!isValidHeap && current != null) {
                    if (current.getLeft() != null && current.getLeft().priority > current.priority){
                        if (current == head){
                            head = current.getLeft();
                        }
                        lostNode = current.rotateRight();
                        current.getParent().insertNode(lostNode, isMaxHeap);
                        current = current.getParent().getParent();
                    }
                    else if (current.getRight() != null && current.getRight().priority > current.priority){
                        if (current == head){
                            head = current.getRight();
                        }
                        lostNode = current.rotateLeft();
                        current.getParent().insertNode(lostNode, isMaxHeap);
                        current = current.getParent().getParent();
                    }
                    else{
                        isValidHeap = true;
                    }
                }
            }
            else{
                while (!isValidHeap && current != null) {
                    if (current.getLeft() != null && current.getLeft().priority < current.priority){
                        if (current == head){
                            head = current.getLeft();
                        }
                        lostNode = current.rotateRight();
                        current.getParent().insertNode(lostNode, isMaxHeap);
                        current = current.getParent().getParent();
                    }
                    else if (current.getRight() != null && current.getRight().priority < current.priority){
                        if (current == head){
                            head = current.getRight();
                        }
                        lostNode = current.rotateLeft();
                        current.getParent().insertNode(lostNode, isMaxHeap);
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

    public void addCount(){
        count++;
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

    private int[] concatinateIntArray(int x[], int y[]){
        int z[] = new int[x.length + y.length];

        for (int i = 0; i < x.length + y.length; i++){
            if (i < x.length){
                z[i] = x[i];
            }
            else{
                z[i] = y[i - x.length];
            }
        }

        return z;
    }

    public int[] inLineTraversal(){
        int s[];
        int c[] = new int[1];

        if (left != null && right != null){
            s = left.inLineTraversal();
            s = concatinateIntArray(s, c);
            s = concatinateIntArray(s, right.inLineTraversal());
        }
        else if (left != null){
            s = left.inLineTraversal();
            s = concatinateIntArray(s, c);
        }
        else if (right != null){
            s = c;
            s = concatinateIntArray(s, right.inLineTraversal());
        }
        else{
            s = new int[0];
        }

        return s;
    }
}