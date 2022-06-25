import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Client {
    String host = "127.0.0.1";
    int port = 32002;

    PrintWriter out;
    BufferedReader in;
    Socket socket;

    public Client() throws IOException {
        socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendRequest(Request request) throws IOException, ParseException {
        Gson gson = new Gson();

        JSONObject rq = new JSONObject();

        String jsonRequest = gson.toJson(request);

        out.println(jsonRequest);
        out.flush();

        String response = in.readLine(); // json
        System.out.println("Server replied " + response);
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = in.readLine();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
