package com.example.divar3.controller;

import com.example.divar3.Network;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SignUpController {

    //private String cities  + setter
    @FXML
    private PasswordField passwordField;


    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    // label for incorrect phone number
    @FXML
    private Label incorrectNumber;


    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("loginPage");
    }

    @FXML
    void signUpClicked(ActionEvent event) throws IOException, InterruptedException {
        if (isFieldEmpty()){
            return;
        }
        if (!isPhoneNumber()){
            incorrectNumber.setVisible(true);
            return;
        }
        // User user = new User (name, city, password, phone)
        // send user
        String password = passwordField.getText();
        String username = usernameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String[] datas = new String[3];
        datas[0] = username;
        datas[1] = password;
        datas[2] = phoneNumber;
        try {
            Gson gson = new Gson();
            String data = gson.toJson(datas);
            Network.sendData(data);
        }
        catch (Exception e){
            System.out.println("gg");
            usernameField.setText("could not connect");
        }
    }

    // the function below checks the validity of a phone number
    private boolean isPhoneNumber (){
        String phoneNumber =  phoneNumberField.getText();
        boolean is = true;
        try {
            BigInteger bigInteger = new BigInteger(phoneNumber);
        }
        catch (Exception exception){
            is = false;
        }
        if (phoneNumber.charAt(0) != '0' || phoneNumber.charAt(1) != '9'){
            is = false;
        }
        if (phoneNumber.length() != 11){
            is = false;
        }
        return is;
    }

    private boolean isFieldEmpty(){
        if (usernameField.getText().equals("") || passwordField.getText().equals("") || phoneNumberField.getText().equals("")){
            return true;
        }
        else {
            return false;
        }
    }


}
