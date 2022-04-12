import javax.swing.*;
import java.awt.event.*;
import java.io.PrintStream;

public class MainForm extends JFrame {
    private JPanel contentPane;
    private JButton restart;
    private JButton buttonCancel;
    private JTextArea consoleOut;
    private JScrollPane scrArea;

    private Bot bot;
    private Resources r;

    /**
     * Creates a new MainForm.
     */
    public MainForm(Resources resources){
        r = resources;
        setContentPane(contentPane);
        getRootPane().setDefaultButton(restart);

        scrArea.getVerticalScrollBar().setValue(scrArea.getVerticalScrollBar().getMaximum());

        PrintStream printStream = new PrintStream(new ConsoleOut(consoleOut));
        System.setOut(printStream);
        System.setErr(printStream);

        bot = new Bot(r);
        bot.connect();

        restart.addActionListener(new ActionListener() {
            /**
             * The event that handles when 'Restart' is selected.
             * @param e The event.
             */
            public void actionPerformed(ActionEvent e) {
                onRestart();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            /**
             * The event that handles when 'Close' is selected.
             * @param e The event.
             */
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            /**
             * The event that handles when the window is closing.
             * @param e The event.
             */
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            /**
             * The event that handles when 'ESCAPE' is used.
             * @param e The event.
             */
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Restarts the bot.
     */
    private void onRestart() {
        bot.restart();
    }

    /**
     * Disconnects the bot from the servers and shuts down the program.
     */
    private void onClose() {
        System.out.println("[APP] Shutting down...");
        bot.disconnect();
        dispose();
        System.exit(0);
    }
}
