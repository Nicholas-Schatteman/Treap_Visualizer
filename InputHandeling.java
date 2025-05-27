import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class InputHandeling extends JPanel{
    private BinaryTree head;
    private double zoomFactor;
    private double screenX;
    private double screenY;
    private Point previousMousePoint;
    private ButtonSpacePartition buttons;
    private Button previousButton;
    private Button currentButton;
    private DropDownMenu dropSideButton; //Unveils operation insertions when hovered over
    private TextBox currentTextBox;
    private int currentKeyCode;
    private Timer graphicsUpdate;

    final public double ZOOM_FACTOR = 2;
    final public int SCREEN_BOUNDS = 100000;
    final public double MIN_ZOOM = 0.3;//Smaller values means it can zoom in more
    final public int UI_HEIGHT = 100;

    final public int OPERATION_SEPERATION = 50;
    final public int OPERATION_BUTTON_WIDTH = 30;
    final public int NEXT_BUTTON_X = 100;
    final public int NEXT_BUTTON_Y = 65;
    final public int TEXT_BOX_HEIGHT = 15;
    


    public InputHandeling(){

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                currentKeyCode = e.getKeyCode();
                if (currentTextBox != null){
                    if (currentKeyCode == KeyEvent.VK_BACK_SPACE){
                        currentTextBox.removeChar();
                    }
                    else if (currentKeyCode == KeyEvent.VK_LEFT){
                        currentTextBox.moveLeft();
                    }
                    else if (currentKeyCode == KeyEvent.VK_RIGHT){
                        currentTextBox.moveRight();
                    }
                    else if (Character.isDigit(currentKeyCode)){
                        currentTextBox.addChar(e.getKeyChar());
                    }
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });

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
                LinkedList<Button> currentButtons = buttons.search(e.getPoint(), getBounds());
                if (!currentButtons.isEmpty() && buttons.search(e.getPoint(), getBounds()).getLast().isVisable()){
                    currentButton = buttons.search(e.getPoint(), getBounds()).getLast();
                }
                else{
                    currentButton = null;
                }
                previousMousePoint = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (previousButton != null){
                    previousButton.offPress();
                }

                LinkedList<Button> currentButtons = buttons.search(e.getPoint(), getBounds());

                if (!currentButtons.isEmpty() && buttons.search(e.getPoint(), getBounds()).getLast().isVisable() && currentButton == buttons.search(e.getPoint(), getBounds()).getLast()){
                    currentTextBox = (TextBox)currentButton.runPress(e.getPoint());
                    currentButton.runPress(head);
                    previousButton = currentButton;
                }
                else{
                    currentTextBox = null;
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
                //Sets cursor if over button based on what button shape is specified
                LinkedList<Button> b = buttons.search(e.getPoint(), getBounds());
                if (b.isEmpty() || !b.getLast().isVisable()){
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                else{
                    setCursor(b.getLast().getCursor());
                }

                //If over drop side button unveil the buttons inside
                dropSideButton.isOver(e.getPoint(), getBounds());
            }
        });

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (zoomFactor < 1 / MIN_ZOOM || Math.pow(ZOOM_FACTOR, -e.getWheelRotation()) < 1){
                    screenX = Math.pow(ZOOM_FACTOR, -e.getWheelRotation()) * (e.getX() + screenX) - e.getX();
                    screenY = Math.pow(ZOOM_FACTOR, -e.getWheelRotation()) * (e.getY() + screenY) - e.getY();

                    zoomFactor *= Math.pow(ZOOM_FACTOR, -e.getWheelRotation());
                }

                boundScreen();
                repaint();
            }
        });

        graphicsUpdate = new Timer(24, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        graphicsUpdate.setRepeats(true);
        graphicsUpdate.start();

        Random r = new Random();
        zoomFactor = 1;

        head = new BinaryTree(false);
        int x1;
        int x2;
        for (int i = 0; i < r.nextInt(2) + 8; i++){
            x1 = r.nextInt(20);
            x2 = r.nextInt(20);
            head.insert(x1, x2);
        }

        buttons = addButtons();

        head.updatePosition();
        repaint();
    }

    private ButtonSpacePartition addButtons(){
        try{
            LinkedList<Button> buttonsToAdd = new LinkedList<>();
            File imageFile = new File("Images\\Back.png");
            BufferedImage image = ImageIO.read(imageFile);

            Button hudBackDrop = new Button(new Rectangle(0, UI_HEIGHT, 10000, UI_HEIGHT), Cursor.DEFAULT_CURSOR, Button.SW){
                @Override
                public void offPress() {}
                @Override
                public void runPress(BinaryTree tree) {}
                @Override
                public Button runPress(Point p) {return null;}

                @Override
                protected void draw(Graphics g, Rectangle placement) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(placement.x, placement.y, placement.width, placement.height);
                }
            };
            hudBackDrop.setVisable(true);
            buttonsToAdd.insert(hudBackDrop);

            ImageButton button = new ImageButton(new Rectangle(NEXT_BUTTON_X + OPERATION_SEPERATION, NEXT_BUTTON_Y, OPERATION_BUTTON_WIDTH, OPERATION_BUTTON_WIDTH), Cursor.HAND_CURSOR) {
                @Override
                public void offPress() {}

                @Override
                public Button runPress(Point p) {
                    return null;
                }

                @Override
                public void runPress(BinaryTree tree) {
                    if (tree.hasPrevious()){
                        tree.setSearchOperation();
                        tree.resetTreap();

                        while (!tree.isBeforeSearch()){
                            tree.nextOperation();
                        }
                        repaint();
                    }
                }
            };
            button.setVisable(true);
            button.setImage(image);
            buttonsToAdd.insert(button);

            imageFile = new File("Images\\Forward.png");
            image = ImageIO.read(imageFile);
        
            button = new ImageButton(new Rectangle(NEXT_BUTTON_X, NEXT_BUTTON_Y, OPERATION_BUTTON_WIDTH, OPERATION_BUTTON_WIDTH), Cursor.HAND_CURSOR) {
                @Override
                public void offPress() {}

                @Override
                public Button runPress(Point p) {
                    return null;
                }

                @Override
                public void runPress(BinaryTree tree) {
                    if (tree.hasNext()){
                        tree.nextOperation();
                        repaint();
                    }
                }
            };
            button.setVisable(true);
            button.setImage(image);
            buttonsToAdd.insert(button);

            dropSideButton = new DropDownMenu(new Rectangle(45, 750, 30, 500), new Rectangle(45, 750, 200, 500)) {
                final public int VERTICAL_BORDOR_DISTANCE = 5;
                final public int HORIZONTAL_BORDOR_DISTANCE = 5;

                @Override
                protected void draw(Graphics g, Rectangle placement) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(placement.x, placement.y, placement.width, placement.height);
                    g.setColor(Color.BLACK);
                    int xPoints[] = {placement.x + HORIZONTAL_BORDOR_DISTANCE, placement.x + HORIZONTAL_BORDOR_DISTANCE, placement.x + placement.width - HORIZONTAL_BORDOR_DISTANCE};
                    int yPoints[] = {placement.y + VERTICAL_BORDOR_DISTANCE, placement.y + placement.height - VERTICAL_BORDOR_DISTANCE, placement.y + placement.height / 2};
                    g.fillPolygon(xPoints, yPoints, 3);
                }
            };
            dropSideButton.setVisable(true);

            int textBoxWidth = (int)(GraphicsUtility.HEIGHT_TO_CWIDTH * 3 * TEXT_BOX_HEIGHT);

            TextBox textBox1 = new TextBox(new Rectangle(100, 700, textBoxWidth, TEXT_BOX_HEIGHT), 3);
            dropSideButton.insert(textBox1);
            buttonsToAdd.insert(textBox1);

            TextBox textBox2 = new TextBox(new Rectangle(100, 700 - TEXT_BOX_HEIGHT - 2, textBoxWidth, TEXT_BOX_HEIGHT), 3);

            dropSideButton.insert(textBox2);
            buttonsToAdd.insert(textBox2);

            SubmitTextButton submitionButton = new SubmitTextButton(new Rectangle(230, 100, 20, 20), textBox1, textBox2, "Insert") {
                @Override
                public void action(BinaryTree tree, int x, int y) {
                    tree.insert(x, y);
                }
            };
            dropSideButton.insert(submitionButton);
            buttonsToAdd.insert(submitionButton);

            return new ButtonSpacePartition(buttonsToAdd, 0);
        }catch (IOException e){
            return null;
        }
    }

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
        if (head.isMoving()){
            head.updatePosition();
        }
        head.update(g, screenX, screenY, zoomFactor);

        buttons.update(g, getBounds());
        dropSideButton.update(g, getBounds());

        
    }
}
