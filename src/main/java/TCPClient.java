/**
 * Created by schiduvasile on 9/6/17.
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;


        int maxMess;
        int delayTime;
        int counter = 0 ;


            try {


                Scanner intFromUser = new Scanner(System.in);
                Socket clientSocket = new Socket("localhost", 8080);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());


                System.out.println("Enter number of messages you want to send: ");
                maxMess = intFromUser.nextInt();
                System.out.println("Enter delay time in seconds for sending: ");
                delayTime = intFromUser.nextInt();

                while (counter < maxMess) {
                    outToServer.writeUTF("Message " + (counter+1));
                    outToServer.flush();
                    modifiedSentence = inFromServer.readUTF();
                    System.out.println("FROM SERVER: " + "Message" + (counter+1));
                    counter++;
                    Thread.sleep(delayTime * 1000);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }




}
