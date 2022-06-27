package com.example.divar3.controller;

import DB.User;
import com.example.divar3.FileHolder;
import com.example.divar3.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProfilePageController {

    @FXML
    private Label cityField;

    @FXML
    private GridPane grid;

    @FXML
    private Label phoneNumberField;

    @FXML
    private CheckBox phoneNumberView;

    @FXML
    private ImageView userPicture;

    @FXML
    private Label usernameField;

    public void initialize(){
        User user = UserHolder.getUser();
        usernameField.setText(user.getUsername());
        cityField.setText(user.getCity());
        phoneNumberField.setText(user.getPhoneNumber());
    }
    @FXML
    void bookmarkClicked(ActionEvent event) {

    }



    @FXML
    void phoneNClicked(ActionEvent event) {

    }

    @FXML
    void returnClicked(ActionEvent event) {

    }

}
