
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // ou l'IP du serveur en réseau local
        int port = 1234;

        try (
                Socket socket = new Socket(serverAddress, port);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connecté au serveur de calculatrice.");
            System.out.println("Entrez vos opérations (ex: 5 + 3) ou 'exit' pour quitter.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) break;

                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    System.out.println("Format invalide. Veuillez utiliser : nombre operateur nombre");
                    continue;
                }

                int op1 = Integer.parseInt(parts[0]);
                char operator = parts[1].charAt(0);
                int op2 = Integer.parseInt(parts[2]);

                // Création et envoi de l'objet Operation
                Operation op = new Operation(op1, op2, operator);
                out.writeObject(op);
                out.flush();

                // Lecture de la réponse
                Operation resultOp = (Operation) in.readObject();
                System.out.println("Résultat : " + resultOp.getResult());
            }
        } catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}