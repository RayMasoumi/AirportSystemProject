package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Employee;
import com.company.model.Manager;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.jnlp.SingleInstanceListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditEmployeePageController implements Initializable {


    @FXML Button submitBtn;
    @FXML Button backBtn;
    @FXML TextField nameField;
    @FXML TextField lastNameField;
    @FXML TextField idField;
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML TextField emailField;
    @FXML TextField phoneField;
    @FXML TextField addressField;
    @FXML TextField salaryField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        int editIndex = Employee.employees.indexOf(
                EmployeeManagementController.employeeTbl2.getSelectionModel().getSelectedItem());

        nameField.setText(Employee.employees.get(editIndex).getName());
        lastNameField.setText(Employee.employees.get(editIndex).getLastName());
        usernameField.setText(Employee.employees.get(editIndex).getUsername());
        passwordField.setText(Employee.employees.get(editIndex).getPassword());
        idField.setText(Employee.employees.get(editIndex).getID());
        emailField.setText(Employee.employees.get(editIndex).getEmail());
        phoneField.setText(Employee.employees.get(editIndex).getPhoneNumber());
        addressField.setText(Employee.employees.get(editIndex).getAddress());
        salaryField.setText(Employee.employees.get(editIndex).getSalary());

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

                    //editing employee's info :
                    Employee.employees.get(editIndex).setName(nameField.getText());
                    Employee.employees.get(editIndex).setLastName(lastNameField.getText());
                    Employee.employees.get(editIndex).setID(idField.getText());
                    Employee.employees.get(editIndex).setUsername(usernameField.getText());
                    Employee.employees.get(editIndex).setPassword(passwordField.getText());
                    Employee.employees.get(editIndex).setPhoneNumber(phoneField.getText());
                    Employee.employees.get(editIndex).setAddress(addressField.getText());
                    Employee.employees.get(editIndex).setEmail(emailField.getText());
                    Employee.employees.get(editIndex).setSalary(salaryField.getText());

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
                            + "-" + addressField.getText()
                            + "-" + salaryField);
                    activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                    activity.setAction("edited employee " + Employee.employees.get(editIndex).getUsername() + "'s profile");
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
                    EmployeeManagementController.employeeTbl2.getItems().clear();
                    for (int i = 0; i < Employee.employees.size(); i++) {
                        EmployeeManagementController.employeeTbl2.getItems().add(Employee.employees.get(i));
                    }
                    EmployeeManagementController.employeeTbl2.refresh();

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
