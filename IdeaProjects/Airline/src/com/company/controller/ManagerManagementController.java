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

public class ManagerManagementController implements Initializable {

@FXML TableView managerTbl;
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

public static TableView managerTbl2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        managerTbl2 = managerTbl;

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

        for (Manager manager : Manager.managers) {
            managerTbl.getItems().add(manager);
        }

        //pressing add button ;
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load add manager page :
                FXMLLoader addManagerLoader = new FXMLLoader(Main.class.getResource("view/AddManagerPage.fxml"));
                try {
                    addManagerLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading manager add page");
                }
                Stage addManagerStage = new Stage();
                addManagerStage.setScene(new Scene(addManagerLoader.getRoot()));
                addManagerStage.show();

            }
        });

        //pressing edit button :
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load edit manager page :
                FXMLLoader editManagerLoader = new FXMLLoader(Main.class.getResource("view/EditManagerPage.fxml"));
                try {
                    editManagerLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading manager edit page");
                }
                Stage editManagerStage = new Stage();
                editManagerStage.setScene(new Scene(editManagerLoader.getRoot()));
                editManagerStage.show();
            }
        });

        //pressing remove button :
        removeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //find index :
                int removeIndex = Manager.managers.indexOf(managerTbl.getSelectionModel().getSelectedItem());

                //report :
                Activity activity = new Activity();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                activity.setTime(dateTimeFormatter.format(now));
                activity.setRole("SUPERADMIN");
                activity.setChangedInfo(" - ");
                activity.setPerson(Manager.superAdmins.get(LoginPageController.loginIndex).getUsername());
                activity.setAction("removed manager " + Manager.managers.get(removeIndex).getUsername());
                Activity.getActivities().add(activity);
                //remove :
                Manager.managers.remove(Manager.managers.get(removeIndex));

                //show in tableView :
                managerTbl.getItems().clear();
                for (int i=0 ; i<Manager.managers.size() ; i++) {
                    managerTbl.getItems().add(Manager.managers.get(i));
                }
                managerTbl.refresh();

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
