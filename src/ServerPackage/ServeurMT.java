package ServerPackage;


import ClientProcess.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMT {

    public static void main(String[] args) {
        int port = 1234;
        int clientNumber = 0;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Le serveur est démarré et écoute sur le port " + port);


            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientNumber++;

                ClientHandler clientHandler = new ClientHandler(clientSocket, clientNumber);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Erreur sur le serveur : " + e.getMessage());
        }
    }
}