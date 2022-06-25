package com.example.divar3;

//import Socket.Client;
import DB.AD;
import DB.Chat;
import DB.Message;
import DB.User;
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
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application{
    public static void main(String[] args) throws IOException {
        User user = new User("alir", "22", "ali", "rezqa","09"
        , "th", "true", "true");
        User user2 = new User("alir", "22", "ali", "rezqa","09"
                , "th", "true", "true");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("cars");
        Chat chat = new Chat("alir", "mh");
        Message message = new Message("hello", user, user2);
        AD ad = new AD("pezhoo" , "la", "2200 $",user,"sss6 \n yyyy",tags);
        AD ad2 = new AD("pride" , "la", "22 $",user,"sss \n yyyy",tags);
        ArrayList<AD> arrayList = new ArrayList<>();
        arrayList.add(ad);
        arrayList.add(ad2);
        SearchResultHolder.setArrayList(arrayList);
        ADHolder.setAd(ad);
        UserHolder.setUser(user);
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = PageController.open("adView");
        //AdViewController adViewController = loader.getController();
        //adViewController.addButton();
        //AdViewController adViewController = loader.getController();
        //adViewController.addButton();
    }
}
