package com.example.divar3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class menuController {

    //private User user; + setter;

    @FXML
    private ChoiceBox<?> citiesChoicebox;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private TextField searchTextfield;

    @FXML
    void createAdCliclked(ActionEvent event) throws IOException {
        PageController.close();
        FXMLLoader loader = PageController.open("createAd");
        // setters
    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("loginPage");
    }

    @FXML
    void searchClicked(ActionEvent event) {
        String from = fromTextField.getText();
        String to = toTextField.getText();
        String search = searchTextfield.getText();
        System.out.println("search : " + search + " from " + from + " to " + to );
    }

}
