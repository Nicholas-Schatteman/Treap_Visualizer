import java.awt.Rectangle;
import java.time.Clock;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;

public class TextBox extends Button{
    private String text;
    private int sizeLimit;
    private int position;
    private long timeSince;

    final public static int TEXT_DISTANCE = 2;

    public TextBox(Rectangle hitBox){
        super(hitBox, Cursor.TEXT_CURSOR, Button.SW);
        text = "";
        position = -1;
    }

    public TextBox(Rectangle hitBox, int sizeLimit){
        this(hitBox);
        this.sizeLimit = sizeLimit;
    }

    public void addChar(char c){
        if (text.length() < sizeLimit){
            if (text.isEmpty()){
                text += c;
                position++;
            }
            else{
                text = text.substring(0, position) + c + text.substring(position, text.length());
                position++;
            }
        }
    }

    public void removeChar(){
        if (position != 0){
            text = text.substring(0, position - 1) + text.substring(position, text.length());
            position--;
        }
    }

    public void moveLeft(){
        if (0 < position){
            position--;
        }
    }

    public void moveRight(){
        if (position < text.length()){
            position++;
        }
    }

    public void clearText(){
        text = "";
    }

    public String getText() {
        return text;
    }

    @Override
    public TextBox runPress(Point p) {
        int assignedPosition = (int)((p.x - hitBox.x) / (hitBox.height * GraphicsUtility.HEIGHT_TO_CWIDTH) + 1.0 / 2);
        if (assignedPosition > text.length()){
            position = text.length();
        }
        else{
            position = assignedPosition;
        }

        timeSince = Clock.systemUTC().millis();
        return this;
    }

    @Override
    public void offPress() {
        position = -1;
    }

    @Override
    public void runPress(BinaryTree tree) {
        
    }

    @Override
    public void draw(Graphics g, Rectangle placement) {

        g.setColor(Color.GRAY);
        g.drawRect(placement.x, placement.y, placement.width, placement.height);

        g.setColor(Color.BLACK);
        GraphicsUtility.drawString(g, text, placement.x, placement.y - TEXT_DISTANCE / 2, placement.height - TEXT_DISTANCE);

        if (position != -1){
            double colorFactor = 255 * Math.abs(Math.cos((double)(Clock.systemUTC().millis() - timeSince) / 300));
            g.setColor(new Color(0, 0, 0, (int)(colorFactor)));
            int xPos = (int)(placement.x + position * placement.height * GraphicsUtility.HEIGHT_TO_CWIDTH);
            g.drawLine(xPos, placement.y + TEXT_DISTANCE, xPos, placement.y + placement.height - TEXT_DISTANCE);
        }
    }
}