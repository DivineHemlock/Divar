package Socket;


import java.io.*;
import java.net.Socket;

import com.google.gson.Gson;
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
        listenForMessage();
    }

    public void sendRequest(Request request) throws IOException, ParseException {
        Gson gson = new Gson();

//        String jsonRequest = gson.toJson(request);
        String jsonRequest = gson.toJson(request);
        out.println(jsonRequest);
        out.flush();
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            Gson gson = new Gson();

            @Override
            public void run() {
                String response;

                while (socket.isConnected()) {
                    try {
                        response = in.readLine();

                        Request JsonToClassResponse = gson.fromJson(response, Request.class);
//                        System.out.println(response);
                        switch (JsonToClassResponse.getId()){
                            case "SC Login" -> {

                            }
                            case "Fail Login" -> {

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
