import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class SimpleSocketServeur {
    public static void main(String[] args) {
        try (ServerSocket serveur = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serveur.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object obj = ois.readObject();

                if (obj instanceof List<?>) {
                    @SuppressWarnings("unchecked")
                    List<Integer> t1 = (List<Integer>) obj;
                    @SuppressWarnings("unchecked")
                    List<Integer> t2 = (List<Integer>) ois.readObject();
                    List<Integer> rep = new ArrayList<>(t1);
                    rep.addAll(t2);
                    Collections.sort(rep);
                    System.out.println("le serveur a reçu : " + t1 + " et " + t2);

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(rep);
                } else if (obj instanceof Set<?>) {
                    @SuppressWarnings("unchecked")
                    Set<String> set = (Set<String>) obj;
                    System.out.println("le serveur a reçu : " + set);
                } else {
                    System.out.println("L'objet reçu n'est pas une liste ou un ensemble");
                }

                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}