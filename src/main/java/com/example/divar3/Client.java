package com.example.divar3;

import com.example.divar3.controller.AdViewController;
import com.example.divar3.controller.ChatBoxController;
import com.example.divar3.controller.PageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = PageController.open("loginPage");
        //ChatBoxController chatBoxController = loader.getController();
        //chatBoxController.addMessage();


    }
}
