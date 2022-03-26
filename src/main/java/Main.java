import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {

        WindowActions wActs = new WindowActions();

        MainForm mainForm = new MainForm();
        wActs.displayWindow(mainForm);
    }
}