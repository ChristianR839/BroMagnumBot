import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    private JDA jda;
    private Resources r;

    public Bot(Resources resources) {
        r = resources;
    }

    /**
     * Connects the bot to the Discord servers.
     */
    public void connect() {
        try {
            jda = JDABuilder.createLight(r.token,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.DIRECT_MESSAGES)
                    .addEventListeners(new MessageListener(this, r), new WordleController(r)) // new WordleController()
                    .setActivity(Activity.watching("this cool cat's worth a chat"))
                    .build();
            System.out.println("[BOT] Bot connected to servers.");
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects the bot from the Discord servers.
     */
    public void disconnect() {
        jda.shutdown();
        System.out.println("[BOT] Bot disconnected from servers.");
    }

    /**
     * Restarts the bot (disconnects, then reconnects).
     */
    public void restart() {
        disconnect();
        connect();
    }
}
