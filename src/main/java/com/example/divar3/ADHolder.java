package com.example.divar3;

import DB.AD;

public class ADHolder {
    private static AD ad;
    private static boolean returnToMenu;

    private static String returnFromProfilePage;

    public static String getReturnFromProfilePage() {
        return returnFromProfilePage;
    }

    public static void setReturnFromProfilePage(String returnFromProfilePage) {
        ADHolder.returnFromProfilePage = returnFromProfilePage;
    }

    public static void setReturnToMenu(boolean returnToMenu) {
        ADHolder.returnToMenu = returnToMenu;
    }
    public static boolean getReturnToMenu(){
        return returnToMenu;
    }

    public static AD getAd() {
        return ad;
    }

    public static void setAd(AD ad) {
        ADHolder.ad = ad;
    }

}
