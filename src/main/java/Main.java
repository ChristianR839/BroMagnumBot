import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws LoginException {

        Resources r = new Resources();

        AtomicBoolean scan = new AtomicBoolean(true);

        // Set to true if the GUI is desired
        boolean gui = false;

        if (gui) {
            WindowActions wActs = new WindowActions();

            MainForm mainForm = new MainForm(r);
            wActs.displayWindow(mainForm);
        } else {
            Scanner scanner = new Scanner(System.in);
            Bot bot = new Bot(r);
            bot.connect();

            System.out.println("[APP] Use 'bm-restart' to disconnect from and reconnect to servers.");
            System.out.println("[APP] Use 'bm-wordle' to create a new input file for wordles.");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    Thread.sleep(200);
                    System.out.println("[APP] Shutting down...");
                    scan.set(false);
                    bot.disconnect();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }));

            while (scan.get()) {
                try {
                    String input = scanner.nextLine();
                    if (input.equals("bm-restart")) {
                        bot.restart();
                    } else if (input.equals("bm-wordle")) {
                        File file = new File(r.wordleFile);
                        try {
                            if (file.createNewFile()) {
                                System.out.println("[APP] New wordle file created: " + r.wordleFile);
                            } else System.out.println("[APP] Wordle file already exists: " + r.wordleFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("[APP] Command '" + input + "' not found");
                    }
                } catch (NoSuchElementException ignore) {}
            }
        }
    }
}
