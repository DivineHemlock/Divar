package com.example.divar3.controller;

import DB.Chat;
import DB.Message;
import Socket.Request;
import com.example.divar3.ChatHolder;
import com.example.divar3.ClientHolder;
import com.example.divar3.UserHolder;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class ChatBoxController {

    @FXML
    private VBox chatBox;

    @FXML
    private TextField messageTextField;

    public void initialize() {
        Chat chat = ChatHolder.getChat();
        ArrayList<Message> messages = chat.getMessages();
        for (int i = 0; i < messages.size();i++){
            Label messageLabel = new Label();
            Message message = messages.get(i);
            messageLabel.setText(message.getText());
            messageLabel.setPrefWidth(600);
            if(message.getSenderUsername().equals(UserHolder.getUser())){
                messageLabel.setStyle("-fx-background-color :   \"5ce18e\"");

            }
            else {
                messageLabel.setStyle("-fx-background-color :   \"ffffff\"");
            }
            chatBox.getChildren().add(messageLabel);
        }
    }
    @FXML
    void profileButtonClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("profile");
    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("adPage");
    }

    @FXML
    void sendClicked(ActionEvent event) throws IOException, ParseException {
        Gson gson = new Gson();
        String sender;
        String reciver;
        sender = UserHolder.getUser().getUsername();
        if(UserHolder.getUser().getUsername().equals(ChatHolder.getChat().getUserAUsername())){
            reciver = ChatHolder.getChat().getUserBUsername();
        }
        else {
            reciver = ChatHolder.getChat().getUserAUsername();
        }
        Message message = new Message(messageTextField.getText(),sender,reciver);
        Request request = new Request();
        request.setId("10");
        request.setData(gson.toJson(message));
        ClientHolder.getClient().sendRequest(request);
    }

}
