package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Manager;
import com.company.model.Passenger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PassengerBudgetController implements Initializable {

    @FXML Button backBtn;
    @FXML Button submitBtn;
    @FXML TextField budgetField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (LoginPageController.lEnter
                /* && (Passenger.passengers.get(LoginPageController.loginIndex).getBudget() < budgetField.getText())*/) {

                //pressing submit :
                submitBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        if (!(budgetField.getText().equals(""))) {

                            //set budget :
                            Passenger.passengers.get(LoginPageController.loginIndex).setBudget(budgetField.getText());

                            //report :
                            Activity activity = new Activity();
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            activity.setTime(dateTimeFormatter.format(now));
                            activity.setRole("PASSENGER");
                            activity.setChangedInfo(budgetField.getText());
                            activity.setPerson(Passenger.passengers.get(LoginPageController.loginIndex).getUsername());
                            activity.setAction("increased her credit");
                            Activity.getActivities().add(activity);

                            //write files :
                            try {
                                Manager.writeAll();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("error writing files");
                            }

                            //close :
                            ((Stage) submitBtn.getScene().getWindow()).close();

                            //show previous :
                            FXMLLoader passengerMenuLoader = new FXMLLoader(Main.class.getResource("view/PassengerMenu.fxml"));
                            try {
                                passengerMenuLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("error loading passenger menu");
                            }
                            Stage passengerMenuStage = new Stage();
                            passengerMenuStage.setScene(new Scene(passengerMenuLoader.getRoot()));
                            passengerMenuStage.show();

                            //success pop up :

                            FXMLLoader successPopUpLoader = new FXMLLoader(Main.class.getResource("view/SuccessPopUp.fxml"));
                            try {
                                successPopUpLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("error loading pop up");
                            }
                            Stage successPopUpStage = new Stage();
                            successPopUpStage.setScene(new Scene(successPopUpLoader.getRoot()));
                            successPopUpStage.show();

                        }
                    }
                });
            }

        if (RegisterPageController.rEnter) {

            //pressing submit :
            submitBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (!budgetField.getText().equals("")) {

                        //edit budget :
                        Passenger.passengers.get(RegisterPageController.registerIndex).setBudget(budgetField.getText());

                        //report :
                        Activity activity = new Activity();
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        activity.setTime(dateTimeFormatter.format(now));
                        activity.setRole("PASSENGER");
                        activity.setChangedInfo(budgetField.getText());
                        activity.setPerson(Passenger.passengers.get(RegisterPageController.registerIndex).getUsername());
                        activity.setAction("increased her credit");
                        Activity.getActivities().add(activity);

                        //write files :
                        try {
                            Manager.writeAll();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("error writing files");
                        }

                        //close :
                        ((Stage) submitBtn.getScene().getWindow()).close();

                        //show previous :
                        FXMLLoader passengerMenuLoader = new FXMLLoader(Main.class.getResource("view/PassengerMenu.fxml"));
                        try {
                            passengerMenuLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("error loading passenger menu");
                        }
                        Stage passengerMenuStage = new Stage();
                        passengerMenuStage.setScene(new Scene(passengerMenuLoader.getRoot()));
                        passengerMenuStage.show();

                        //success pop up :

                        FXMLLoader successPopUpLoader = new FXMLLoader(Main.class.getResource("view/SuccessPopUp.fxml"));
                        try {
                            successPopUpLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("error loading pop up");
                        }
                        Stage successPopUpStage = new Stage();
                        successPopUpStage.setScene(new Scene(successPopUpLoader.getRoot()));
                        successPopUpStage.show();

                    }
                }
            });

            //pressing back button :
            backBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //close this page :
                    ((Stage)backBtn.getScene().getWindow()).close();

                    //show previous page (passenger menu) :
                    FXMLLoader passengerMenuLoader = new FXMLLoader(Main.class.getResource("view/PassengerMenu.fxml"));
                    try {
                        passengerMenuLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error loading passenger menu");
                    }
                    Stage passengerMenuStage = new Stage();
                    passengerMenuStage.setScene(new Scene(passengerMenuLoader.getRoot()));
                    passengerMenuStage.show();

                }
            });
        }
    }
}