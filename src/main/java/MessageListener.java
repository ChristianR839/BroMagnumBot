import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class MessageListener extends ListenerAdapter {

    private final LinkedList<User> userList = new LinkedList<User>();
    private final LinkedList<DMController> dmControllerList = new LinkedList<DMController>();
    private final ImageController img = new ImageController();

    private final Bot bot;
    private final Resources r;

    /**
     * Creates a new MessageListener.
     * @param bot The bot JDA.
     * @param resources The Resources for this bot.
     */
    public MessageListener(Bot bot, Resources resources) {
        this.bot = bot;
        this.r = resources;
    }

    /**
     * Generates a random integer that is within the specified bounds (inclusive).
     * @param min The minimum value of the random integer.
     * @param max The maximum value of the random integer.
     * @return A random integer that is within the specified bounds (inclusive).
     */
    public int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Generates a random date between 1 January 1979 and the present (excluding 29 February).
     * @return A random date  as a formatted string (yyyy/mm/dd).
     */
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

    public String randomize(String url) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return url + "&" + random.nextInt() + "=" + random.nextInt();
    }

    /**
     * The event that handles receiving and responding to messages.
     * Private messages are diverted to DMController for handling.
     * @param event The event.
     */
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        User sender = msg.getAuthor();

        if (!msg.isFromGuild() && msg.getContentRaw().equals("!ping")) {
            MessageChannel channel = event.getChannel();
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
        } else if (!msg.isFromGuild() && msg.getContentRaw().equals("!restart_bro")) {
            System.out.println("[BOT] Bot was remotely restarted by " + sender.getAsTag());
            bot.restart();
        } else if (!msg.isFromGuild() && msg.getContentRaw().equals("ratio") && Objects.equals(sender, event.getJDA().getSelfUser())) {
            MessageChannel channel = event.getChannel();
            msg.addReaction("U+2B06").queue();
        } else if (!msg.isFromGuild() && !Objects.equals(sender, event.getJDA().getSelfUser())) {
            if (!userList.contains(sender)) {
                userList.add(sender);
                DMController newDM = new DMController(msg.getPrivateChannel());
                dmControllerList.add(newDM);
                newDM.setMessage(msg.getContentRaw());
                newDM.conversation();
            } else {
                for (DMController dm : dmControllerList) {
                    if (dm.userMatch(sender)) {
                        dm.setMessage(msg.getContentRaw());
                        dm.conversation();
                        break;
                    }
                }
            }
        } else if (msg.getContentRaw().equals("!!new_feats_message!!") && !Objects.equals(sender, event.getJDA().getSelfUser())) {
            msg.delete().queue();
            MessageChannel channel = event.getChannel();
            channel.sendMessage(
              "hey, @everyone, just want to let you know that i have some new commands!\n"
                + "   • !cat - receive a random image of a cat\n"
                + "   • !meme - receive a random meme from the r/ProgrammerHumor subreddit\n"
                + "   • !comic - receive a random comic of your favorite lasagna-loving monday-hating feline\n"
                + "also, look forward to daily walldles (custom wordles for CS 4401) at noon!").queue();
        } else if (msg.getContentRaw().equals("!comic") && !Objects.equals(sender, event.getJDA().getSelfUser())) {
            String url = "https://www.gocomics.com/garfield/" + comicDate();
            MessageChannel channel = event.getChannel();
            channel.sendMessage(url).queue();
        } else if (msg.getContentRaw().equals("!cat") && !Objects.equals(sender, event.getJDA().getSelfUser())) {
            MessageChannel channel = event.getChannel();
            img.catPic(channel);
        } else if (msg.getContentRaw().equals("!meme") && !Objects.equals(sender, event.getJDA().getSelfUser())) {
            MessageChannel channel = event.getChannel();
            img.meme(channel);
        } else if (msg.getContentRaw().contains("954162794176061541") && !Objects.equals(sender, event.getJDA().getSelfUser())) {
            MessageChannel channel = event.getChannel();

            int response = random(1, 12);

            switch (response) {
                case 1:
                    channel.sendMessage("big fat hairy deal").queue();
                    break;
                case 2:
                    channel.sendMessage("i hate mondays").queue();
                    break;
                case 3:
                    channel.sendMessage("feed me").queue();
                    break;
                case 4:
                    channel.sendMessage("eat your heart out, <@" + msg.getAuthor().getId() + ">").queue();
                    break;
                case 5:
                    channel.sendMessage("i'm not overweight. i'm undertall").queue();
                    break;
                case 6:
                    channel.sendMessage("i'm not known for my compassion").queue();
                    break;
                case 7:
                    channel.sendMessage("i love lasagna").queue();
                    break;
                case 8:
                    channel.sendMessage("it's not that i dislike you, <@" + msg.getAuthor().getId()
                            + ">, i just don't like you near me").queue();
                    break;
                case 9:
                    channel.sendMessage("show me a good mouser and i'll show you a cat with bad breath").queue();
                    break;
                case 10:
                    channel.sendMessage("try '!comic' to see some of my favorites").queue();
                    break;
                case 11:
                    channel.sendMessage("<@" + msg.getAuthor().getId() + ">").queue();
                    channel.sendMessage("<@" + msg.getAuthor().getId() + ">").queue();
                    channel.sendMessage("<@" + msg.getAuthor().getId() + ">").queue();
                    channel.sendMessage("hi").queue();
                    break;
                case 12:
                    channel.sendMessage("would you like to see a '!cat' ?").queue();
                    break;
                case 13:
                    channel.sendMessage("use 'meme!' to see something you might find funny").queue();
                    break;
            }
        }
    }
}
