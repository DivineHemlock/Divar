package com.example.divar3;

import java.io.*;
import java.net.Socket;

public class Network {
    public static void sendData(String s) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8889);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        dataOutputStream.writeUTF(s);
    }
}
