import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

abstract public class SubmitTextButton extends Button{
    protected TextBox text[];
    private String display;

    public SubmitTextButton(Rectangle hitBox, TextBox text1, TextBox text2, String display){
        super(hitBox, Cursor.HAND_CURSOR, Button.SW);
        text = new TextBox[2];
        text[0] = text1;
        text[1] = text2;
        this.display = display;
    }

    @Override
    public void offPress() {}

    @Override
    public Button runPress(Point p) {
        return null;
    }

    @Override
    public void runPress(BinaryTree tree) {
        if (!text[0].getText().isEmpty() && !text[1].getText().isEmpty()){
            action(tree, Integer.parseInt(text[0].getText()), Integer.parseInt(text[1].getText()));
            text[0].clearText();
            text[1].clearText();
        }
    }

    @Override
    public void draw(Graphics g, Rectangle placement) {
        g.setColor(Color.BLACK);
        g.fillRect(placement.x, placement.y, placement.width, placement.height);

        g.setColor(Color.RED);
        GraphicsUtility.drawString(g, display, placement.x, placement.y, placement.height);
    }

    abstract public void action(BinaryTree tree, int x, int y);
}
