import java.io.*;
import java.net.*;
import java.util.*;

public class ServeurFusion implements Runnable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(6000);

        while (true) {
            Socket socket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Serveur fusion: Receiving list1 and list2 from client");
            List<Integer> list1 = (List<Integer>) in.readObject();
            List<Integer> list2 = (List<Integer>) in.readObject();

            List<Integer> sortedList = new ArrayList<>();
            sortedList.addAll(list1);
            sortedList.addAll(list2);
            Collections.sort(sortedList);
            System.out.println("Serveur fusion: Sending sorted list to client");

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Serveur fusion: Sending sorted list to client");
            out.writeObject(sortedList);

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