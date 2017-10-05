/**
 * Created by schiduvasile on 9/6/17.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.IntStream;

class TCPClientIterator {
    public static void main(String argv[]) throws Exception {


        IntStream.rangeClosed(8000, 8080).filter(port -> {
            try {
                Socket socket = new Socket("localhost", port);
                return true;
            } catch (IOException e) {
                return false;
            }
        }).forEach(System.out::println);


    }
}
