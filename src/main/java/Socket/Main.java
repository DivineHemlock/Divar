package Socket;


import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main { // FX main
    public static void main(String[] args) throws IOException, ParseException {
        Request request1 = new Request();
        Request request2 = new Request();

        request1.setId("Login");
        request1.setData("aryan");

        request2.setId("Login");
        request2.setData("aryan");

        Client client = new Client();

        client.sendRequest(request1);
        client.sendRequest(request2);
    }
}
