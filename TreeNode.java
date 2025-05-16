abstract public class TreeNode {
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

    public TreeNode rotateNodeLeft(TreeNode node){
        TreeNode lostNode = right.left;

        if (parent == null){
            node.right.parent = null;
            node.right.insertNodeLeft(this);
            node.right = null;
        }
        else if (parent.left == this){
            node.parent.insertNodeLeft(right);
            node.right.insertNodeLeft(this);
            node.right = null;
        }
        else{
            node.parent.insertNodeRight(right);
            node.right.insertNodeLeft(this);
            node.right = null;
        }

        return lostNode;
    }

    public TreeNode rotateRight(TreeNode node){
        TreeNode lostNode = node.left.right;

        if (parent == null){
            node.left.parent = null;
            node.left.insertNodeRight(this);
            node.left = null;
        }
        else if (parent.left == this){
            node.parent.insertNodeLeft(left);
            node.left.insertNodeRight(this);
            node.left = null;
        }
        else{
            node.parent.insertNodeRight(left);
            node.left.insertNodeRight(this);
            node.left = null;
        }

        return lostNode;
    }
}
