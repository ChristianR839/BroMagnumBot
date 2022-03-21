import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DMController extends ListenerAdapter {

    private User user;
    private PrivateChannel channel;

    public DMController(PrivateChannel channel, String startMessage) {
        this.user = channel.getUser();
        this.channel = channel;
        SendMessage(startMessage);
    }

    public boolean SendMessage(String message){
        try {
            channel.sendMessage(message).queue();
        }catch(ErrorResponseException ignored) {
            System.out.println("error");
        }

        return true;
    }

}
