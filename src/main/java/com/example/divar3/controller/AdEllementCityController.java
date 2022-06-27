package com.example.divar3.controller;

import DB.AD;
import com.example.divar3.ADHolder;
import com.example.divar3.CitySearchHolder;
import com.example.divar3.SearchResultHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class AdEllementCityController {
    private int indexOfAd;
    @FXML
    private Label addText;

    @FXML
    private Label priceText;

    @FXML
    public void initializeWhenSet(){
        addText.setText(CitySearchHolder.getArrayList().get(indexOfAd).getName());
        priceText.setText(CitySearchHolder.getArrayList().get(indexOfAd).getPrice());
    }

    @FXML
    void buttonClicked(ActionEvent event) throws IOException {
        ArrayList<AD> arrayList = CitySearchHolder.getArrayList();
        ADHolder.setReturnToMenu(true);
        ADHolder.setAd(arrayList.get(indexOfAd));
        PageController.close();
        PageController.open("AdPage");
    }

    public int getIndexOfAd() {
        return indexOfAd;
    }

    public void setIndexOfAd(int indexOfAd) {
        this.indexOfAd = indexOfAd;
    }
}