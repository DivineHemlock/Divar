package com.example.divar3.controller;

import com.example.divar3.HelloController;
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

import java.io.File;
import java.io.IOException;
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
    }

    @FXML
    void createAdClicked(ActionEvent event) {
        invalidPriceLabel.setVisible(false);
        if (areFieldsEmpty()){
            return;
        }
        else if (!isPriceValid()){
            invalidPriceLabel.setVisible(true);
            return;
        }
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
    void profileButtonClicked(ActionEvent event) {

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
}
