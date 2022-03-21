import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class HelpMessage {

    JDA jda;

    public HelpMessage(JDA jda) {
        this.jda = jda;
    }

    public void helpMessage(String requester, User user) {
        // User user = jda.retrieveUserById("<ID>").complete(); // User ID
        user.openPrivateChannel()
                .flatMap(channel -> channel.sendMessage("Help has been requested by " + requester))
                .queue();
    }
}
