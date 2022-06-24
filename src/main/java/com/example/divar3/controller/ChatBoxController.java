package com.example.divar3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatBoxController {

    @FXML
    private VBox chatBox;

    @FXML
    private TextField messageTextField;

    public void initialize(){
        for (int i = 1; i <= 10; i++){
            Label label = new Label("message from reciver -20");
            label.setStyle("-fx-background-radius: 100");
            label.setStyle("-fx-background-color :  \"000000\"");
            label.setPrefWidth(chatBox.getPrefWidth() -20);
            label.setAlignment(Pos.CENTER_RIGHT);
            chatBox.getChildren().add(label);
        }
    }
    @FXML
    void profileButtonClicked(ActionEvent event) {

    }

    @FXML
    void returnClicked(ActionEvent event) {

    }

    @FXML
    void sendClicked(ActionEvent event) {

    }

}
