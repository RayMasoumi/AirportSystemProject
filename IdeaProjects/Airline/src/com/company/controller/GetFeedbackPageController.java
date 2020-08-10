package com.company.controller;

import com.company.Main;
import com.company.model.Feedback;
import com.company.model.Manager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GetFeedbackPageController implements Initializable {

    @FXML TextArea feedbackArea;
    @FXML Button submitBtn;
    @FXML Button backBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //pressing submit :

            submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!feedbackArea.getText().equals("")) {

                    //Feedback.feedBacks.add(feedbackArea.getText());
                    Feedback feedback = new Feedback(feedbackArea.getText());
                    Feedback.feedBacks.add(feedback);

                    //write file :
                    try {
                        Manager.writeAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //close feedback page :
                     ((Stage) submitBtn.getScene().getWindow()).close();

//                    //open previous page (passenger menu) :
//                    FXMLLoader passengerMenu = new FXMLLoader((Main.class.getResource("view/PassengerMenu.fxml")));
//                    try {
//                        passengerMenu.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Stage passengerMenuStage = new Stage();
//                    passengerMenuStage.setScene(new Scene(passengerMenu.getRoot()));
//                    passengerMenuStage.show();

                    //success pop up :

                    FXMLLoader successPopUpLoader = new FXMLLoader(Main.class.getResource
                            ("view/SuccessPopUp.fxml"));
                    try {
                        successPopUpLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage successPopUpStage = new Stage();
                    successPopUpStage.setScene(new Scene(successPopUpLoader.getRoot()));
                    successPopUpStage.show();


                }
            }
        });

        //pressing back :

            backBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    //close feedback page :

                    ((Stage)backBtn.getScene().getWindow()).close();

                    //open previous page (passenger menu) :

                    FXMLLoader passengerMenu = new FXMLLoader((Main.class.getResource("view/PassengerMenu.fxml")));
                    try {
                        passengerMenu.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage passengerMenuStage = new Stage();
                    passengerMenuStage.setScene(new Scene(passengerMenu.getRoot()));
                    passengerMenuStage.show();
                }
            });

    }
}
