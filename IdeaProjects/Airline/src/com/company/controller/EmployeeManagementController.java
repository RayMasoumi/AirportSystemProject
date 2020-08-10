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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Initializable {

    @FXML TableView employeeTbl;
    @FXML TableColumn nameCln;
    @FXML TableColumn lastNameCln;
    @FXML TableColumn idCln;
    @FXML TableColumn usernameCln;
    @FXML TableColumn passCln;
    @FXML TableColumn addressCln;
    @FXML TableColumn emailCln;
    @FXML TableColumn numberCln;
    @FXML TableColumn salaryCln;
    @FXML Button addBtn;
    @FXML Button removeBtn;
    @FXML Button editBtn;
    @FXML Button backBtn;

    public static TableView employeeTbl2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        employeeTbl2 = employeeTbl;

        //table view :
        nameCln.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameCln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idCln.setCellValueFactory(new PropertyValueFactory<>("ID"));
        usernameCln.setCellValueFactory(new PropertyValueFactory<>("username"));
        passCln.setCellValueFactory(new PropertyValueFactory<>("password"));
        addressCln.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailCln.setCellValueFactory(new PropertyValueFactory<>("email"));
        numberCln.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        salaryCln.setCellValueFactory(new PropertyValueFactory<>("salary"));

        for (Employee employee : Employee.employees) {
            employeeTbl.getItems().add(employee);
        }

        //pressing add button ;
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load add employee :
                FXMLLoader addEmployeeLoader = new FXMLLoader(Main.class.getResource("view/AddEmployeePage.fxml"));
                try {
                    addEmployeeLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading add employee");
                }
                Stage addEmployeeStage = new Stage();
                addEmployeeStage.setScene(new Scene(addEmployeeLoader.getRoot()));
                addEmployeeStage.show();
            }
        });

        //press edit button :
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load edit page :
                FXMLLoader editEmployeeLoader = new FXMLLoader(Main.class.getResource("view/EditEmployeePage.fxml"));
                try {
                    editEmployeeLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading add employee");
                }
                Stage editEmployeeStage = new Stage();
                editEmployeeStage.setScene(new Scene(editEmployeeLoader.getRoot()));
                editEmployeeStage.show();
            }
        });

        //press remove button :
        removeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //find index :
                int removeIndex = Employee.employees.indexOf(employeeTbl.getSelectionModel().getSelectedItem());

                //report :
                Activity activity = new Activity();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                activity.setTime(dateTimeFormatter.format(now));
                activity.setRole("MANAGER");
                activity.setChangedInfo(" - ");
                activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                activity.setAction("removed " + Employee.employees.get(LoginPageController.loginIndex).getUsername());
                Activity.getActivities().add(activity);

                //remove
                Employee.employees.remove(Employee.employees.get(removeIndex));

                //show in tableView :
                employeeTbl.getItems().clear();
                for (int i=0 ; i<Employee.employees.size() ; i++) {
                    employeeTbl.getItems().add(Employee.employees.get(i));
                }
                employeeTbl.refresh();

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
            }
        });
    }
}
