package com.example.divar3;

//import Socket.Client;
import com.example.divar3.controller.AdViewController;
import com.example.divar3.controller.PageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//import Socket.TransferInfos;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

public class Main extends Application{
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Socket socket = new Socket("127.0.0.1", 3191);
        //Clientholder.setClient()
        FXMLLoader loader = PageController.open("adView");
        AdViewController adViewController = loader.getController();
        adViewController.addButton();
        //AdViewController adViewController = loader.getController();
        //adViewController.addButton();


    }
}
