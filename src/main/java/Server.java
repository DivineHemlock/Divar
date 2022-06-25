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

        public ClientHandler(Socket socket){
            this.clientSocket = socket;

        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                JSONParser parser = new JSONParser();

                String requestJson;

                while ((requestJson = in.readLine()) != null){

                    JSONObject json = (JSONObject) parser.parse(requestJson);
                    JSONObject userPass = (JSONObject) parser.parse((String) json.get("data"));

                    if (json.get("id").equals("Login")){
                        loginPageSearchStuff((String) userPass.get("username"), (String) userPass.get("password"));

                        System.out.println(json.get("id"));
                        System.out.println(userPass.get("username"));
                        System.out.println(userPass.get("password"));

                        out.println("more data from username: " + userPass.get("username") + " and password " + userPass.get("password"));
                        out.flush();
                    }

                    if (json.get("id").equals("Sign up")){

                    }

                    if (json.get("id").equals("Create ad")){

                    }

                }
            } catch (IOException | ParseException e) {
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
}

