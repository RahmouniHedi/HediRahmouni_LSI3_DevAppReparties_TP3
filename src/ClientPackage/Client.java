package ClientPackage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 1234;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connecté au serveur sur " + serverAddress + ":" + serverPort);

            // Lire le message envoyé par le serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = in.readLine();

            System.out.println("Message du serveur : " + serverResponse);

        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}