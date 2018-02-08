import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Tools {
    //--------------------Methoden--------------------//

    public static void send(String message) {

        //Dem Programm wird das Ziel der Nachricht gesetzt mit Hilfe des "ConfigurationBuilder"
        ConfigurationBuilder cb = new ConfigurationBuilder();

        //Debug
        cb.setDebugEnabled(true);

        //Ziel: (Die Twitter Application)
        cb.setOAuthConsumerKey(STATICS.Consumer_Key);
        cb.setOAuthConsumerSecret(STATICS.Consumer_Secret);
        cb.setOAuthAccessToken(STATICS.Access_Token);
        cb.setOAuthAccessTokenSecret(STATICS.Access_Secret);

        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();


        //try-catch fängt mögliche Errors ab
        try {
            //Durch updateStatus wird eine Message in Form eines Strings auf Twitter gepostet
            twitter.updateStatus(message);
        } catch (Exception e) {
            //Error wird ausgegeben
            e.printStackTrace();
        }
    }

    //Zufälliges Bild
    public static File getPic() {
        try {
            File path = new File("bilder/");
            if (!path.exists()) {
                path.mkdirs();
            }
            InputStream inputStream = new URL("http://random.cat/meow").openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            JSONObject object = new JSONObject(br.readLine());

            FileOutputStream fos = new FileOutputStream("bilder/bild.jpg");
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(object.getString("file")).openStream());
            fos.getChannel().transferFrom(readableByteChannel, 0 , Long.MAX_VALUE);

            File f = new File("bilder/bild.jpg");
            return f;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendPic(File f) {
        //Dem Programm wird das Ziel der Nachricht gesetzt mit Hilfe des "ConfigurationBuilder"
        ConfigurationBuilder cb = new ConfigurationBuilder();

        //Debug
        cb.setDebugEnabled(true);

        //Ziel: (Die Twitter Application)
        cb.setOAuthConsumerKey(STATICS.Consumer_Key);
        cb.setOAuthConsumerSecret(STATICS.Consumer_Secret);
        cb.setOAuthAccessToken(STATICS.Access_Token);
        cb.setOAuthAccessTokenSecret(STATICS.Access_Secret);

        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();


        //try-catch fängt mögliche Errors ab
        try {
            //Neuer Status wird gesetzt
            StatusUpdate status = new StatusUpdate("Image:");

            //Als Status wird eine Datei gesetzt
            status.setMedia(f);


            //File wird auf Twitter gepostet
            twitter.updateStatus(status);
        } catch (Exception e) {
            //Error wird ausgegeben
            e.printStackTrace();
        }
    }
}
