package com.example.divar3.controller;

import DB.User;
import com.example.divar3.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ProfilePageController {

    @FXML
    private CheckBox bookmark;

    @FXML
    private Label cityField;

    @FXML
    private GridPane grid;

    @FXML
    private Label phoneNumberField;

    @FXML
    private ImageView userPicture;

    @FXML
    private Label usernameField;

    @Override
    public void initialize(){
        User user = UserHolder.getUser();
        usernameField.setText(user.getUsername());
        phoneNumberField.setText(user.getPhoneNumber());
        cityField.setText(user.getCity());

    }

    @FXML
    void phoneNClicked(ActionEvent event) {

    }

    @FXML
    void insertPictureClicked(ActionEvent event) {

    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("menu");
    }


}
