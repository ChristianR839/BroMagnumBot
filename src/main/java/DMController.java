import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.ThreadLocalRandom;

public class DMController extends ListenerAdapter {

    private final User user;
    private final PrivateChannel channel;
    private String sent = "";
    private int turns = 0;

    public DMController(PrivateChannel channel, String startMessage) {
        this.user = channel.getUser();
        this.channel = channel;
        sendMessage(startMessage);
    }

    public boolean userMatch(User user) {
        return this.user.equals(user);
    }

    public void setMessage(String message) {
        sent = message;
    }

    public int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public boolean sentHas(String query) {
        return sent.toLowerCase().contains("flag");
    }

    public void sendMessage(String message){
        channel.sendTyping().queue();
        try {
            Thread.sleep(random(1000, 10000));
            channel.sendMessage(message).queue();
        } catch (ErrorResponseException ignored) {
            System.out.println("ERROR: Could not send message to " + user.getAsTag());
            ignored.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void conversation() throws InterruptedException {
        if (sentHas("flag")) {
            int send = random(1, 3);
            switch (send) {
                case 1:
                    sendMessage("lets not talk about that right now");
                    break;
                case 2:
                    sendMessage("thats not important");
                    break;
                case 3:
                    sendMessage("not now, ok?");
                    break;
                default:
                    sendMessage("Default response; flag");
            }
        } else {
            sendMessage("Default response.");
        }
    }
}
