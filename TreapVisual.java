import javax.swing.JFrame;
import java.awt.Toolkit;

public class TreapVisual{
    public static void main(String[]args){
        Toolkit tk = Toolkit.getDefaultToolkit();
        JFrame window = new JFrame("Treaps");

        window.setBounds(-10, 0, 0, 0);
        window.setSize(tk.getScreenSize());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(new InputHandeling());

        window.setVisible(true);
    }
}