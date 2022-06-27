package com.example.divar3.controller;

import DB.User;
import Socket.Client;
import Socket.Request;
import com.example.divar3.ClientHolder;
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
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

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
        File file = new File("user/user.jpg");
        userPicture.setImage(new Image(file.getAbsolutePath()));
        if(UserHolder.getUser().getIsNumberPublic().equals("1")){
            phoneNumberView.setSelected(true);
        }
        else{
            phoneNumberView.setSelected(false);
        }
    }
    @FXML
    void bookmarkClicked(ActionEvent event) throws IOException, ParseException {
        Request request = new Request();
        request.setId("findBookmark");
        request.setData(UserHolder.getUser().getUsername());
        ClientHolder.getClient().sendRequest(request);
    }



    @FXML
    void phoneNClicked(ActionEvent event) throws IOException, ParseException {
        if (!phoneNumberView.isSelected()){
            UserHolder.getUser().setIsNumberPublic("0");
            UserHolder.updateUserInDataBase();
        }
        else {
            UserHolder.getUser().setIsNumberPublic("1");
            UserHolder.updateUserInDataBase();
        }

    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("menu");
    }

}
