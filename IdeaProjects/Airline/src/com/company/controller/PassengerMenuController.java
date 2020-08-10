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

public class PassengerMenuController implements Initializable {

    @FXML Button exitBtn;
    @FXML Button backBtn;
    @FXML Button budgetBtn;
    @FXML Button ticketBtn;
    @FXML Button editBtn;
    @FXML Button feedbackBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    //pressing exit :

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ((Stage)exitBtn.getScene().getWindow()).close();
        }
    });

     //pressing back :

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)backBtn.getScene().getWindow()).close();

                FXMLLoader registerLoader = new FXMLLoader(Main.class.getResource("view/RegisterPage.fxml"));
                try {
                    registerLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage registerStage = new Stage();
                registerStage.setScene(new Scene(registerLoader.getRoot()));
                registerStage.show();
            }
        });

     //pressing edit :

//        editBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                FXMLLoader passengerEditPageLoader = new FXMLLoader(Main.class.getResource("view/PassengerEditProfile.fxml"));
//                try {
//                    passengerEditPageLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.println("error loading passenger edit page");
//                }
//                Stage passengerEditStage = new Stage();
//                passengerEditStage.setScene(new Scene(passengerEditPageLoader.getRoot()));
//                passengerEditStage.show();
//
//            }
//       });

        //pressing edit :
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load edit page :
                FXMLLoader editLoader = new FXMLLoader(Main.class.getResource("view/PassengerEditProfile.fxml"));
                try {
                    editLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage editStage = new Stage();
                editStage.setScene(new Scene(editLoader.getRoot()));
                editStage.show();
            }
        });

    //pressing increase budget :

        budgetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader passengerBudgetLoader = new FXMLLoader(Main.class.getResource("view/PassengerBudget.fxml"));
                try {
                    passengerBudgetLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading passenger budget page");
                }
                Stage passengerBudgetStage = new Stage();
                passengerBudgetStage.setScene(new Scene(passengerBudgetLoader.getRoot()));
                passengerBudgetStage.show();
            }
        });

    //pressing feedback :

        feedbackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


//          //close menu :
//
//                ((Stage)feedbackBtn.getScene().getWindow()).close();

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

    //pressing buy ticket :

        ticketBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
