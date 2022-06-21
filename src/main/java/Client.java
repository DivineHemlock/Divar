import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable{
    private static Socket socket;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        socket = new Socket("127.0.0.1", Server.PORT);

        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        Message message = new Message("aryan", "arad");

        objectOutputStream.writeObject(message);
//        socket.close();
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try{
                messageFromClient = (String) objectInputStream.readObject();
                System.out.println(messageFromClient);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
