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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerEditProfileController implements Initializable {

@FXML TextField nameField;
@FXML TextField lastNameField;
@FXML TextField usernameField;
@FXML TextField passwordField;
@FXML TextField phoneNumberField;
@FXML TextField emailField;
@FXML Button backBtn;
@FXML Button submitBtn;
@FXML Button exitBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (RegisterPageController.rEnter) {
            nameField.setText(Passenger.passengers.get(RegisterPageController.registerIndex).getName());
            lastNameField.setText(Passenger.passengers.get(RegisterPageController.registerIndex).getLastName());
            usernameField.setText(Passenger.passengers.get(RegisterPageController.registerIndex).getUsername());
            passwordField.setText(Passenger.passengers.get(RegisterPageController.registerIndex).getPassword());
            phoneNumberField.setText(Passenger.passengers.get(RegisterPageController.registerIndex).getPhoneNumber());
            emailField.setText(Passenger.passengers.get(RegisterPageController.registerIndex).getEmail());

            //pressing submit button :
            submitBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    //validate email :
                    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(emailField.getText());

                    if (!matcher.matches()) {
                        emailField.setStyle("-fx-background-color: red");
                    }

                    if (matcher.matches()) {

                        Passenger.passengers.get(RegisterPageController.registerIndex).setName(nameField.getText());
                        Passenger.passengers.get(RegisterPageController.registerIndex).setLastName(lastNameField.getText());
                        Passenger.passengers.get(RegisterPageController.registerIndex).setUsername(usernameField.getText());
                        Passenger.passengers.get(RegisterPageController.registerIndex).setPassword(passwordField.getText());
                        Passenger.passengers.get(RegisterPageController.registerIndex).setPhoneNumber(phoneNumberField.getText());
                        Passenger.passengers.get(RegisterPageController.registerIndex).setEmail(emailField.getText());

                        //report :
                        Activity activity = new Activity();
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        activity.setTime(dateTimeFormatter.format(now));
                        activity.setRole("PASSENGER");
                        activity.setChangedInfo(nameField.getText()
                                + "-" + lastNameField.getText()
                                + "-" + usernameField.getText()
                                + "-" + passwordField.getText()
                                + "-" + emailField.getText()
                                + "-" + phoneNumberField.getText());
                        activity.setPerson(Passenger.passengers.get(RegisterPageController.registerIndex).getUsername());
                        activity.setAction("edited her profile");
                        Activity.getActivities().add(activity);

                        //write file :
                        try {
                            Manager.writeAll();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //close this page :
                        ((Stage) submitBtn.getScene().getWindow()).close();
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

        if (LoginPageController.lEnter) {
            nameField.setText(Passenger.passengers.get(LoginPageController.loginIndex).getName());
            lastNameField.setText(Passenger.passengers.get(LoginPageController.loginIndex).getLastName());
            usernameField.setText(Passenger.passengers.get(LoginPageController.loginIndex).getUsername());
            passwordField.setText(Passenger.passengers.get(LoginPageController.loginIndex).getPassword());
            phoneNumberField.setText(Passenger.passengers.get(LoginPageController.loginIndex).getPhoneNumber());
            emailField.setText(Passenger.passengers.get(LoginPageController.loginIndex).getEmail());

            //pressing submit button :
            submitBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    //validate email :
                    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(emailField.getText());

                    if (!matcher.matches()) {
                        emailField.setStyle("-fx-background-color: red");
                    }

                    if (matcher.matches()) {

                        Passenger.passengers.get(LoginPageController.loginIndex).setName(nameField.getText());
                        Passenger.passengers.get(LoginPageController.loginIndex).setLastName(lastNameField.getText());
                        Passenger.passengers.get(LoginPageController.loginIndex).setUsername(usernameField.getText());
                        Passenger.passengers.get(LoginPageController.loginIndex).setPassword(passwordField.getText());
                        Passenger.passengers.get(LoginPageController.loginIndex).setPhoneNumber(phoneNumberField.getText());
                        Passenger.passengers.get(LoginPageController.loginIndex).setEmail(emailField.getText());

                        //report :
                        Activity activity = new Activity();

                        activity.setRole("PASSENGER");
                        activity.setChangedInfo(nameField.getText()
                                + "-" + lastNameField.getText()
                                + "-" + usernameField.getText()
                                + "-" + passwordField.getText()
                                + "-" + emailField.getText()
                                + "-" + phoneNumberField.getText());
                        activity.setPerson(Passenger.passengers.get(LoginPageController.loginIndex).getUsername());
                        activity.setAction("edited her profile");
                        Activity.getActivities().add(activity);

                        //write file :
                        try {
                            Manager.writeAll();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //close this page :
                        ((Stage) submitBtn.getScene().getWindow()).close();
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

        //pressing exit button :
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)exitBtn.getScene().getWindow()).close();
            }
        });


    }
}
