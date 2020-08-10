package com.company.controller;

import com.company.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeMenuController implements Initializable {

@FXML Button feedbackBtn;
@FXML Button flightBtn;
@FXML Button backBtn;
@FXML Button exitBtn;
@FXML Button editBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //pressing feedback button :
        feedbackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //loading feedback page :
                FXMLLoader feedbackLoader = new FXMLLoader(Main.class.getResource("view/GetFeedbackPage.fxml"));
                try {
                    feedbackLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading feedback page");
                }
                Stage feedbackStage = new Stage();
                feedbackStage.setScene(new Scene(feedbackLoader.getRoot()));
                feedbackStage.show();
            }
        });

        //pressing flight button :
        flightBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //***********88
            }
        });

        //pressing edit button :
        editBtn.setOnAction(event -> {

            //load employee edit page :
            FXMLLoader employeeEditPageLoader = new FXMLLoader(Main.class.getResource("view/EmployeeEditPage.fxml"));
            try {
                employeeEditPageLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error loading employee edit page");
            }
            Stage employeeEditStage = new Stage();
            employeeEditStage.setScene(new Scene(employeeEditPageLoader.getRoot()));
            employeeEditStage.show();
        });

        //pressing back button
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //close this page :
                ((Stage)backBtn.getScene().getWindow()).close();

                //load login page :
//                FXMLLoader feedbackLoader = new FXMLLoader(Main.class.getResource("view/LoginPage.fxml"));
//                try {
//                    feedbackLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.println("error loading login page");
//                }
//                Stage feedbackStage = new Stage();
//                feedbackStage.setScene(new Scene(feedbackLoader.getRoot()));
//                feedbackStage.show();



            }
        });

        //pressing exit button
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)exitBtn.getScene().getWindow()).close();
            }
        });
    }
}
