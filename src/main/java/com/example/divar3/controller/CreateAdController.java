package com.example.divar3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CreateAdController {

    @FXML
    private TextArea detailsField;

    @FXML
    private Label invalidPriceLabel;

    @FXML
    private TextField priceField;

    @FXML
    private ChoiceBox<?> tagChoiceBox;

    @FXML
    private TextField titleField;

    @FXML
    void createAdCliclked(ActionEvent event) {
        invisibleErrors();
        if (isEmpty()){
            return;
        }
        if (!isPriceValid()){
            invalidPriceLabel.setVisible(true);
            return;
        }
        String price = priceField.getText();
        String title = titleField.getText();
        String details = detailsField.getText();
        System.out.println(price + " " + title + " " + details);

    }

    //function below checks if fields are empty *** need to add for choice box
    private boolean isEmpty(){
        boolean is = false;
        if (priceField.getText().equals("") || titleField.getText().equals("")){
            is = true;
        }
        return is;
    }

    // remember to add a value for checking 2 digits
    private boolean isPriceValid (){
        boolean is = true;
        try {
            Double num = Double.parseDouble(priceField.getText());
        }
        catch (Exception exception){
            is = false;
        }
        return is;
    }

    // function below set errors invisible
    private void invisibleErrors(){
        invalidPriceLabel.setVisible(false);
    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        FXMLLoader loader = PageController.open("menu");
        // set user name
    }

}
