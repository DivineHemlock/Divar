package com.example.divar3.controller;

import com.example.divar3.HelloController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PageController {

    private static Stage stage;

    public static FXMLLoader open(String name) throws IOException
    {
        stage = new Stage();
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource( name +
                    ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return loader;
    }

    public static void close(){
        stage.close();
    }


}
