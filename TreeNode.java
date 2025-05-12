public class TreeNode {
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public TreeNode getParent(){
        return parent;
    }

    public TreeNode getLeft(){
        return left;
    }

    public TreeNode getRight(){
        return right;
    }

    public void insertNodeLeft(TreeNode node){
        left = node;
        node.parent = this;
    }

    public void insertNodeRight(TreeNode node){
        right = node;
        node.parent = this;
    }

    public TreeNode rotateLeft(){
        TreeNode lostNode = right.left;

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

    public TreeNode rotateRight(){
        TreeNode lostNode = left.right;

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
