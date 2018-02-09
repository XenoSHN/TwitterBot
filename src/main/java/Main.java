import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class Main {

    private static Timer timer = new Timer();
    //Core
    public static void main(String[] args) throws ParseException, IOException, InterruptedException {

        //System input:
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i;

        //System input zur Auswahl der Unterpunkte des Menüs
        Scanner input = new Scanner(System.in);

        //Menü wird in die Konsole ausgegeben
        System.out.println("Bot Started\n" +
                "1. Tweet senden:\n" +
                "2. 5min-Pic-Bot starten\n" +
                "3. Quit\n");

        //Zahl i wird die nächste Zahl die in die Konsole eingegeben wird
        i = input.nextInt();


        if (i == 1) {
            //Neue Variable(String) "msg" wird gesetzt und initialisiert
            String msg = null;

            cls();
            //"Tweet:" wird in die Konsole ausgegeben
            System.out.println("Tweet:");

            try {
                //Varibale wird durch den BufferedReader auf den nächsten String gesetzt, der in die Konsole eigegeben wird
                msg = br.readLine();
            } catch (Exception e) {
                System.out.println("ERROR");
            }
            //Debug
            System.out.println(msg);

            //Tweet wird mit Hife der Methode aus Tools gesendet
            Tools.send(msg);
            cls();
            //Programm wird neu gestartet
            Main.main(args);

        } else if (i == 2) {
            //5min-Pic-Bot

            cls();
            //Timer für die zeitgesteuerte Loop
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Tools.sendPic(Tools.getPic());
                        }
                    } , 0 ,5*60*1000 //aller 5 min wird ein Bild geschickt
            );

            Main.main(args);

        } else if (i == 3) {
            //Pic-Bot wird gestoppt
            timer.cancel();

            //Programm wird beendet
            System.exit(0);
        }
    }
    //Die Konsole löschen
    public static void cls() throws IOException, InterruptedException {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    }
}
