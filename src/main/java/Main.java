import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main { // FX main
    public static void main(String[] args) throws IOException, ParseException {
        Gson gson = new Gson();
        Request request1 = new Request();
        Request request2 = new Request();

        request1.setId("Send file");
//        request1.setId("Ad image");

        JSONObject jsonData = new JSONObject();
        jsonData.put("username", "ali");
        jsonData.put("password", "123");

        request1.setData(jsonData.toString());

//        request2.setId("Login");
//        request2.setData("aryan");

        Client client = new Client();

        client.sendRequest(request1);
//        client.sendRequest(request2);
    }
}
