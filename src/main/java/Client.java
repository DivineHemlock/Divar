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

    public Client() throws IOException {
        Socket socket;
        socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendRequest(String requestID) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        while (!"exit".equalsIgnoreCase(requestID)) { // exit button

            Request request = new Request();

            if (requestID.equals("Login")){
                JSONObject userPass = new JSONObject();

                String username = scanner.nextLine();
                String password = scanner.nextLine();

                System.out.println("username:");
                userPass.put("username", username);
                System.out.println("password");
                userPass.put("password", password);

                System.out.println(userPass);// testing

                request.setId(requestID);
                request.setData(userPass.toString());// request data is json

                String jsonRequest = gson.toJson(request);

                out.println(jsonRequest);
                out.flush();
            }

            if (requestID.equals("Sign up")){
                continue;
            }

            if (requestID.equals("Create ad")){
                continue;
            }

            String response = in.readLine(); // json
            System.out.println("Server replied " + response);
        }
    }

//    public static void main(String[] args) throws IOException {
//        String host = "127.0.0.1";
//        int port = 32002;
//        try (Socket socket = new Socket(host, port)){
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            Scanner scanner = new Scanner(System.in);
//
//            JSONObject obj = new JSONObject();
//            Gson gson = new Gson();
//
//            System.out.println("request: ");
//            String requestID = scanner.nextLine();
//
//            while (!"exit".equalsIgnoreCase(requestID)) { // exit button
//
//                Request request = new Request();
//
//                if (requestID.equals("LOGIN")){
//                    JSONObject userPass = new JSONObject();
//
//                    String username = scanner.nextLine();
//                    String password = scanner.nextLine();
//
//                    userPass.put("username", username);
//                    userPass.put("password", password);
//
//                    System.out.println(userPass);// testing
//
//                    request.setId(requestID);
//                    request.setData(userPass.toString());// request data is json
//
//                    String jsonRequest = gson.toJson(request);
//
//                    out.println(jsonRequest);
//                    out.flush();
//                }
//
//                System.out.println("Server replied " + in.readLine());
//            }
//            scanner.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    public void interpreting(Request responseFromDB) {
        Gson gson = new Gson();
        String id = responseFromDB.getId();
        String data = responseFromDB.getData();


        switch (id) {
            case "SCLOGIN":
//                User dataUser = gson.fromJson(data, User.class);

                break;
        }
    }
}
