package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Manager;
import com.company.model.Passenger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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

public class PassengerManagementController implements Initializable {

    @FXML TableView passengerTbl;
    @FXML TableColumn nameCln;
    @FXML TableColumn lastNameCln;
    @FXML TableColumn idCln;
    @FXML TableColumn usernameCln;
    @FXML TableColumn passCln;
    @FXML TableColumn budgetCln;
    @FXML TableColumn emailCln;
    @FXML TableColumn numberCln;
    @FXML Button removeBtn;
    @FXML Button editBtn;
    @FXML Button backBtn;

    public static TableView passengerTbl2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       passengerTbl2 = passengerTbl;

        //table view :
        nameCln.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameCln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idCln.setCellValueFactory(new PropertyValueFactory<>("ID"));
        usernameCln.setCellValueFactory(new PropertyValueFactory<>("username"));
        passCln.setCellValueFactory(new PropertyValueFactory<>("password"));
        budgetCln.setCellValueFactory(new PropertyValueFactory<>("budget"));
        emailCln.setCellValueFactory(new PropertyValueFactory<>("email"));
        numberCln.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        for (Passenger passenger : Passenger.passengers) {
            passengerTbl.getItems().add(passenger);
        }

        //sort?
//        ObservableList data = FXCollections.observableArrayList();
//        SortedList sortedData = new SortedList(data);
//
//        sortedData.comparatorProperty().bind(passengerTbl.comparatorProperty());
//        passengerTbl.setItems(sortedData);
//
//        passengerTbl.getSortOrder().add(idCln);
//
//        data.addAll();

        //pressing remove button :
        removeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //find index :
                int removeIndex = Passenger.passengers.indexOf(passengerTbl.getSelectionModel().getSelectedItem());

                //report :
                Activity activity = new Activity();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                activity.setTime(dateTimeFormatter.format(now));
                activity.setRole("MANAGER");
                activity.setAction(" - ");
                activity.setAction("removed passenger" + Passenger.passengers.get(removeIndex).getUsername());
                activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                Activity.getActivities().add(activity);

                // remove
                Passenger.passengers.remove(Passenger.passengers.get(removeIndex));

                //show in tableView :
                passengerTbl.getItems().clear();
                for (int i=0 ; i<Passenger.passengers.size() ; i++) {
                    passengerTbl.getItems().add(Passenger.passengers.get(i));
                }
                passengerTbl.refresh();

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

        //press edit button :
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load edit page :
                FXMLLoader editLoader = new FXMLLoader(Main.class.getResource("view/EditPassengerPage.fxml"));
                try {
                    editLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading edit passenger");
                }
                Stage editStage = new Stage();
                editStage.setScene(new Scene(editLoader.getRoot()));
                editStage.show();
            }
        });
    }
}
