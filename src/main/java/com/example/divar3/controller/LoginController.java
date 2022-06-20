package com.example.divar3.controller;

import com.example.divar3.Network;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void LoginClicked(ActionEvent event) throws IOException {
        if (isFieldEmpty()){
            return;
        }
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            Network.sendData(username);
            Network.sendData(password);
        }
        catch (Exception exception){

        }
    }

    @FXML
    void signUpClicked(ActionEvent event) throws IOException {
        PageController.close();
        FXMLLoader loader = PageController.open("signUp");
        //setter

    }

    private boolean isFieldEmpty (){
        if(usernameField.getText().equals("") || passwordField.getText().equals("")){
            return true;
        }
        else {
            return false;
        }
    }

}
