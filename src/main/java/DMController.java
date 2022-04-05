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
    private final LinkedList<Integer> flagSent = new LinkedList<Integer>();
    private final LinkedList<Integer> genResponse = new LinkedList<Integer>();
    private boolean flagGot = false;
    private final Resources r = new Resources();

    public DMController(PrivateChannel channel) {
        this.user = channel.getUser();
        this.channel = channel;
        System.out.println("Communication initiated with " + user.getAsTag());
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
        return (sent.toLowerCase().contains(query) || sent.toLowerCase().contains(query + "s"));
    }

    public void sendMessage(String message){
        try {
            channel.sendTyping().queue();
            Thread.sleep(random(1000, 6000));
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

    public int flagResponseNum() {
        int send = 0;
        do {
            // System.out.println("IN FLAG LOOP");
            send = random(1, 10);
        } while (flagSent.contains(send));
        // System.out.println("OUT OF FLAG LOOP");
        flagSent.add(send);
        return send;
    }

    public int genResponseNum() {
        int send = 0;
        do {
            // System.out.println("IN GENERAL LOOP");
            if (genResponse.size() >= 10) {
                genResponse.clear();
            }
            send = random(1, 10);
        } while (genResponse.contains(send));
        // System.out.println("OUT OF GENERAL LOOP");
        genResponse.add(send);
        return send;
    }

    public boolean flagCheck() {
        int rVal = random(3, 6);
        if (flagGot) {
            return false;
        } else return (rVal <= turns);
    }

    public void conversation() throws InterruptedException {

        /*
         * Steps:
         * 1. Add 1 to turn count
         * 2. Roll to see if flag is obtained
         *      Random num btw 1 and X where X turns is a guaranteed flag
         * 3. If a keyword was used, perform that action
         *      Current keywords: flag, comic
         *      Future keywords: ?
         * 4. If no keyword was used: base dialogue about needing a question answered (hardest part)
         */

        turns += 1;

        if (!flagCheck()) {
            if (sentHas("flag") && !flagGot) {
                int send = flagResponseNum();
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
            } else if (sentHas("flag") && flagGot) {
                sendMessage("You got the flag, right? Ask me about something else. Maybe comics?");
            } else if (sentHas("comic")) {
                String url = "https://www.gocomics.com/garfield/" + comicDate();
                sendMessage("here's a comic for you. it's one of my favorites\n" + url);
            } else if (sentHas("garfield")) {
                String url = "https://www.gocomics.com/garfield/" + comicDate();
                sendMessage(url);
            } else {
                int send = genResponseNum();
                switch (send) {
                    case 1:
                        sendMessage("help! professor walls has trapped me in the internet!");
                        break;
                    case 2:
                        sendMessage("ratio");
                        break;
                    case 3:
                        sendMessage("can you help me study for this class?");
                        break;
                    case 4:
                        sendMessage("do you know morse code?");
                        break;
                    case 5:
                        sendMessage("i miss smartworld :sob:");
                        break;
                    case 6:
                        sendMessage("can you swipe me into daka?");
                        break;
                    case 7:
                        sendMessage("omG i lIteRalLY cAnNot FiGure oUt whAT To dO wIth tHiS boT");
                        break;
                    case 8:
                        sendMessage("i hate mondays (i am garfield, voiced by bill murray)");
                        break;
                    case 9:
                        sendMessage("today, i kicked odie off the table");
                        break;
                    case 10:
                        sendMessage("fun fact: odie was first owned by jon's friend lyman. i wish he kept him");
                        break;
                }
            }
        } else {
            sendMessage("I give up, you're impossible to talk to. The flag is: " + r.flag);
            flagGot = true;
            System.out.println("Flag granted to " + user.getAsTag());
        }
    }
}
