package com.example.divar3.controller;

import DB.AD;
import com.example.divar3.ADHolder;
import com.example.divar3.FileHolder;
import com.example.divar3.SearchResultHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class AdElementController {

    private int indexOfAd;
    @FXML
    private Label addText;

    @FXML
    private Label priceText;

    @FXML
    public void initializeWhenSet(){
        addText.setText(SearchResultHolder.getArrayList().get(indexOfAd).getName());
        priceText.setText(SearchResultHolder.getArrayList().get(indexOfAd).getPrice());
    }

    @FXML
    void buttonClicked(ActionEvent event) throws IOException, ParseException {
        ArrayList<AD> arrayList =SearchResultHolder.getArrayList();
        ADHolder.setAd(arrayList.get(indexOfAd));
        ADHolder.setReturnToMenu(false);
        FileHolder.updateADPicture();
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
