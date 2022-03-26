import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    private JDA jda;
    private Resources r = new Resources();

    public Bot() throws LoginException {
        jda = JDABuilder.createLight(r.token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new MessageListener())
                // .setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS, "this cool cat is worth a chat"))
                .build();
    }

    public boolean restart() {
        try {
            jda.shutdown();
            jda = JDABuilder.createLight(r.token,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.DIRECT_MESSAGES)
                    .addEventListeners(new MessageListener())
                    // .setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS, "this cool cat is worth a chat"))
                    .build();
        } catch (LoginException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
