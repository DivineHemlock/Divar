package com.example.divar3;

//import Socket.Socket.Client;
import DB.AD;
import DB.Chat;
import DB.Message;
import DB.User;
import com.example.divar3.controller.AdViewController;
import com.example.divar3.controller.LoginController;
import com.example.divar3.controller.PageController;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import Socket.*;
//import Socket.TransferInfos;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application{
    public static void main(String[] args) throws IOException {
        Request req = new Request();

        Gson gson = new Gson();

        req.setId("Login");
        req.setData("ali");

        String s = gson.toJson(req);
        System.out.println(s);

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = PageController.open("menu");
        //AdViewController adViewController = loader.getController();
        //adViewController.addButton();
        //AdViewController adViewController = loader.getController();
        //adViewController.addButton();
    }
}
