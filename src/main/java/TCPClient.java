/**
 * Created by schiduvasile on 9/6/17.
 */

import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;

        int counter = 0 ;


            try {

                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

                Socket clientSocket = new Socket("localhost", 8080);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());


                while (counter < 5) {
                    outToServer.writeUTF("Message " + (counter+1));
                    outToServer.flush();
                    modifiedSentence = inFromServer.readUTF();
                    System.out.println("FROM SERVER: " + "Message" + (counter+1));
                    counter++;
                    Thread.sleep(5000);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }




}
