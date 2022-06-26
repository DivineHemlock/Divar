module com.example.divar3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires json.simple;


    opens com.example.divar3.controller to com.google.gson, javafx.fxml;
    exports com.example.divar3.controller;
    opens com.example.divar3 to com.google.gson;
    exports com.example.divar3;
    opens Socket to com.google.gson;
    exports Socket;
    opens DB to com.google.gson;
    exports DB;

}