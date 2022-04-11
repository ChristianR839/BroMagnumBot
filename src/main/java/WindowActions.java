import javax.swing.*;
import java.awt.*;

public class WindowActions {

    /**
     * Sets the look and feel of the application GUI to the system default.
     */
    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Centres an application window on the screen.
     * @param frame The application window to centre.
     */
    public static void centreWindow(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * Displays an application window with pre-determined settings.
     * @param frame The application window to display.
     */
    public void displayWindow(JFrame frame) {
        frame.setTitle("Bro Magnum");
        frame.setResizable(false);
        setLookAndFeel();
        frame.setSize(1000, 300);
        // frame.pack();
        centreWindow(frame);
        frame.setVisible(true);
    }
}
