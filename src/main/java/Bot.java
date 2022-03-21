import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException {

        Resources r = new Resources();

        JDA jda = JDABuilder.createLight(r.token,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new MessageListener())
                // .setActivity(Activity.playing("!help"))
                .build();
    }
}