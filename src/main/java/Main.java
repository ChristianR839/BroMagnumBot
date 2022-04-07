import javax.security.auth.login.LoginException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws LoginException {

        // Set to true if the GUI is desired
        boolean gui = false;

        if (gui) {
            WindowActions wActs = new WindowActions();

            MainForm mainForm = new MainForm();
            wActs.displayWindow(mainForm);
        } else {
            Scanner scanner = new Scanner(System.in);
            Bot bot = new Bot();
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("bm-stop")) {
                    bot.disconnect();
                    System.exit(0);
                } else {
                    System.out.println("Command '" + input + "' not found");
                }
            }
        }
    }
}

// Text