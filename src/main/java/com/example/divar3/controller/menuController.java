package com.example.divar3.controller;

import Socket.Request;
import com.example.divar3.ClientHolder;
import com.example.divar3.HelloController;
import com.example.divar3.UserHolder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class menuController {

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private Button moreButton;

    @FXML
    private TextField searchTextfield;

    @FXML
    private ChoiceBox<String> tagChoiceBox;

    public void initialize() throws IOException {
        setTagChoiceBox();
    }

    @FXML
    void createAdCliclked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("createAd");
    }

    @FXML
    void profileButtonClicked(ActionEvent event) {

    }

    @FXML
    void returnClicked(ActionEvent event) throws IOException {
        PageController.close();
        PageController.open("loginPage");
    }

    @FXML
    void searchClicked(ActionEvent event) throws IOException, ParseException {
        if (searchTextfield.getText().equals("empty")){
            return;
        }
        String city = UserHolder.getUser().getCity();
        String search = searchTextfield.getText();
        String min = minField.getText();
        String max = maxField.getText();
        String tag = tagChoiceBox.getValue();
        if(tagChoiceBox.getValue() == null){
            tag = "";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("city", city);
        jsonObject.addProperty("search", search);
        jsonObject.addProperty("min", min);
        jsonObject.addProperty("max", max);
        jsonObject.addProperty("tag", tag);
        Gson gson = new Gson();
        Request request = new Request();
        request.setId("search");
        String data = gson.toJson(jsonObject);
        request.setData(data);
        ClientHolder.getClient().sendRequest(request);
    }

    @FXML
    void moreClicked(ActionEvent event) {
        if (!maxField.visibleProperty().getValue()){
            maxField.setVisible(true);
            minField.setVisible(true);
            tagChoiceBox.setVisible(true);
        }
        else {
            emptyMore();
            maxField.setVisible(false);
            minField.setVisible(false);
            tagChoiceBox.setVisible(false);
        }
    }

    //empty nods that appear when more is shown
    public void emptyMore(){
        maxField.setText("");
        minField.setText("");
        tagChoiceBox.setValue("");
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


}
