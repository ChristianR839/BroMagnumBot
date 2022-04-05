import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WordleController extends ListenerAdapter {

    Resources r;

    ZonedDateTime now;

    public WordleController(Resources r) {
        this.r = r;
    }

    public String getWordFromFile() {
        // Read file and return the top-most word that does not have a specific character included
        String fileName = "wordles.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Add that specific character to the word being used so next time it reads the next one
                if (!line.contains("#")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-";
    }

    // come up with the list of words ourselves
    // calendar for dates with shirts

    public String wordleMessage() {
        String wordleCode = getWordFromFile();
        if (!wordleCode.equals("-")) {
            return "https://mywordle.strivemath.com/?word=" + getWordFromFile();
        } else {
            return "ERROR: No more custom Wordles have been provided!";
        }
    }

    public void onReady(@NotNull ReadyEvent event) {

        // Get current time
        now = ZonedDateTime.now(ZoneId.of("America/New_York"));

        // Set time of first Wordle
        // NOTE: Uses 24h clock
        ZonedDateTime nextFirstWordle = now.withHour(13).withMinute(45).withSecond(0);

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
            String message = wordleMessage();
            for (Guild guild : jda.getGuilds()) {
                Objects.requireNonNull(guild.getDefaultChannel()).sendMessage(message).queue();
            }
        },
                initialDelayFirstWordle,
                TimeUnit.DAYS.toSeconds(1),
                TimeUnit.SECONDS);
    }
}
