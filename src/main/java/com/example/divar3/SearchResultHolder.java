package com.example.divar3;

import DB.AD;

import java.util.ArrayList;

public class SearchResultHolder {
    private static ArrayList<AD> arrayList = new ArrayList<>();

    public static ArrayList<AD> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<AD> arrayList) {
        SearchResultHolder.arrayList = arrayList;
    }
}
