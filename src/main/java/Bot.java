import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    private JDA jda;
    private final Resources r = new Resources();

    public Bot(){
        try {
            jda = JDABuilder.createLight(r.token,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.DIRECT_MESSAGES)
                    .addEventListeners(new MessageListener())
                    .setActivity(Activity.watching("this cool cat's worth a chat"))
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public void restart() {
        try {
            jda.shutdown();
            jda = JDABuilder.createLight(r.token,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.DIRECT_MESSAGES)
                    .addEventListeners(new MessageListener())
                    .setActivity(Activity.watching("this cool cat's worth a chat"))
                    .build();
        } catch (LoginException e){
            e.printStackTrace();
        }
    }
}
