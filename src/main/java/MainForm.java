import javax.security.auth.login.LoginException;
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

    public MainForm() throws LoginException {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(restart);

        scrArea.getVerticalScrollBar().setValue(scrArea.getVerticalScrollBar().getMaximum());

        PrintStream printStream = new PrintStream(new ConsoleOut(consoleOut));
        // System.setOut(printStream);
        // System.setErr(printStream);

        bot = new Bot();

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        bot.restart();
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
