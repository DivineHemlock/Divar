import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    private ArrayList<ClientHandler> clients;

    public static void main(String[] args) {
        ServerSocket server = null;
        try{
            server = new ServerSocket(32002);
            server.setReuseAddress(true);
            while (true){
                Socket client = server.accept();
                System.out.println("New client connected");
                ClientHandler clientSock = new ClientHandler(client);

                new Thread(clientSock).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (server != null){
                try{
                    server.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ClientHandler implements Runnable{

        private final Socket clientSocket;
        PrintWriter out;
        BufferedReader in;

        public ClientHandler(Socket socket){
            this.clientSocket = socket;

        }

        @Override
        public void run() {
            try {
                Gson gson = new Gson();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                JSONParser parser = new JSONParser();

                String requestJson;

                while ((requestJson = in.readLine()) != null){

                    Request request = gson.fromJson(requestJson, Request.class);
                    String id = request.getId();
                    String data = request.getData();

                    switch (id){
                        case "Login" -> {
                            Request response = responseLogin(data);
                            String responseJson = gson.toJson(response);
                            out.println(responseJson);
                            out.flush();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try{
                    if (out != null){
                        out.close();
                    }
                    if (in != null){
                        in.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loginPageSearchStuff(String username, String password){
        // Searching methods
    }

    public static Request responseLogin(String data){
        Gson gson = new Gson();
        JSONObject userPass = gson.fromJson(data, JSONObject.class);

        Request response = new Request();
        response.setData("more data");

        if (userPass.get("username").equals("ali")){
            response.setId("SC Login");
        } else{
            response.setId("Fail Login");
        }

        return response;
    }
}

