package com.example.divar3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class HyperLinkController {

    @FXML
    private Hyperlink hyperLink;
    @FXML
    void hyperLinkClicked(ActionEvent event) {

    }

    public void setText(String s){
        hyperLink.setText(s);
    }

    public void setColor(int i){
        if (i % 4 == 1){
            hyperLink.setStyle("-fx-background-color: \"33ffe3\"");
        }
        else if (i % 4 == 2){
            hyperLink.setStyle("-fx-background-color: \"fffc33\"");
        }
        else if (i % 4 == 3){
            hyperLink.setStyle("-fx-background-color: \"f3322f\"");
        }
        //System.out.println(i);
    }

}
