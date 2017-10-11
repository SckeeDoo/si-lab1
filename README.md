# SI - Laboratory Work 1

Performed: **Vasile SCHIDU**

Language: **Java 1.8**

### 1. Send TCP Messages

**TCPServer.java** - accepts a **TCP** connection and waits for incoming messages from the connected **client**

```java
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
```

**TCPClien.java** - connects to server and sends string **messages** read from the keyboard. Also you can input **delay time** and **number of messages**

```java
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

```

### 2. Send TCP Messages. Port scanning of a given IP and outputs the port that is active.

**TCPClientIterator.java** - scanning of a given IP and outputs the port that is active.

```java
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
```
### 3. Send a file through network
**TCPFileSender.java** - sends the file.
```java
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
```
**TCPFileReceiver.java** - receive the file.
```java
public class TCPFileReceiver {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        InputStream inputStream = socket.getInputStream();

        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        System.out.println(size);
        byte[] imageAr = new byte[size];
        inputStream.read(imageAr);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        ImageIO.write(image, "jpg", new File("/Users/schiduvasile/Desktop/screen1.png"));
    }
}
```

