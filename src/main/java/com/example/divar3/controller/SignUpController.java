package com.example.divar3.controller;

import com.example.divar3.HelloController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private Label usernameError;

    @FXML
    private ChoiceBox<String> cityChoiceBox;

    @FXML
    void LoginClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("loginPage");
    }

    //initialize for setting choice box
    public void initialize() throws IOException {
        setCityChoiceBox();
    }

    @FXML
    void signUpClicked(ActionEvent event) throws IOException, InterruptedException {
        incorrectNumber.setVisible(false);
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
        String city = cityChoiceBox.getValue();
        //User user = new user(pas city num name);
        //string data = json
        //Client client = ClientHolder.get();
        //Request request ..
        //client.main()
    }

    //this function returns yes if any field is empty
    private boolean isFieldEmpty(){
        String s = cityChoiceBox.getValue();
        if (usernameField.getText().equals("") || passwordField.getText().equals("") ||
                phoneNumberField.getText().equals("") || s.equals("")){
            return true;
        }
        else {
            return false;
        }
    }

    // checks validity of phone number
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

    // set choice box based on a file
    private void setCityChoiceBox () throws IOException {
        File file = new File(HelloController.class.getResource("cities.txt").getFile());
        Scanner reader = new Scanner(file);
        ArrayList<String> cities = new ArrayList<>();
        while (reader.hasNextLine()){
            cities.add(reader.nextLine());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(cities);
        cityChoiceBox.setItems(observableList);
    }

}




