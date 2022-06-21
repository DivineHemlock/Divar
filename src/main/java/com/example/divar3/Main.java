package com.example.divar3;

import com.example.divar3.controller.PageController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Client client =
        //Clientholder.setClient()
        PageController.open("loginPage");
    }
}
