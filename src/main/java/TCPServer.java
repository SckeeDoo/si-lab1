/**
 * Created by schiduvasile on 9/6/17.
 */
import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        final String[] clientSentence = new String[1];
        final String[] capitalizedSentence = new String[1];
        ServerSocket welcomeSocket = new ServerSocket(8080);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("New client connected ...");

            new Thread(() -> {
                try {
                    DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
                    DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                    while (!welcomeSocket.isClosed()){
                        clientSentence[0] = inFromClient.readUTF();
                        System.out.println("Received: " + clientSentence[0]);
                        capitalizedSentence[0] = clientSentence[0].toUpperCase();
                        outToClient.writeUTF(capitalizedSentence[0]);
                    }



                } catch (IOException e) {

                }
            }).start();

        }
    }
}