import javax.swing.*;
import java.awt.*;

public class WindowActions {

    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void centreWindow(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public void displayWindow(JFrame frame) {
        frame.setTitle("Bro Magnum");
        frame.setResizable(false);
        setLookAndFeel();
        frame.setSize(800, 300);
        // frame.pack();
        centreWindow(frame);
        frame.setVisible(true);
    }
}
