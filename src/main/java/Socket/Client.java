package Socket;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import DB.AD;
import DB.User;
import com.example.divar3.*;
import com.example.divar3.controller.LoginController;
import com.example.divar3.controller.PageController;
import com.example.divar3.controller.SignUpController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
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
                                try {
                                    Platform.runLater(
                                            () -> {
                                                PageController.close();
                                                FXMLLoader loader = null;
                                                try {
                                                    loader = PageController.open("loginPage");
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                LoginController loginController = loader.getController();
                                                loginController.setError();
                                            }
                                    );

                                }
                                catch (Exception e){
                                    System.out.println(e.getMessage());
                                }

                            }
                            case "scSignUP" -> {
                                File file = FileHolder.getPic();
                                FileInputStream fileInputStream = new FileInputStream(file);
                                sendFile(fileInputStream,socket);
                                Platform.runLater(
                                        () -> {
                                            PageController.close();
                                            try {
                                                PageController.open("loginPage");
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                );

                            }
                            case "failSignUP" -> {
                                Platform.runLater(
                                        () -> {
                                            PageController.close();
                                            FXMLLoader loader = null;
                                            try {
                                                loader = PageController.open("signUp");
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            SignUpController signUpController = loader.getController();
                                            signUpController.userError();
                                        }
                                );
                            }
                            case  "createdAd" ->{
                                    createdAd(jsonToClassResponse);
                            }
                            case "searchResult" ->{
                                String data = jsonToClassResponse.getData();
                                TypeToken<ArrayList<AD>> token = new TypeToken<ArrayList<AD>>() {};
                                ArrayList<AD> ads = gson.fromJson(data,token.getType());
                                SearchResultHolder.setArrayList(ads);
                                Platform.runLater(
                                        () -> {

                                            try {
                                                PageController.open("adView");
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                );
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
        Platform.runLater(
                () -> {
                    PageController.close();
                    try {
                        PageController.open("menu");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

    }

    private void createdAd(Request response){
        PictureNameHolder.setAdPictureName(response.getData());
        System.out.println(response.getData());
    }


    public static void getFile(FileOutputStream fr, Socket socket) throws IOException {
        InputStream is = socket.getInputStream();

        byte[] b = new byte[16000];
        is.read(b, 0, b.length);
        fr.write(b, 0, b.length);
    }

    public static void sendFile(FileInputStream fr, Socket socket) throws IOException {
        OutputStream os = socket.getOutputStream();

        byte b[] = new byte[16000];
        fr.read(b, 0, b.length);

        os.write(b, 0, b.length);
    }

}
