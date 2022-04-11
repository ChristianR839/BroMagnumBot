import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class WordleController extends ListenerAdapter {

    private static final String fileName = "wordles.txt";

    private static Resources r = null;

    private static final String completeSymbol = "#";
    private ZonedDateTime now;

    /**
     * Creates a new WordleController
     * @param resources The Resources for this bot.
     */
    public WordleController(Resources resources) {
        r = resources;
    }

    /**
     * Updates wordles.txt file to show that the wordle used has been posted.
     * Meant to append WordleController#completeSymbol to the beginning of the line with the used word.
     * An unintended (however, beneficial) affect of this causes duplicate words to also be marked as completed.
     * @param replaceWith What to replace
     * @param type What to replace it with
     */
    public static void replaceSelected(String replaceWith, String type) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(r.wordleFile));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            String inputStr = inputBuffer.toString();

            // System.out.println(inputStr); // display the original file for debugging

            // logic to replace lines in the string (could use regex here to be generic)
            inputStr = inputStr.replace(replaceWith, completeSymbol + replaceWith);

            // display the new file for debugging
            // System.out.println("----------------------------------\n" + inputStr);

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(r.wordleFile);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("[APP] Problem reading file.");
        }
    }

    /**
     * Reads the input file and gets the word to be used for the current day's wordle.
     * @return A string containing the word to be used.
     */
    public String getWordFromFile() {
        // Read file and return the top-most word that does not have a specific character included

        File file = new File(r.wordleFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(r.wordleFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Add that specific character to the word being used so next time it reads the next one
                if (!line.contains(completeSymbol)) {
                    br.close();
                    return line;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-";
    }

    /**
     * Generates a URL for the custom wordle.
     * @return A string containing the URL.
     */
    public String wordleURL() {
        String wordleCode = getWordFromFile();
        if (!wordleCode.equals("-")) {
            replaceSelected(wordleCode, completeSymbol + wordleCode);
            return "https://mywordle.strivemath.com/?word=" + wordleCode;
        } else {
            System.out.println("[APP] ERROR: No more custom Wordles have been provided!");
            return "-";
        }
    }

    /**
     * Randomizes the end of the cat image API URL.
     * @param url The URL to append the randomness to.
     * @return The randomized URL.
     */
    public String randomize(String url) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return url + "&" + random.nextInt() + "=" + random.nextInt();
    }

    /**
     * The event that handles sending a custom wordle daily at a specified time.
     * @param event The event.
     */
    public void onReady(@NotNull ReadyEvent event) {

        // Get current time
        now = ZonedDateTime.now(ZoneId.of("America/New_York"));

        // Set time of first Wordle
        // NOTE: Uses 24h clock
        ZonedDateTime nextFirstWordle = now.withHour(12).withMinute(0).withSecond(0); // <-- Set time of day for wordle

        // If it's past the time, schedule the Wordle for the next day
        if (now.compareTo(nextFirstWordle) > 0) {
            nextFirstWordle = nextFirstWordle.plusDays(1);
        }

        // Duration between now and the beginning of the next first Wordle
        Duration durationUntilFirstWordle = Duration.between(now, nextFirstWordle);
        // In seconds
        long initialDelayFirstWordle = durationUntilFirstWordle.getSeconds();

        // Schedules Wordle at a fixed rate of one day
        ScheduledExecutorService schedulerFirstWordle = Executors.newScheduledThreadPool(1);
        schedulerFirstWordle.scheduleAtFixedRate(() -> {
            // Send a message
            JDA jda = event.getJDA();
            String wordleLink = wordleURL();
            for (Guild guild : jda.getGuilds()) {
                EmbedBuilder wordle = new EmbedBuilder();
                wordle.setColor(0xffa502);
                wordle.setTitle("Walldle " + now.format(DateTimeFormatter.ISO_LOCAL_DATE), wordleLink);
                wordle.setImage(randomize("http://thecatapi.com/api/images/get?format=src&type=png"));
                Objects.requireNonNull(guild.getDefaultChannel()).sendMessageEmbeds(wordle.build()).queue();
                System.out.println("[BOT] Wordle " + now.format(DateTimeFormatter.ISO_LOCAL_DATE) + " posted in " +
                        guild.getName() + " / " + guild.getDefaultChannel().getName() + " -> " + wordleLink);
            }
        },
                initialDelayFirstWordle,
                TimeUnit.DAYS.toSeconds(1), // <-- Set time between wordles
                TimeUnit.SECONDS);
    }
}
