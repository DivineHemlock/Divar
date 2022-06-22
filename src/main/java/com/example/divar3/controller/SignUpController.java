package com.example.divar3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigInteger;

public class SignUpController {


    @FXML
    private Label incorrectNumber;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private ChoiceBox<?> cityChoiceBox;

    @FXML
    void LoginClicked(ActionEvent event) throws IOException {
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
        String password = passwordField.getText();
        String username = usernameField.getText();
        String phoneNumber = phoneNumberField.getText();
        //User user = new user(pas city num name);
        //string data = json
        //Client client = ClientHolder.get();
        //Request request ..
        //client.main()
    }

    private boolean isFieldEmpty(){
        if (usernameField.getText().equals("") || passwordField.getText().equals("") ||
                phoneNumberField.getText().equals("")){
            return true;
        }
        else {
            return false;
        }
    }

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

}





