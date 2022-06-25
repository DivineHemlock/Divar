import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main { // FX main
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Request request = new Request();
        JSONObject userPass = new JSONObject();

        System.out.println("request:");
        String requestID = scanner.nextLine();

        Client client = new Client();
        client.sendRequest(requestID);
    }
}
