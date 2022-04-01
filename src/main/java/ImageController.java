import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

public class ImageController {

    public String randomize(String url) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return url + "&" + random.nextInt() + "=" + random.nextInt();
    }

    public void catPic(MessageChannel channel) {
        EmbedBuilder cat = new EmbedBuilder();
        cat.setColor(0xffa502);
        cat.setImage(randomize("http://thecatapi.com/api/images/get?format=src&type=png"));
        channel.sendMessageEmbeds(cat.build()).queue();
    }

    public void meme(MessageChannel channel) {

        JSONParser parser = new JSONParser();

        String postLink = "";
        String title = "";
        String url = "";
        boolean nsfw = false;

        try {
            URL memeUrl = new URL("https://meme-api.herokuapp.com/gimme/programmerhumor/");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(memeUrl.openConnection().getInputStream()));

            String lines;

            while ((lines = bufferedReader.readLine()) != null) {
                JSONArray array = new JSONArray();
                array.add(parser.parse(lines));

                for (Object o : array) {
                    JSONObject jsonObject = (JSONObject) o;

                    postLink = (String) jsonObject.get("postLink");
                    title = (String) jsonObject.get("title");
                    url = (String) jsonObject.get("url");
                    nsfw = (boolean) jsonObject.get("nsfw");
                }
            }
            bufferedReader.close();

            if (nsfw) {
                meme(channel);
                return;
            }

            EmbedBuilder meme = new EmbedBuilder();
            meme.setColor(0xffa502);
            meme.setTitle(title, postLink);
            meme.setImage(url);
            channel.sendMessageEmbeds(meme.build()).queue();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
