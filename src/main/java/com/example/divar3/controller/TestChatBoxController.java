package com.example.divar3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class TestChatBoxController {

    @FXML
    private VBox chatBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox chatbox2;


    @FXML
    public void initialize(){
        for (int i = 0; i <= 100; i++){
            Label label = new Label("ssss");
            chatbox2.getChildren().add(label);
        }
    }

}
