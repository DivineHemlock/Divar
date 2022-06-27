package com.example.divar3.controller;

import DB.AD;
import DB.User;
import Socket.Request;
import com.example.divar3.ClientHolder;
import com.example.divar3.FileHolder;
import com.example.divar3.HelloController;
import com.example.divar3.UserHolder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateAdController {

    private File image;
    @FXML
    private ImageView adImageField;

    @FXML
    private Label invalidPriceLabel;

    @FXML
    private TextArea detailsTextField;


    @FXML
    private TextField priceTextField;

    @FXML
    private ChoiceBox<String> tagChoiceBox;

    @FXML
    private TextField titleTextField;

    public void initialize() throws IOException {
        setTagChoiceBox();
        image = new File("ad.jpg");
    }

    @FXML
    void createAdClicked(ActionEvent event) throws IOException, ParseException {
        Gson gson = new Gson();
        invalidPriceLabel.setVisible(false);
        if (areFieldsEmpty()){
            return;
        }
        else if (!isPriceValid()){
            invalidPriceLabel.setVisible(true);
            return;
        }
        String title = titleTextField.getText();
        String price = standardPrice(priceTextField.getText());
        String tag = tagChoiceBox.getValue();
        String details = detailsTextField.getText();
        String city = UserHolder.getUser().getCity();
        String phoneNumber = "not available";
        System.out.println(UserHolder.getUser().getIsNumberPublic() + "*********");
        if (UserHolder.getUser().getIsNumberPublic().equals("1")){
            phoneNumber = UserHolder.getUser().getPhoneNumber();
        }
        String username = UserHolder.getUser().getUsername();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", title);
        jsonObject.addProperty("price", price);
        jsonObject.addProperty("tag", tag);
        jsonObject.addProperty("details",details);
        jsonObject.addProperty("city", city);
        jsonObject.addProperty("phoneNumber", phoneNumber);
        jsonObject.addProperty("username", username);
        String data = gson.toJson(jsonObject);
        Request request = new Request();
        request.setId("createAd");
        request.setData(data);
        FileHolder.setPic(image);
        ClientHolder.getClient().sendRequest(request);
    }

    @FXML
    void insertPictureClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        try {
            File file = fileChooser.showOpenDialog(stage);
            Image image = new Image(file.getAbsolutePath());
            adImageField.setImage(image);
            setImage(file);
        }
        catch (Exception exception){
            System.out.println("unknown image");
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
        PageController.open("menu");
    }

    private void setImage(File image) {
        this.image = image;
    }

    private File getImage(){
        return this.image;
    }

    private void setTagChoiceBox () throws IOException {
        File file = new File(HelloController.class.getResource("tags.txt").getFile());
        Scanner reader = new Scanner(file);
        ArrayList<String> cities = new ArrayList<>();
        while (reader.hasNextLine()){
            cities.add(reader.nextLine());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(cities);
        tagChoiceBox.setItems(observableList);
    }

    private boolean areFieldsEmpty(){
        try {
            tagChoiceBox.getValue().equals("");
        }
        catch (Exception e){
            return true;
        }
        System.out.println(detailsTextField.getText());
        if(detailsTextField.getText().equals("")
        || priceTextField.getText().equals("")|| titleTextField.getText().equals("")){
            return true;
        }
        else {
            return false;
        }
    }


    private boolean isPriceValid (){
        String price = priceTextField.getText();
        try {
            double num = Double.parseDouble(price);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    private String standardPrice(String price){
        double dPrice = Double.parseDouble(price);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String sPrice = decimalFormat.format(dPrice);
        sPrice = sPrice;
        return sPrice;
    }
}
