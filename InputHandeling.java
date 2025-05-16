import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class InputHandeling extends JPanel{
    private BinaryTree head;
    private BinaryTree head2;
    private double zoomFactor;
    private double screenX;
    private double screenY;
    private Point previousMousePoint;
    private Treap treap;
    private ButtonBST buttons;
    private Button currentButton;
    private Timer timer = new Timer();
    private boolean isPressedButton;

    final public double ZOOM_FACTOR = 2;
    final public int SCREEN_BOUNDS = 100000;
    final public double MIN_ZOOM = 0.01;
    final public int UI_HEIGHT = 100;

    final public int OPERATION_BUTTON_WIDTH = 30;
    final public int NEXT_BUTTON_X = 500;
    final public int NEXT_BUTTON_Y = 500;
    


    public InputHandeling(){

        //addKeyListener(null);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            @Override
            public void mousePressed(MouseEvent e) {
                currentButton = buttons.search(e.getPoint());
                previousMousePoint = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentButton == buttons.search(e.getPoint()) && currentButton != null){
                    if (head.hasNext()){
                        currentButton.runPress(head);
                    }
                    if (head != null && head.hasCurrent()){
                        //System.out.println(head.getCurrentValue());
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentButton == null){
                    screenX += previousMousePoint.getX() - e.getX();
                    screenY += previousMousePoint.getY() - e.getY();
                    boundScreen();
                    previousMousePoint = e.getPoint();
                    repaint();
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                Button b = buttons.search(e.getPoint());
                if (b == null){
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                else{
                    setCursor(b.getCursor());
                }
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                
                //screenX += (e.getX() + screenX) * Math.pow(ZOOM_FACTOR, -e.getWheelRotation()) / zoomFactor - (e.getX() + screenX) / zoomFactor;
                //screenY = (e.getY() + screenY) * Math.pow(ZOOM_FACTOR, -e.getWheelRotation()) / zoomFactor - e.getY();

                screenX = Math.pow(ZOOM_FACTOR, -e.getWheelRotation()) * (e.getX() + screenX) - e.getX();
                screenY = Math.pow(ZOOM_FACTOR, -e.getWheelRotation()) * (e.getY() + screenY) - e.getY();
                
                zoomFactor *= Math.pow(ZOOM_FACTOR, -e.getWheelRotation());
                if (zoomFactor < MIN_ZOOM){
                    zoomFactor = MIN_ZOOM;
                }
                

                boundScreen();
                repaint();
            }
        });

        Random r = new Random();
        zoomFactor = 1;

        OperationLL operations = new OperationLL();

        head = new BinaryTree(false);
        int x1;
        int x2;
        for (int i = 0; i < 25; i++){
            x1 = r.nextInt(20);
            x2 = r.nextInt(20);
            //System.out.println(x1 + ", " + x2);
            head.insert(x1, x2);
        }

        buttons = new ButtonBST();
        addButtons();

        //head = treapToBinaryTree(treap);
        

        head.updatePosition();
        repaint();
    }

    private void addButtons(){
        try{
            File imageFile = new File("C:\\Users\\nicho\\Desktop\\Executables\\Java Executables\\Extra\\Treap Visual\\Images\\Forward.png");
            BufferedImage image = ImageIO.read(imageFile);
        
            Button button = new Button(new Rectangle(NEXT_BUTTON_X, NEXT_BUTTON_Y, OPERATION_BUTTON_WIDTH, OPERATION_BUTTON_WIDTH), Cursor.HAND_CURSOR) {
                @Override
                public void runPress() {
                }

                @Override
                public void runPress(BinaryTree tree) {
                    tree.nextOperation();
                    repaint();
                }
            };
            button.setVisable(true);
            button.setImage(image);
            buttons.insert(button);

            imageFile = new File("C:\\Users\\nicho\\Desktop\\Executables\\Java Executables\\Extra\\Treap Visual\\Images\\Back.png");
            image = ImageIO.read(imageFile);

            button = new Button(new Rectangle(NEXT_BUTTON_X - 60, NEXT_BUTTON_Y, OPERATION_BUTTON_WIDTH, OPERATION_BUTTON_WIDTH), Cursor.HAND_CURSOR) {
                @Override
                public void runPress() {
                }

                @Override
                public void runPress(BinaryTree tree) {
                    tree.resetTreap();
                    tree.setSearchOperation();

                    while (!tree.isBeforeSearch()){
                        tree.nextOperation();
                    }
                    tree.nextOperation();
                    repaint();
                }
            };
            button.setVisable(true);
            button.setImage(image);
            buttons.insert(button);
        }catch (IOException e){
            
        }
    }

    /*private BinaryTree treapToBinaryTree(Treap treap){
        return new BinaryTree(treapNodeToBinaryTree(treap.getHead()));
    }

    private BinaryTreeNode treapNodeToBinaryTree(TreapNode treap){
        BinaryTreeNode tree = new BinaryTreeNode(treap.priority, treap.value);

        if (treap.getLeft() != null && treap.getRight() != null){
            tree.insertNodeLeft(treapNodeToBinaryTree(treap.getLeft()));
            tree.insertNodeRight(treapNodeToBinaryTree(treap.getRight()));
        }
        else if (treap.getLeft() != null){
            tree.insertNodeLeft(treapNodeToBinaryTree(treap.getLeft()));
        }
        else if (treap.getRight() != null){
            tree.insertNodeRight(treapNodeToBinaryTree(treap.getRight()));
        }

        return tree;
    }*/

    private void boundScreen(){
        if (screenX < -SCREEN_BOUNDS * zoomFactor){
            screenX = -SCREEN_BOUNDS * zoomFactor;
        }
        if (screenX > SCREEN_BOUNDS * zoomFactor){
            screenX = SCREEN_BOUNDS * zoomFactor;
        }
        if (screenY < -SCREEN_BOUNDS * zoomFactor){
            screenY = -SCREEN_BOUNDS * zoomFactor;
        }
        if (screenY > SCREEN_BOUNDS * zoomFactor){
            screenY = SCREEN_BOUNDS * zoomFactor;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

        g.setColor(Color.BLACK);
        head.update(g, screenX, screenY, zoomFactor);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getBounds().x, getBounds().height - UI_HEIGHT, getBounds().width, UI_HEIGHT);
        buttons.update(g);
    }
}
