import javax.security.auth.login.LoginException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws LoginException {

        // Set to true if the GUI is desired
        boolean gui = false;

        if (gui) {
            WindowActions wActs = new WindowActions();

            MainForm mainForm = new MainForm();
            wActs.displayWindow(mainForm);
        } else {
            Bot bot = new Bot();
        }
    }
}