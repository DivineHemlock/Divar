package com.example.divar3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class menuController {

    //private User user; + setter;

    @FXML
    private ChoiceBox<String> citiesChoicebox;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private TextField searchTextfield;

    @FXML
    void profileButtonClicked(ActionEvent event) {

    }

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
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        ObservableList<String> observableList = FXCollections.observableArrayList(arrayList);
        citiesChoicebox.setItems(observableList);

    }

}
