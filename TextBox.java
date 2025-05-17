import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;

public class TextBox extends Button{
    private String text;
    private int sizeLimit;
    private int position;

    final public static int TEXT_DISTANCE = 2;

    public TextBox(Rectangle hitBox){
        super(hitBox, Cursor.TEXT_CURSOR);
        text = "";
    }

    @Deprecated
    public TextBox(Rectangle hitBox, Cursor overCursor){
        super(hitBox, overCursor);
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
        if (!text.isEmpty()){
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

    public String getText() {
        return text;
    }

    @Override
    public TextBox runPress() {
        return this;
    }

    @Override
    public void runPress(BinaryTree tree) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);

        g.setColor(Color.BLACK);
        GraphicsUtility.drawString(g, text, hitBox.x, hitBox.y + hitBox.height - TEXT_DISTANCE / 2, hitBox.height - TEXT_DISTANCE);
    }
}