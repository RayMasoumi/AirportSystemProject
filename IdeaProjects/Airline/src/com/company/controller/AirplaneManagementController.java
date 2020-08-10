package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Airplane;
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

public class AirplaneManagementController implements Initializable {

@FXML TableView airplanesTbl;
@FXML TableColumn flightsCln;
@FXML TableColumn idCln;
@FXML TableColumn seatsCln;
@FXML Button addBtn;
@FXML Button removeBtn;
@FXML Button editBtn;

public static TableView airplanesTbl2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        airplanesTbl2 = airplanesTbl;

        //table view :
        seatsCln.setCellValueFactory(new PropertyValueFactory<>("seats"));
        flightsCln.setCellValueFactory(new PropertyValueFactory<>("flights"));
        idCln.setCellValueFactory(new PropertyValueFactory<>("airplaneId"));

        airplanesTbl.getItems().clear();
        for (Airplane airplane : Airplane.airplanes) {
            airplanesTbl.getItems().add(airplane);
        }
        airplanesTbl.refresh();

        //pressing add button :
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //loading add page :
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/AddAirplanePage.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading airplane add page");
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.show();
            }
        });

        //pressing edit button :
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //loading edit page :
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/EditAirplanePage.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading airplane edit page");
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.show();
            }
        });

        //pressing remove button :
        removeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //find index :
                int removeIndex = Airplane.airplanes.indexOf(airplanesTbl.getSelectionModel().getSelectedItem());

                //report :
                Activity activity = new Activity();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                activity.setTime(dateTimeFormatter.format(now));
                activity.setRole("MANAGER");
                activity.setChangedInfo(" - ");
                activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                activity.setAction("removed airplane "+ Airplane.airplanes.get(removeIndex).getAirplaneId());
                Activity.getActivities().add(activity);

                //remove
                Airplane.airplanes.remove(Airplane.airplanes.get(removeIndex));

                //show in table view :
                airplanesTbl.getItems().clear();
                for (Airplane airplane : Airplane.airplanes) {
                    airplanesTbl.getItems().add(airplane);
                }
                airplanesTbl.refresh();
            }
        });
    }
}
