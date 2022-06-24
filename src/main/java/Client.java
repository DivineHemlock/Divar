import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import org.json.simple.JSONObject;


public class Client {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 32001;
        try (Socket socket = new Socket(host, port)){
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            JSONObject obj = new JSONObject();
            Gson gson = new Gson();

            System.out.println("request: ");
            String requestID =scanner.nextLine();

            while (!"exit".equalsIgnoreCase(requestID)) { // exit button

                Request request = new Request();

                if (requestID.equals("LOGIN")){
                    JSONObject userPass = new JSONObject();

                    String username = scanner.nextLine();
                    String password = scanner.nextLine();

                    userPass.put("username", username);
                    userPass.put("password", password);

                    System.out.println(userPass);// testing

                    request.setId(requestID);
                    request.setData(userPass.toString());// request data is json

                    String jsonRequest = gson.toJson(request);

                    out.println(jsonRequest);
                    out.flush();
                }

                System.out.println("Server replied " + in.readLine());
            }
            scanner.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
