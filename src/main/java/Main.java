import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {

        WindowActions wActs = new WindowActions();

        MainForm mainForm = new MainForm();
        wActs.displayWindow(mainForm);

        // TODO: Mirror the console output to the text box on the GUI
    }
}