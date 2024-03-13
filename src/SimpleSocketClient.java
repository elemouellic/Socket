import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class SimpleSocketClient {

    private static final List<Integer> t1 = Arrays.asList(4, 5, 9, 12, 16);
    private static final List<Integer> t2 = Arrays.asList(1, 6, 8, 9, 12);

    public static void main(String[] args) {
        System.out.println("début");
        try (Socket socket= new Socket(InetAddress.getLocalHost(), 5000)){
            ObjectOutputStream oos= new ObjectOutputStream( socket.getOutputStream());
            oos.writeObject(t1);
            oos.writeObject(t2);

            SortedSet<String> l = new TreeSet<String>();
            l.add("TCP");
            l.add("essai");
            oos.writeObject( l);

            ObjectInputStream ois= new ObjectInputStream(socket.getInputStream());

            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<Integer> rep = (List<Integer>) obj;
                System.out.println("le serveur retourne : " + rep);
            } else {
                System.out.println("L'objet reçu n'est pas une liste");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("fin");
    }
}