package com.example.divar3.controller;

import DB.AD;
import DB.DBMethods;
import Socket.Request;
import com.example.divar3.ADHolder;
import com.example.divar3.ClientHolder;
import com.example.divar3.HelloController;
import com.example.divar3.UserHolder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class AdPageController {

    @FXML
    private ImageView adImage;

    @FXML
    private Label dateLabel;

    @FXML
    private TextArea detailsField;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private CheckBox bookmark;





    @FXML
    public void initialize(){
        File file = new File("user/ad.jpg");
        adImage.setImage(new Image(file.getAbsolutePath()));
        AD ad = ADHolder.getAd();
        dateLabel.setText(ad.getSubmitDate().toString());
        priceLabel.setText(ad.getPrice() + "$");
        titleLabel.setText(ad.getName());
        detailsField.setText(ad.getInfo());
        phoneNumberLabel.setText(ad.getPhoneNumber());
        if (UserHolder.getUser().getBookmarkIDs().contains(ad.getID())){
            bookmark.setSelected(true);
        }
    }
    @FXML
    void chatClicked(ActionEvent event) throws IOException, ParseException {
        Gson gson = new Gson();
        JsonObject data = new JsonObject();
        Request request = new Request();
        request.setId("getChatBox");
        String username = UserHolder.getUser().getUsername();
        String adOwnerUsername = ADHolder.getAd().getUsername();
        if (username.equals(adOwnerUsername))
        {
            return;
        }
        data.addProperty("username" , username);
        data.addProperty("adOwnerUsername" , adOwnerUsername);
        String jsonData = gson.toJson(data);
        request.setData(jsonData);
        ClientHolder.getClient().sendRequest(request);
    }


    @FXML
    void phoneNumberClicked(ActionEvent event) {
        if(phoneNumberLabel.isVisible()){
            phoneNumberLabel.setVisible(false);
        }
        else {
            phoneNumberLabel.setVisible(true);
        }
    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        if(ADHolder.getReturnToMenu()){
            PageController.open("menu");
        }
        else {
            PageController.open("adView");
        }
    }

    @FXML
    void bookmarkClicked(ActionEvent event) throws IOException, ParseException {
        if (bookmark.isSelected()){
            UserHolder.getUser().addBookmark(ADHolder.getAd().getID());
            UserHolder.updateUserInDataBase();
        }
        else {
            UserHolder.getUser().removeBookmark(ADHolder.getAd().getID());
            UserHolder.updateUserInDataBase();
        }
    }

}
