import java.net.InetAddress;
import java.net.UnknownHostException;

public class Adressage {
    public static void main(String[] args) {
        InetAddress localeAdresse;
        InetAddress serveurAdresse;

        try {
            localeAdresse = InetAddress.getLocalHost();
            System.out.println("locale = " + localeAdresse);
            // source de l'exemple

            serveurAdresse = InetAddress.getByName("www.openclassrooms.com");
            System.out.println("oc = " + serveurAdresse);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}