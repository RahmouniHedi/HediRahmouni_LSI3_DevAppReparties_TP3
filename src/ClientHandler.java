// Fichier: ClientProcess.java
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.clientSocket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            while (true) {
                Operation op = (Operation) in.readObject();
                int res = 0;
                switch (op.getOperator()) {
                    case '+': res = op.getOp1() + op.getOp2(); break;
                    case '-': res = op.getOp1() - op.getOp2(); break;
                    case '*': res = op.getOp1() * op.getOp2(); break;
                    case '/': res = (op.getOp2() != 0) ? op.getOp1() / op.getOp2() : 0; break;
                }
                op.setResult(res);

                ServeurMT.incrementAndLogOperations();

                out.writeObject(op);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("Client n°" + clientNumber + " déconnecté.");
        }
    }
}