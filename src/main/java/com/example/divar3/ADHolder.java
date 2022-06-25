package com.example.divar3;

import DB.AD;

public class ADHolder {
    private static AD ad;

    public static AD getAd() {
        return ad;
    }

    public static void setAd(AD ad) {
        ADHolder.ad = ad;
    }
}
