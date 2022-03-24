import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException, InterruptedException {

        Resources r = new Resources();

        WindowActions wActs = new WindowActions();

//        MainForm mainForm = new MainForm();
//        wActs.displayWindow(mainForm);

        // TODO: The program will not run if the window is initialized

        JDABuilder.createLight(r.token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new MessageListener())
                // .setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS, "this cool cat is worth a chat"))
                .build();
    }
}