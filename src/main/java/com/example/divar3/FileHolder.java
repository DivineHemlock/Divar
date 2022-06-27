package com.example.divar3;

import Socket.Request;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class FileHolder {
    private static File pic;

    public static File getPic() {
        return pic;
    }

    public static void setPic(File pic) {
        FileHolder.pic = pic;
    }
    public static void updateUserProfile() throws IOException, ParseException {
        String username = UserHolder.getUser().getUsername();
        Request request = new Request();
        request.setId("getProfilePhoto");
        request.setData(username);
        ClientHolder.getClient().sendRequest(request);
    }
    public static void updateADPicture() throws IOException, ParseException {
        String adId = ADHolder.getAd().getID();
        Request request = new Request();
        request.setId("getADPhoto");
        request.setData(adId);
        ClientHolder.getClient().sendRequest(request);
    }
}
