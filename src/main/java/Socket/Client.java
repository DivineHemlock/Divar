package Socket;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import DB.AD;
import DB.Chat;
import DB.Message;
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
import org.bson.Document;
import org.json.simple.parser.ParseException;


public class Client {
    String host = "127.0.0.1";
    int port = 32002;

    DataOutputStream out;
    DataInputStream in;
    Socket socket;

    public Client() throws IOException {
        socket = new Socket(host, port);
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
        listenForMessage();
    }

    public void sendRequest(Request request) throws IOException, ParseException {
        Gson gson = new Gson();

//        String jsonRequest = gson.toJson(request);
        String jsonRequest = gson.toJson(request);
        out.writeUTF(jsonRequest);
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
                        response = in.readUTF();

                        Request jsonToClassResponse = gson.fromJson(response, Request.class);
//                        System.out.println(response);
                        switch (jsonToClassResponse.getId()){
                            case "SC Login" -> {
                                scLogin(jsonToClassResponse.getData());
                                FileHolder.updateUserProfile();
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
                                sendProfile(file.getAbsolutePath(),socket);
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
                                sendProfile(FileHolder.getPic().getAbsolutePath(),socket);
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
                            case "searchResult" ->{
                                String data = jsonToClassResponse.getData();
                                TypeToken<ArrayList<AD>> token = new TypeToken<ArrayList<AD>>() {};
                                ArrayList<AD> ads = gson.fromJson(data,token.getType());
                                SearchResultHolder.setArrayList(ads);
                                Platform.runLater(
                                        () -> {

                                            try {
                                                PageController.close();
                                                PageController.open("adView");
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                );
                            }
                            case "sendUserProfile" ->{
                                File file = new File("user/user.jpg");
                                receiveFile(file.getAbsolutePath(),socket);
                            }
                            case "sendADPicture" ->{
                                File file = new File("user/ad.jpg");
                                receiveFile(file.getAbsolutePath(),socket);
                            }
                            case  "responseChat" ->{
                                Chat chat = gson.fromJson(jsonToClassResponse.getData() , Chat.class);
                                for (Message message : chat.getMessages())
                                {
                                    System.out.println(message.getText());
                                }
                            }
                        }
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void responseChat (String data)
    {
        Gson gson = new Gson();
        Chat chat = gson.fromJson(data , Chat.class);
        ChatHolder.setChat(chat);

    }

    private void scLogin(String data) throws IOException {
        Gson gson = new Gson();
        TypeToken<ArrayList<String>> userSearch = new TypeToken<ArrayList<String>>() {};
        ArrayList<String> responseData = gson.fromJson(data,userSearch.getType());
        TypeToken<ArrayList<AD>> token = new TypeToken<ArrayList<AD>>() {};
        ArrayList<AD> ads = gson.fromJson(responseData.get(0),token.getType());
        CitySearchHolder.setArrayList(ads);
        User user = gson.fromJson(responseData.get(1),User.class);
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



    private static void receiveFile (String name, Socket socket) throws IOException {
        DataInputStream DIS = new DataInputStream(socket.getInputStream());
        int bytes = 0;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(name);
            long size = DIS.readLong();
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = DIS.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                fileOutputStream.write(buffer, 0, bytes);
                size -= bytes;
            }
            fileOutputStream.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    private static void sendProfile (String name, Socket socket) throws IOException {
        DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());

        try {
            int bytes = 0;
            File file = new File(name);
            FileInputStream fileInputStream = new FileInputStream(file);
            DOS.writeLong(file.length());
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                DOS.write(buffer, 0, bytes);
                DOS.flush();
            }
            fileInputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



}
