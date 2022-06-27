package com.example.divar3.controller;

import DB.User;
import Socket.Request;
import com.example.divar3.ClientHolder;
import com.example.divar3.FileHolder;
import com.example.divar3.HelloController;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignUpController {


    private File image;
    @FXML
    private Label incorrectNumber;

    @FXML
    private ImageView profileImage;


    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameError;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;


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
        File file = new File(HelloController.class.getResource("user.jpg").toString());
    }

    @FXML
    void signUpClicked(ActionEvent event) throws IOException, InterruptedException, ParseException {
        Gson gson = new Gson();
        incorrectNumber.setVisible(false);
        usernameError.setVisible(false);
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
        String firsName = firstNameField.getText();
        String lastName = lastNameField.getText();
        User user = new User(username, password,firsName, lastName,phoneNumber, city,
                "1","1");
        Request request = new Request();
        request.setData(gson.toJson(user));
        request.setId("signUp");
        ClientHolder.getClient().sendRequest(request);
    }

    //this function returns yes if any field is empty
    private boolean isFieldEmpty(){
        try {
            cityChoiceBox.getValue().equals("");
        }
        catch (Exception e){
            return true;
        }
        if (usernameField.getText().equals("") || passwordField.getText().equals("") ||
                phoneNumberField.getText().equals("")|| firstNameField.equals("")
        ||lastNameField.equals("")){
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

    @FXML
    void insertPictureClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        try {
            File file = fileChooser.showOpenDialog(stage);
            Image image = new Image(file.getAbsolutePath());
            profileImage.setImage(image);
            setImage(file);
        }
        catch (Exception exception){
            System.out.println("unknown image");
        }

    }

    private void setImage(File image) {
        this.image = image;
    }

    public void userError(){
        usernameError.setVisible(true);
    }

}





