package Socket;


import java.io.*;
import java.net.Socket;

import DB.User;
import com.example.divar3.UserHolder;
import com.example.divar3.controller.LoginController;
import com.example.divar3.controller.PageController;
import com.example.divar3.controller.SignUpController;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
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
        System.out.println(request.getId());
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

                        Request jsonToClassResponse = gson.fromJson(response, Request.class);
//                        System.out.println(response);
                        switch (jsonToClassResponse.getId()){
                            case "SC Login" -> {
                                scLogin(jsonToClassResponse.getData());
                            }
                            case "Fail Login" -> {
                                FXMLLoader loader = PageController.open("loginPage");
                                LoginController loginController = loader.getController();
                                loginController.setError();
                            }
                            case "scSignUp" -> {
                                PageController.close();
                                PageController.open("loginPage");
                            }
                            case "failSignUP" -> {
                                FXMLLoader loader = PageController.open("signUp");
                                SignUpController signUpController = loader.getController();

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void scLogin(String data) throws IOException {
        Gson gson = new Gson();
        User user = gson.fromJson(data,User.class);
        UserHolder.setUser(user);
        PageController.close();
        PageController.open("menu");
    }

}
