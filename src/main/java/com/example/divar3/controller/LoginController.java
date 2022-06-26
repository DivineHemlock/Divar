package com.example.divar3.controller;

//import Socket.Socket.Client;
//import Socket.TransferInfos;
import com.example.divar3.ClientHolder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Socket.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label incorectLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void LoginClicked(ActionEvent event) throws IOException, ParseException {
        Gson gson = new Gson();
        if (isFieldEmpty()){
            return;
        }
        String username = usernameField.getText();
        String password = passwordField.getText();
        JsonObject data = new JsonObject();
        data.addProperty("username", username);
        data.addProperty("password", password);
        Request request = new Request();
        request.setId("Login");
        request.setData(gson.toJson(data));
        ClientHolder.getClient().sendRequest(request);
    }

    @FXML
    void signUpClicked(ActionEvent event) throws IOException {
        PageController.close();
        try {
            FXMLLoader loader = PageController.open("signUp");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private boolean isFieldEmpty (){
        if(usernameField.getText().equals("") || passwordField.getText().equals("")){
            return true;
        }
        else {
            return false;
        }
    }
    public void setError(){
        incorectLogin.setVisible(true);
    }

}
