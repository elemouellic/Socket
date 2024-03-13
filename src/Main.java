public class Main {

    public static void main(String[] args) {
        Thread threadServeurDivision = new Thread(new ServeurDivision());
        System.out.println("Serveur division lancé");
        threadServeurDivision.start();


        Thread threadServeurFusion = new Thread(new ServeurFusion());
        System.out.println("Serveur fusion lancé");
        threadServeurFusion.start();

        Thread threadClient = new Thread(new Client());
        System.out.println("Client lancé");
        threadClient.start();
    }
}
