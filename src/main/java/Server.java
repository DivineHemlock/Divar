import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    static PrintWriter out;
    static BufferedReader in;
    private static HashMap<String, PrintWriter> loginInfos = new HashMap<>();
    static Socket socket;

    public static void main(String[] args) {
        ServerSocket server = null;
        try{
            server = new ServerSocket(32003);
            server.setReuseAddress(true);
            while (true){
                socket = server.accept();
                System.out.println("New client connected");
                ClientHandler clientSock = new ClientHandler(socket);

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
                            Request response = responseLogin(data, out);
                            String responseJson = gson.toJson(response);
                            System.out.println(loginInfos.get("ali").getClass());
                            out.println(responseJson);
                            out.flush();
                        }
                        case "Send File" -> {
                            FileOutputStream file = new FileOutputStream("url");
                            sendFile(file);
                        }
                        case "Ad image" -> {
                            // response
                            //getFile(new File());
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

    public static Request responseLogin(String data, PrintWriter out){
        Gson gson = new Gson();
        JSONObject userPass = gson.fromJson(data, JSONObject.class);
        System.out.println(userPass);

        Request response = new Request();
        response.setData("more data");

        if (userPass.get("username").equals("ali")){
            response.setId("SC Login");
            loginInfos.put((String) userPass.get("username"), out);

        } else{
            response.setId("Fail Login");
        }

        return response;
    }

    public static void getFile(File file) throws IOException {
        InputStream inputStream = socket.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) > 0){
            fileOutputStream.write(buffer);
            System.out.println(Arrays.toString(buffer));
        }
        fileOutputStream.close();
        inputStream.close();
    }

    public static void sendFile(FileOutputStream file) throws IOException { // send File to Client
        OutputStream os = socket.getOutputStream();
        FileInputStream fr = new FileInputStream("/Users/aryanneizehbaz/Aryan8221/coding_projects/java_projects/test2/src/main/java/java.jpg");

        System.out.println(Files.size(Path.of("/Users/aryanneizehbaz/Aryan8221/coding_projects/java_projects/test2/src/main/java/java.jpg")));
        byte b[] = new byte[25000];
        fr.read(b, 0, b.length);
        System.out.println(Arrays.toString(b));
        os.write(b, 0, b.length);
    }
}

