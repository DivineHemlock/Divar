package Socket;

import DB.AD;
import DB.DBHandler;
import DB.DBMethods;
import DB.User;
import com.example.divar3.HelloController;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ArrayList<ClientHandler> clients;

    public static void main(String[] args) {
        DBHandler handler = new DBHandler();
        handler.getMainDB().drop();
        handler.getMongoClient().close();
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
        DataOutputStream out;
        DataInputStream in;

        public ClientHandler(Socket socket){
            this.clientSocket = socket;

        }

        @Override
        public void run() {
            try {
                Gson gson = new Gson();
                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new DataInputStream(clientSocket.getInputStream());

                JSONParser parser = new JSONParser();

                String requestJson;


                while (true){
                    requestJson = in.readUTF();
                    if (requestJson == null){
                        break;
                    }
                    Request request = gson.fromJson(requestJson, Request.class);
                    String id = request.getId();
                    String data = request.getData();

                    switch (id){
                        case "Login" -> {
                            Request response = responseLogin(data);
                            String responseJson = gson.toJson(response);
                            out.writeUTF(responseJson);
                            out.flush();
                        }
                        case "signUp" ->{
                            String username = gson.fromJson(data,User.class).getUsername();
                            Request response = responseSignUP(data);
                            String responseJson = gson.toJson(response);
                            out.writeUTF(responseJson);
                            out.flush();
                            if(response.getId().equals("scSignUP")){
//                                FileOutputStream fileOutputStream = new FileOutputStream("/Users/aryanneizehbaz/Aryan8221/coding_projects/java_projects/Divar/src/main/java/test.jpg");
//                                getFile(fileOutputStream, in);
                                String path = "/Users/aryanneizehbaz/Aryan8221/coding_projects/java_projects/Divar/src/main/java/test.jpg";
                                receiveFile(path, clientSocket);
                            }

                        }
                        case "createAd" ->{
                            Request response = new Request();
                            response = responseCreateAd(data);
                            String responseJson = gson.toJson(response);
                            out.writeUTF(responseJson);
                            out.flush();
                        }
                        case  "searchPrice" ->{
                            Request response = responseSearchPrice(data);
                            String responseJson = gson.toJson(response);
                            out.writeUTF(responseJson);
                            out.flush();
                        }
                        case "searchTag" ->{
                            Request response = responseSearchTag(data);
                            String responseJson = gson.toJson(response);
                            out.writeUTF(responseJson);
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
        String username = userPass.get("username").toString();
        String password = userPass.get("password").toString();
        JsonObject jsonObject = DBMethods.findUserInDB(username, password);
        User user = gson.fromJson(jsonObject, User.class);
        ArrayList<AD> ads = new ArrayList<>();
        Request response = new Request();
        if(user == null){
            response.setId("Fail Login");
        }
        else {
            response.setId("SC Login");
            String city = user.getCity();
            ArrayList<Document> jsonResult = DBMethods.findAdByCity(city);
            System.out.println(jsonResult.size());
            for (int i = 0; i < jsonResult.size(); i++){
                AD ad = gson.fromJson(jsonResult.get(i).toJson(), AD.class);
                ads.add(ad);
            }
            ArrayList<String> responseData = new ArrayList<>();
            responseData.add(gson.toJson(ads));
            responseData.add(gson.toJson(user));
            response.setData(gson.toJson(responseData));
        }
        return response;
    }

    public static Request responseSignUP(String data){
        Request response = new Request();
        Gson gson = new Gson();
        User user = gson.fromJson(data,User.class);
        boolean isCreated = DBMethods.makeNewUser(user);
        if (isCreated){
            response.setId("scSignUP");
        }
        else {
            response.setId("failSignUP");
        }
        return response;
    }

    public static Request responseCreateAd(String data){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        String title = jsonObject.get("title").getAsString();
        String price = jsonObject.get("price").getAsString();
        String tag = jsonObject.get("tag").getAsString();
        String details = jsonObject.get("details").getAsString();
        String city = jsonObject.get("city").getAsString();
        String phoneNumber = jsonObject.get("phoneNumber").getAsString();
        String username = jsonObject.get("username").getAsString();
        AD ad = new AD(title,city,price,username,details,tag,phoneNumber);
        DBMethods.makeNewAD(ad);
        Request response = new Request();
        response.setData(ad.getID());
        response.setId("createdAd");
        return response;
    }
    public static Request responseSearchPrice(String data){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        String min = jsonObject.get("min").getAsString();
        String max = jsonObject.get("max").getAsString();
        Request response = new Request();
        response.setId("searchResult");
        ArrayList<Document> jsonResult = DBMethods.findAdByPriceRange(min,max);
        ArrayList<AD> ads = new ArrayList<>();
        for (int i = 0; i < jsonResult.size(); i++){
            AD ad = gson.fromJson(jsonResult.get(i).toJson(), AD.class);
            ads.add(ad);
        }
        response.setData(gson.toJson(ads));
        return response;
    }
    public static Request responseSearchTag(String data){
        Gson gson = new Gson();
        String tag = data;
        Request response = new Request();
        response.setId("searchResult");
        ArrayList<Document> jsonResult = DBMethods.findAdByTag(tag);
        ArrayList<AD> ads = new ArrayList<>();
        for (int i = 0; i < jsonResult.size(); i++){
            AD ad = gson.fromJson(jsonResult.get(i).toJson(), AD.class);
            ads.add(ad);
        }
        response.setData(gson.toJson(ads));
        return response;
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

