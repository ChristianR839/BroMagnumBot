import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.LinkedList;
import java.util.Objects;

public class MessageListener extends ListenerAdapter {

    private final LinkedList<User> userList = new LinkedList<User>();
    private final LinkedList<DMController> dmControllerList = new LinkedList<DMController>();

    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        User sender = msg.getAuthor();

        if (msg.getContentRaw().equals("!ping")) {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
        }

        if (msg.getContentRaw().equals("ratio") && Objects.equals(sender, event.getJDA().getSelfUser())) {
            MessageChannel channel = event.getChannel();
            msg.addReaction("U+2B06").queue();
        }

        if (!msg.isFromGuild() && !Objects.equals(sender, event.getJDA().getSelfUser())) {
            if (!userList.contains(sender)) {
                userList.add(sender);
                DMController newDM = new DMController(msg.getPrivateChannel());
                dmControllerList.add(newDM);
                newDM.setMessage(msg.getContentRaw());
                try {
                    newDM.conversation();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                for (DMController dm : dmControllerList) {
                    if (dm.userMatch(sender)) {
                        dm.setMessage(msg.getContentRaw());
                        try {
                            dm.conversation();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }
}
