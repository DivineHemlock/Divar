package com.example.divar3;

public class PictureNameHolder {
    private static String adPictureName;
    private static String userPictureName;

    public static String getUserPictureName() {
        return userPictureName;
    }

    public static String getAdPictureName() {
        return adPictureName;
    }

    public static void setAdPictureName(String adPictureName) {
        PictureNameHolder.adPictureName = adPictureName;
    }

    public static void setUserPictureName(String userPictureName) {
        PictureNameHolder.userPictureName = userPictureName;
    }
}
