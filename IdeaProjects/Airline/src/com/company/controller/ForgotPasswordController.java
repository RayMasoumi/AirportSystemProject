package com.company.controller;

import com.company.model.Employee;
import com.company.model.Manager;
import com.company.model.Passenger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {

    @FXML Button submitBtn;
    @FXML Button backBtn;
    @FXML TextField answerField;
    @FXML TextField usernameField;
    @FXML Label passwordLbl;
    String role = "";

    String setRole(TextField usernameField, TextField answerField){
        for (Manager manager : Manager.managers) {
            if (usernameField.getText().equals(manager.getUsername())
                    && answerField.getText().equals(manager.getEmail())) {

                role = "manager";
                break;
            }
        }

        for (Passenger passenger : Passenger.passengers) {
            if (usernameField.getText().equals(passenger.getUsername())
                    && answerField.getText().equals(passenger.getEmail())) {

                role = "passenger";
                break;
            }
        }

        for (Manager superAdmin : Manager.superAdmins) {
            if (usernameField.getText().equals(superAdmin.getUsername())
                    && answerField.getText().equals(superAdmin.getEmail())) {

                role = "superAdmin";
                break;
            }
        }

        for (Employee employee : Employee.employees) {
            if (usernameField.getText().equals(employee.getUsername())
                    && answerField.getText().equals(employee.getEmail())) {

                role = "employee";
                break;
            }
        }

        return role;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //pressing submit button :
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                setRole(usernameField, answerField);

                if (role.equals("manager")) {
                    for (Manager manager : Manager.managers) {
                        if (usernameField.getText().equals(manager.getUsername())) {
                            passwordLbl.setText("your password is :" + manager.getPassword());
                            passwordLbl.setStyle("-fx-background-color: black");
                            break;
                        }
                    }
                }

                if (role.equals("superAdmin")) {
                    for (Manager superAdmin : Manager.superAdmins) {
                        if (usernameField.getText().equals(superAdmin.getUsername())) {
                            passwordLbl.setStyle("-fx-background-color: black");
                            passwordLbl.setText("your password is :" + superAdmin.getPassword());
                            break;
                        }
                    }
                }

                if (role.equals("passenger")) {
                    for (int i=0 ; i<Passenger.passengers.size() ; i++) {
                        if (usernameField.getText().equals(Passenger.passengers.get(i).getUsername())) {
                            passwordLbl.setText("your password is : " + Passenger.passengers.get(i).getPassword());
                            passwordLbl.setStyle("-fx-background-color: black");
                            break;
                        }
                    }
                }

                if (role.equals("employee")) {
                    for (Employee employee : Employee.employees) {
                        if (usernameField.getText().equals(employee.getUsername())) {
                            passwordLbl.setText("your password is : " + employee.getPassword());
                            passwordLbl.setStyle("-fx-background-color: black");
                            break;
                        }
                    }
                }

                //write files :
                try {
                    Manager.writeAll();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error writing files");
                }
            }
        });

        //pressing back button :
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)backBtn.getScene().getWindow()).close();
            }
        });
    }
}
