import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BinaryTree {
    private BinaryTree parent;
    private BinaryTree left;
    private BinaryTree right;
    private int priority;
    private int value;
    private Color color;
    public int x;
    public int y;

    final public int CIRCLE_SIZE = 100;
    final public int CIRCLE_OFFSET = 7;
    final public int CIRCLE_MIDDLE = 3;
    final public int CIRCLE_MIDDLE_TOFFSET = 2;
    final public double NODE_WIDTH_SEPERATION = 70;
    final public double NODE_HEIGHT_SEPERATION = 140;


    public BinaryTree(int priority, int value){
        this.priority = priority;
        this.value = value;
        color = Color.BLACK;

        x = 0;
        y = 0;
    }

    public BinaryTree getLeft(){
        return left;
    }

    public BinaryTree getRight(){
        return right;
    }

    public void insertLeft(int priority, int value){
        insertNodeLeft(new BinaryTree(priority, value));
    }

    public void insertNodeLeft(BinaryTree node){
        left = node;
        node.parent = this;
    }

    public void insertRight(int priority, int value){
        insertNodeRight(new BinaryTree(priority, value));
    }

    public void insertNodeRight(BinaryTree node){
        right = node;
        node.parent = this;
    }

    public void addStep(){

    }

    public void goStart(){

    }

    public void stepForward(){

    }

    public void stepBack(){

    }

    public void goEnd(){

    }

    public BinaryTree rotateLeft(){
        BinaryTree lostNode = right.left;

        if (parent.left == this){
            parent.insertNodeLeft(right);
            right.insertNodeLeft(this);
        }
        else{
            parent.insertNodeRight(right);
            right.insertNodeLeft(this);
        }

        return lostNode;
    }

    public BinaryTree rotateRight(){
        BinaryTree lostNode = left.right;

        if (parent.left == this){
            parent.insertNodeLeft(left);
            left.insertNodeRight(this);
        }
        else{
            parent.insertNodeRight(left);
            right.insertNodeRight(this);
        }

        return lostNode;
    }

    public int getHeight(){
        if (left == null && right == null){
            return 0;
        }
        else if (left == null){
            return 1 + right.getHeight();
        }
        else if (right == null){
            return 1 + left.getHeight();
        }
        else{
            return 1 + Math.max(left.getHeight(), right.getHeight());
        }
    }

    public int getDepth(){
        BinaryTree temp = this;
        int count = 0;
        while (temp.parent != null){
            temp = temp.parent;
            count++;
        }
        return count;
    }

    public void updatePosition(){
        if (left != null && right != null){
            int newLeftX = (int)(x - NODE_WIDTH_SEPERATION * Math.pow(2, Math.max(left.getHeight(), right.getHeight())));
            int newLeftY = (int)(y + NODE_HEIGHT_SEPERATION);
            int newRightX = (int)(x + NODE_WIDTH_SEPERATION * Math.pow(2, Math.max(left.getHeight(), right.getHeight())));
            int newRightY = (int)(y + NODE_HEIGHT_SEPERATION);

            left.x = newLeftX;
            left.y = newLeftY;
            left.updatePosition();

            right.x = newRightX;
            right.y = newRightY;
            right.updatePosition();
        }
        else if (left != null){
            int newLeftX = (int)(x - NODE_WIDTH_SEPERATION * Math.pow(2, left.getHeight()));
            int newLeftY = (int)(y + NODE_HEIGHT_SEPERATION);

            left.x = newLeftX;
            left.y = newLeftY;
            left.updatePosition();
        }
        else if (right != null){
            int newRightX = (int)(x + NODE_WIDTH_SEPERATION * Math.pow(2, right.getHeight()));
            int newRightY = (int)(y + NODE_HEIGHT_SEPERATION);

            right.x = newRightX;
            right.y = newRightY;
            right.updatePosition();
        }
    }

    private double findTextHeight(int length){
        int radius = (CIRCLE_SIZE - CIRCLE_OFFSET) / 2;

        double a = Math.pow(length * GraphicsUtility.HEIGHT_TO_CWIDTH / 2, 2) + 1;
        int middleDistance = CIRCLE_MIDDLE + CIRCLE_MIDDLE_TOFFSET;
        double c = Math.pow(middleDistance / 2, 2) - Math.pow(radius, 2);

        return (-middleDistance + Math.sqrt(Math.pow(middleDistance, 2) - 4 * a * c)) / (2 * a);
    }

    public void update(Graphics g, double zoomFactor, double screenX, double screenY){
        if (left != null){
            GraphicsUtility.drawLine(g, x, y, left.x, left.y, screenX, screenY, zoomFactor);
            left.update(g, zoomFactor, screenX, screenY);
        }

        if (right != null){
            GraphicsUtility.drawLine(g, x, y, right.x, right.y, screenX, screenY, zoomFactor);
            right.update(g, zoomFactor, screenX, screenY);
        }

        g.setColor(color);
        GraphicsUtility.fillCircle(g, x, y, CIRCLE_SIZE, screenX, screenY, zoomFactor);

        g.setColor(Color.WHITE);
        GraphicsUtility.fillCircle(g, x, y, CIRCLE_SIZE - CIRCLE_OFFSET, screenX, screenY, zoomFactor);

        g.setColor(Color.BLACK);
        double textHeight = findTextHeight(("" + priority).length());
        GraphicsUtility.drawStringXCentered(g, "" + priority, x, y - CIRCLE_MIDDLE - CIRCLE_MIDDLE_TOFFSET, textHeight, screenX, screenY, zoomFactor);

        textHeight = findTextHeight(("" + value).length());
        GraphicsUtility.drawStringXCentered(g, "" + value, x, y + textHeight + CIRCLE_MIDDLE + CIRCLE_MIDDLE_TOFFSET, textHeight, screenX, screenY, zoomFactor);

        GraphicsUtility.fillRectangleC(g, x, y, Math.sqrt(4 * Math.pow(CIRCLE_SIZE / 2, 2) - Math.pow(CIRCLE_MIDDLE, 2)) - 4, CIRCLE_MIDDLE, screenX, screenY, zoomFactor);
    }
}
