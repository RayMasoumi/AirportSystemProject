package com.company.controller;

import com.company.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuccessPopUpController implements Initializable {


    @FXML Button okBtn;
    @FXML Label successLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)okBtn.getScene().getWindow()).close();

            }
        });
    }
}
