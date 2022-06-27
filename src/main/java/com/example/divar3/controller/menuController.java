package com.example.divar3.controller;

import Socket.Request;
import com.example.divar3.*;
import com.example.divar3.HelloController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class menuController {

    @FXML
    private TextField maxField;

    @FXML
    private GridPane grid;


    @FXML
    private TextField minField;



    @FXML
    private ChoiceBox<String> tagChoiceBox;

    @FXML
    private ChoiceBox<String> searchChoiceBox;


    public void initialize() throws IOException {
        setTagChoiceBox();
        setSearchChoiceBox();
        int columns = 0;
        int rows = 2;
        int size = CitySearchHolder.getArrayList().size();
        grid.setVgap(2);
        if(size == 0){
            return;
        }
        for (int i = 0; i < 50; i ++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HelloController.class.getResource("adElementCity.fxml"));
            AnchorPane adElement = fxmlLoader.load();
            AdEllementCityController adEllementCityController = fxmlLoader.getController();
            adEllementCityController.setIndexOfAd(i);
            adEllementCityController.initializeWhenSet();
            if (columns == 2){
                columns = 0;
                rows ++;
            }
            grid.add(adElement, columns++, rows);
            if (i == size - 1){
                break;
            }
        }

    }

    @FXML
    void createAdCliclked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("createAd");
    }

    @FXML
    void profileButtonClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("profile");
    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("loginPage");
    }

    @FXML
    void searchClicked(ActionEvent event) throws IOException, ParseException {
        Gson gson = new Gson();
        if(!validityOfSearch()){
            minField.setText("");
            maxField.setText("");
            return;
        }
        if(searchChoiceBox.getValue().equals("Price")){
            sendPriceRequest();
        }
        else {
            String tag = tagChoiceBox.getValue();
            Request request = new Request();
            request.setId("searchTag");
            request.setData(tag);
            ClientHolder.getClient().sendRequest(request);
        }

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

    private void setSearchChoiceBox(){
        ArrayList<String> searchMethod = new ArrayList<>();
        searchMethod.add("Price");
        searchMethod.add("Tag");
        ObservableList<String> observableList = FXCollections.observableArrayList(searchMethod);
        searchChoiceBox.setItems(observableList);
    }
    private boolean validityOfSearch(){
        if (searchChoiceBox.getValue() == null){
            return false;
        }
        if(searchChoiceBox.getValue().equals("Price")){
            if (validityOfPrice()){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (tagChoiceBox.getValue() == null){
                return false;
            }
        }
        return true;
    }

    private boolean validityOfPrice(){
        if (minField.getText().equals("")||maxField.getText().equals("")){
            return false;
        }
        try {
            double d1 = Double.parseDouble(minField.getText());
            double d2 = Double.parseDouble(maxField.getText());
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private void sendPriceRequest() throws IOException, ParseException {
        Gson gson = new Gson();
        JsonObject jsonData = new JsonObject();
        String min;
        String max;
        Double d1 = Double.parseDouble(minField.getText());
        Double d2 = Double.parseDouble(maxField.getText());
        if (d1 < d2){
            max = maxField.getText();
            min = minField.getText();
        }
        else {
            max = minField.getText();
            min =maxField.getText();
        }
        Request request = new Request();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("min",min);
        jsonObject.addProperty("max",max);
        request.setData(gson.toJson(jsonObject));
        request.setId("searchPrice");
        ClientHolder.getClient().sendRequest(request);
    }



}
