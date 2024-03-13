// Client.java
import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements Runnable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socketDivision = new Socket("localhost", 5000);
        Socket socketFusion = new Socket("localhost", 6000);

        List<Integer> list = Arrays.asList(4, 5, 9, 12, 16, 1, 6, 8, 9, 12);
        ObjectOutputStream outDivision = new ObjectOutputStream(socketDivision.getOutputStream());
        System.out.println("Client: Sending list: " + list + " division to server");
        outDivision.writeObject(list);

        ObjectInputStream inDivision = new ObjectInputStream(socketDivision.getInputStream());
        List<Integer> list1 = (List<Integer>) inDivision.readObject();
        List<Integer> list2 = (List<Integer>) inDivision.readObject();
        System.out.println("Client: Received list1: " + list1+ " and list2: " + list2 + " from division server");

        ObjectOutputStream outFusion = new ObjectOutputStream(socketFusion.getOutputStream());
        System.out.println("Client: Sending list1: " + list1 + " and list2: " + list2 + " fusion to server");
        outFusion.writeObject(list1);
        outFusion.writeObject(list2);

        ObjectInputStream inFusion = new ObjectInputStream(socketFusion.getInputStream());
        System.out.println("Client: Receiving sorted list from fusion server");
        List<Integer> sortedList = (List<Integer>) inFusion.readObject();
        System.out.println("Sorted list: " + sortedList);

        socketDivision.close();
        socketFusion.close();
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