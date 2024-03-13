import java.io.*;
import java.net.*;
import java.util.*;

public class ServeurDivision implements Runnable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(5000);

        while (true) {
            Socket socket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Serveur division: Receiving list from client");
            List<Integer> list = (List<Integer>) in.readObject();

            int mid = list.size() / 2;
            List<Integer> list1 = new ArrayList<>(list.subList(0, mid));
            List<Integer> list2 = new ArrayList<>(list.subList(mid, list.size()));
            System.out.println("Serveur division: Sending list1: " + list1 + " and list2: " + list2 + " to client");

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(list1);
            out.writeObject(list2);

            socket.close();
        }
    }

    @Override
    public void run() {
        try {
            main(null);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}