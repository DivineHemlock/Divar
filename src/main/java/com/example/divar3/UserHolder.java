package com.example.divar3;

import DB.User;
import Socket.Request;
import com.google.gson.Gson;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UserHolder {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User savedUser) {
        user = savedUser;
    }
    public static void updateUserInDataBase() throws IOException, ParseException {
        Request request = new Request();
        Gson gson = new Gson();
        String data = gson.toJson(getUser());
        request.setData(data);
        request.setId("updateUser");
        ClientHolder.getClient().sendRequest(request);
    }
}
