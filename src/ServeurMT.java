// Fichier: ServeurCalculatrice.java
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ServeurMT {

    // Compteur global partagé (Étape 3)
    private static final AtomicInteger operationCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        int port = 1234;
        int clientNumber = 0;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur de calculatrice démarré sur le port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientNumber++;
                System.out.println("Client n°" + clientNumber + " connecté (" + clientSocket.getRemoteSocketAddress() + ").");

                ClientHandler clientProc = new ClientHandler(clientSocket, clientNumber);
                new Thread(clientProc).start();
            }
        } catch (IOException e) {
            System.err.println("Erreur sur le serveur : " + e.getMessage());
        }
    }

    public static void incrementAndLogOperations() {
        int count = operationCounter.incrementAndGet();
        System.out.println("Nombre total d'opérations traitées : " + count);
    }
}