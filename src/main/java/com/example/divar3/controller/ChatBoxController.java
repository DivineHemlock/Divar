package com.example.divar3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class ChatBoxController {

    @FXML
    private VBox chatBox;

    @FXML
    private TextField messageTextField;

    public void initialize(){
        for (int i = 1; i <= 10; i++){
            Label label = new Label("message from reciver -20");
            label.setStyle("-fx-background-color :   \"C1D3FC\"");
            label.setPrefWidth(chatBox.getPrefWidth() -20);
            label.setAlignment(Pos.CENTER_LEFT);
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
        String message = messageTextField.getText();
        messageTextField.setText("");
        Label label = new Label(message);
        label.setStyle("-fx-background-color :   \"5ce18e\"");
        label.setPrefWidth(chatBox.getPrefWidth() -20);
        label.setAlignment(Pos.CENTER_RIGHT);
        chatBox.getChildren().add(label);
    }

}
