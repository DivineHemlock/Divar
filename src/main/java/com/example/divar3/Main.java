package com.example.divar3;

//import Socket.Client;
import com.example.divar3.controller.AdViewController;
import com.example.divar3.controller.LoginController;
import com.example.divar3.controller.PageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//import Socket.TransferInfos;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main extends Application{
    public static void main(String[] args) throws IOException {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = PageController.open("chatBox");
        //AdViewController adViewController = loader.getController();
        //adViewController.addButton();
        //AdViewController adViewController = loader.getController();
        //adViewController.addButton();
    }
}
