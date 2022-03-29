import javax.swing.JFrame;
import java.awt.*;

public class display extends Canvas {
    private static final float SerialNumber = 1.00f;
    public display(int width, int height, String title, app App){
        JFrame frame = new JFrame(title + " " + SerialNumber);
        frame.setPreferredSize(new Dimension(height, width));
        frame.setMaximumSize(new Dimension(height, width));
        frame.setMinimumSize(new Dimension(height, width));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(App);
        App.start();
    }
}
