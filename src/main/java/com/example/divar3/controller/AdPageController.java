package com.example.divar3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
        detailsField.setText("ssssss \n lllll");
    }
    @FXML
    void chatClicked(ActionEvent event) {

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
        PageController.open("adView");
    }

    @FXML
    void bookmarkClicked(ActionEvent event) {
        System.out.println(bookmark.isSelected());
    }

}
