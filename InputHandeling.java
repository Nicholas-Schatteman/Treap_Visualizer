import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;
import java.util.Timer;

import javax.swing.JPanel;

public class InputHandeling extends JPanel{
    private BinaryTree head;
    private BinaryTree head2;
    private double zoomFactor;
    private double screenX;
    private double screenY;
    private Point previousMousePoint;
    private Timer timer = new Timer();

    final public double ZOOM_FACTOR = 2;
    final public int SCREEN_BOUNDS = 100000;
    final public double MIN_ZOOM = 0.01;


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
                previousMousePoint = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                screenX += previousMousePoint.getX() - e.getX();
                screenY += previousMousePoint.getY() - e.getY();
                boundScreen();
                previousMousePoint = e.getPoint();
                repaint();
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                
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
        /*head = new BinaryTree(0, 0);
        head.insertLeft(1, 1);
        BinaryTree current = head.getLeft();
        current.insertLeft(2, 2);
        current.insertRight(r.nextInt(10), r.nextInt(10));
        current.getRight().insertRight(r.nextInt(10), r.nextInt(10));
        current = current.getLeft();
        current.insertLeft(3, 3);
        current.insertRight(4, 4);
        head.updatePosition();
        System.out.println(head.getHeight() + " " + head.getLeft().getHeight() + " " + head.getLeft().getLeft().getHeight());*/
        /*head2 = new BinaryTree(10, 4);
        head2.x = 500;
        head2.y = 500;*/

        Treap t = new Treap(false);
        int x1;
        int x2;
        for (int i = 0; i < 25; i++){
            x1 = r.nextInt(20);
            x2 = r.nextInt(20);
            //System.out.println(x1 + ", " + x2);
            t.insert(x1, x2);
        }

        head = treapToBinaryTree(t);
        head.updatePosition();
        repaint();
    }

    private BinaryTree treapToBinaryTree(Treap treap){
        return treapNodeToBinaryTree(treap.getHead());
    }

    private BinaryTree treapNodeToBinaryTree(TreapNode treap){
        BinaryTree tree = new BinaryTree(treap.priority, treap.value);

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
    }

    private void boundScreen(){
        if (screenX > SCREEN_BOUNDS * zoomFactor){
            screenX = SCREEN_BOUNDS * zoomFactor;
        }
        else if (screenX < -SCREEN_BOUNDS * zoomFactor){
            screenX = -SCREEN_BOUNDS * zoomFactor;
        }
        if (screenY > SCREEN_BOUNDS * zoomFactor){
            screenY = SCREEN_BOUNDS * zoomFactor;
        }
        else if (screenY < -SCREEN_BOUNDS * zoomFactor){
            screenY = -SCREEN_BOUNDS * zoomFactor;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

        g.setColor(Color.BLACK);
        head.update(g, zoomFactor, screenX, screenY);
    }
}
