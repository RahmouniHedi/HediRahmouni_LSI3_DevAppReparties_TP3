package ClientProcess;

// Fichier : ClientHandler.java
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.clientSocket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        try {
            SocketAddress remoteAddress = clientSocket.getRemoteSocketAddress();
            System.out.println("Client n°" + clientNumber + " connecté. Adresse IP: " + remoteAddress);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Vous etes le client nbr:" + clientNumber);


        } catch (IOException e) {
            System.err.println("Erreur lors de la gestion du client n°" + clientNumber + ": " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
            }
        }
    }
}