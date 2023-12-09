package com.example.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Button mainBtn;

    @FXML
    void clickbtn(ActionEvent event) {
    event.getEventType();
        System.out.println(event.getEventType());
    }

    @FXML
    void initialize() {
    }

}
