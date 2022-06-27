package com.example.divar3.controller;

import com.example.divar3.ADHolder;
import com.example.divar3.HelloController;
import com.example.divar3.SearchResultHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AdViewController {

    @FXML
    private GridPane grid;

    @FXML
    void profileButtonClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("profile");
    }

    public void initialize() throws IOException {
        int columns = 0;
        int rows = 2;
        int size = SearchResultHolder.getArrayList().size();
        grid.setVgap(2);
        if(size == 0){
            return;
        }
        for (int i = 0; i < 50; i ++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HelloController.class.getResource("adElement.fxml"));
            AnchorPane adElement = fxmlLoader.load();
            AdElementController adElementController = fxmlLoader.getController();
            adElementController.setIndexOfAd(i);
            adElementController.initializeWhenSet();
            if (columns == 2){
                columns = 0;
                rows ++;
            }
            grid.add(adElement, columns++, rows);
            if (i == size - 1){
                break;
            }
        }
    }


    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("menu");
    }

}


