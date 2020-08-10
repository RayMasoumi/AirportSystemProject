package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
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

public class AddManagerPageController implements Initializable {

@FXML Button submitBtn;
@FXML Button backBtn;
@FXML TextField nameField;
@FXML TextField lastNameField;
@FXML TextField idField;
@FXML TextField phoneField;
@FXML TextField usernameField;
@FXML TextField passwordField;
@FXML TextField emailField;
@FXML TextField addressField;
@FXML TextField salaryField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //pressing submit :
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

                    //create a manager and add it to arrayList :
                    Manager manager = new Manager(nameField.getText(), lastNameField.getText(), idField.getText(),
                            usernameField.getText(), passwordField.getText(), emailField.getText(),
                            addressField.getText(), phoneField.getText(), salaryField.getText());
                    Manager.managers.add(manager);
                    int addIndex = Manager.managers.indexOf(manager);

                    //report :
                    Activity activity = new Activity();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    activity.setTime(dateTimeFormatter.format(now));
                    activity.setRole("SUPERADMIN");
                    activity.setChangedInfo(nameField.getText() + "-"
                            + lastNameField.getText()
                            + "-" +idField.getText()
                            + "-" +usernameField.getText()
                            + "-" +passwordField.getText()
                            + "-" +emailField.getText()
                            + "-" + addressField.getText()
                            + "-" +phoneField.getText()
                            + "-" +salaryField.getText());
                    activity.setPerson(Manager.superAdmins.get(LoginPageController.loginIndex).getUsername());
                    activity.setAction("added manager " + Manager.managers.get(addIndex).getUsername());
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

                    //add to table view :
                    ManagerManagementController.managerTbl2.getItems().clear();
                    for (int i = 0; i < Manager.managers.size(); i++) {
                        ManagerManagementController.managerTbl2.getItems().add(Manager.managers.get(i));
                    }
                    ManagerManagementController.managerTbl2.refresh();

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

                //load previous page (manager management) :
                FXMLLoader managerLoader = new FXMLLoader(Main.class.getResource("view/ManagerManagementPage.fxml"));
                try {
                    managerLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading manager table");
                }
                Stage managerStage = new Stage();
                managerStage.setScene(new Scene(managerLoader.getRoot()));
                managerStage.show();
            }
        });
    }
}
