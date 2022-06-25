import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main { // FX main
    public static void main(String[] args) throws IOException, ParseException {
        Request request = new Request();

        request.setId("Login");
        request.setData("aryan");

        Client client = new Client();
        client.sendRequest(request);
    }
}
