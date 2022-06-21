package com.example.divar3.controller;

import com.example.divar3.HelloController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;

public class AdViewController {

    @FXML
    private GridPane grid;

    public void addButton() throws IOException {
        int columns = 0;
        int rows = 2;
        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("adViewReturnButton.fxml"));
        AnchorPane anchorPane = loader.load();
        grid.add(anchorPane,1, 1);
        grid.setVgap(22);
        for (int i = 0; i < 50; i ++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HelloController.class.getResource("hyperLink.fxml"));
            Hyperlink hyperlink = fxmlLoader.load();
            if (columns == 2){
                columns = 0;
                rows ++;
            }
            HyperLinkController hyperLinkController = fxmlLoader.getController();
            hyperLinkController.setColor(i);
            grid.add(hyperlink, columns++, rows);
        }
    }

}
