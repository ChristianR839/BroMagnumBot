import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class DMController extends ListenerAdapter {

    private final User user;
    private final PrivateChannel channel;
    private String sent = "";
    private int turns = 0;
    private LinkedList<Integer> flagSent = new LinkedList<Integer>();

    public DMController(PrivateChannel channel) {
        this.user = channel.getUser();
        this.channel = channel;
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
        return sent.toLowerCase().contains(query);
    }

    public void sendMessage(String message){
        try {
            channel.sendTyping().queue();
            Thread.sleep(random(1000, 10000));
            channel.sendMessage(message).queue();
        } catch (ErrorResponseException ignored) {
            System.out.println("ERROR: Could not send message to " + user.getAsTag());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String comicDate() {
        int year = random(1979, LocalDate.now().getYear());
        int month = random(1, 12);
        int day = 0;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = random(1, 31);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = random(1, 30);
                break;
            case 2:
                day = random(1, 28);
                break;
        }

        return (year + "/" + month + "/" + day);
    }

    public int flagNoRepeat() {
        int send = 0;
        do {
            send = random(1, 10);
        } while (flagSent.contains(send));
        flagSent.add(send);
        return send;
    }

    public void conversation() throws InterruptedException {
        if (sentHas("flag")) {
            int send = flagNoRepeat();
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
                case 4:
                    sendMessage("there is no flag here :sweat_smile:");
                    break;
                case 5:
                    sendMessage("youre not getting that info out of me");
                    break;
                case 6:
                    sendMessage("just guess, youll get it right eventually");
                    break;
                case 7:
                    sendMessage("flag? who said anything about a flag?");
                    break;
                case 8:
                    sendMessage("i cant tell you anything");
                    break;
                case 9:
                    sendMessage("im not sure what youre talking about");
                    break;
                case 10:
                    sendMessage("dont ask me about the flag, ask me about comics!");
                    break;
            }
        } else if (sentHas("comic")) {
            String url = "https://www.gocomics.com/garfield/" + comicDate();
            sendMessage(url);
        } else {
            sendMessage("Default response.");
        }
    }
}
