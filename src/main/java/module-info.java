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

    opens com.example.divar3.controller to javafx.fxml;
    exports com.example.divar3;
}