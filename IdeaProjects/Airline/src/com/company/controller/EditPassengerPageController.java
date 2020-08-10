package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Employee;
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

public class EditPassengerPageController implements Initializable {

    @FXML Button submitBtn;
    @FXML Button backBtn;
    @FXML TextField nameField;
    @FXML TextField lastNameField;
    @FXML TextField idField;
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML TextField emailField;
    @FXML TextField phoneField;
    @FXML TextField budgetField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int editIndex = Passenger.passengers.indexOf(
                PassengerManagementController.passengerTbl2.getSelectionModel().getSelectedItem());

        nameField.setText(Passenger.passengers.get(editIndex).getName());
        lastNameField.setText(Passenger.passengers.get(editIndex).getLastName());
        usernameField.setText(Passenger.passengers.get(editIndex).getUsername());
        passwordField.setText(Passenger.passengers.get(editIndex).getPassword());
        idField.setText(Passenger.passengers.get(editIndex).getID());
        emailField.setText(Passenger.passengers.get(editIndex).getEmail());
        phoneField.setText(Passenger.passengers.get(editIndex).getPhoneNumber());
        budgetField.setText(Passenger.passengers.get(editIndex).getBudget());

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

                    //editing passenger's info :
                    Passenger.passengers.get(editIndex).setName(nameField.getText());
                    Passenger.passengers.get(editIndex).setLastName(lastNameField.getText());
                    Passenger.passengers.get(editIndex).setID(idField.getText());
                    Passenger.passengers.get(editIndex).setUsername(usernameField.getText());
                    Passenger.passengers.get(editIndex).setPassword(passwordField.getText());
                    Passenger.passengers.get(editIndex).setPhoneNumber(phoneField.getText());
                    Passenger.passengers.get(editIndex).setBudget(budgetField.getText());
                    Passenger.passengers.get(editIndex).setEmail(emailField.getText());

                    //report :
                    Activity activity = new Activity();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    activity.setTime(dateTimeFormatter.format(now));
                    activity.setRole("MANAGER");
                    activity.setChangedInfo(nameField.getText()
                            + "-" + lastNameField.getText()
                            + "-" + usernameField.getText()
                            + "-" + passwordField.getText()
                            + "-" + idField.getText()
                            + "-" + emailField.getText()
                            + "-" + phoneField.getText()
                            + "-" + budgetField.getText());
                    activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                    activity.setAction("edited " + Passenger.passengers.get(editIndex).getUsername() + "'s profile");
                    Activity.getActivities().add(activity);

                    //write files :
                    try {
                        Manager.writeAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error writing files");
                    }

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

                    //show in tableView :
                    PassengerManagementController.passengerTbl2.getItems().clear();
                    for (int i = 0; i < Passenger.passengers.size(); i++) {
                        PassengerManagementController.passengerTbl2.getItems().add(Passenger.passengers.get(i));
                    }
                    PassengerManagementController.passengerTbl2.refresh();

                    //close page :
                    ((Stage) submitBtn.getScene().getWindow()).close();
                }
            }
        });

        //pressing back button :
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //close this page :
                ((Stage)backBtn.getScene().getWindow()).close();
            }
        });
    }
}
