package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Employee;
import com.company.model.Manager;
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

public class ManagerEditPageController implements Initializable {

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

        nameField.setText(Manager.managers.get(LoginPageController.loginIndex).getName());
        lastNameField.setText(Manager.managers.get(LoginPageController.loginIndex).getLastName());
        usernameField.setText(Manager.managers.get(LoginPageController.loginIndex).getUsername());
        passwordField.setText(Manager.managers.get(LoginPageController.loginIndex).getPassword());
        phoneNumberField.setText(Manager.managers.get(LoginPageController.loginIndex).getPhoneNumber());
        emailField.setText(Manager.managers.get(LoginPageController.loginIndex).getEmail());

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

                    Manager.managers.get(LoginPageController.loginIndex).setName(nameField.getText());
                    Manager.managers.get(LoginPageController.loginIndex).setLastName(lastNameField.getText());
                    Manager.managers.get(LoginPageController.loginIndex).setUsername(usernameField.getText());
                    Manager.managers.get(LoginPageController.loginIndex).setPassword(passwordField.getText());
                    Manager.managers.get(LoginPageController.loginIndex).setPhoneNumber(phoneNumberField.getText());
                    Manager.managers.get(LoginPageController.loginIndex).setEmail(emailField.getText());

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
                            + "-" + emailField.getText()
                            + "-" + phoneNumberField.getText());
                    activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                    activity.setAction("edited her profile");
                    Activity.getActivities().add(activity);

                    //write file :
                    try {
                        Manager.writeAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error writing files");
                    }

                //close this page :
                ((Stage)submitBtn.getScene().getWindow()).close();

                    //show previous page (Manager menu) :
                    FXMLLoader ManagerMenuLoader = new FXMLLoader(Main.class.getResource("view/ManagerMenu.fxml"));
                    try {
                        ManagerMenuLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error loading manager menu");
                    }
                    Stage ManagerMenuStage = new Stage();
                    ManagerMenuStage.setScene(new Scene(ManagerMenuLoader.getRoot()));
                    ManagerMenuStage.show();

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

                //show previous page (Manager menu) :
                FXMLLoader ManagerMenuLoader = new FXMLLoader(Main.class.getResource("view/ManagerMenu.fxml"));
                try {
                    ManagerMenuLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading manager menu");
                }
                Stage ManagerMenuStage = new Stage();
                ManagerMenuStage.setScene(new Scene(ManagerMenuLoader.getRoot()));
                ManagerMenuStage.show();
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
