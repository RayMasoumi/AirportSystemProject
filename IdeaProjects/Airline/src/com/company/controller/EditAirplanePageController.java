package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Airplane;
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

public class EditAirplanePageController implements Initializable {

    @FXML TextField seatField;
    @FXML Button submitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int editIndex = Airplane.airplanes.indexOf(
                AirplaneManagementController.airplanesTbl2.getSelectionModel().getSelectedItem());

        seatField.setText(Airplane.airplanes.get(editIndex).getSeats());

        //pressing submit :
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //edit airplane's info :
                Airplane.airplanes.get(editIndex).setSeats(seatField.getText());

                //report :
                Activity activity = new Activity();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                activity.setTime(dateTimeFormatter.format(now));
                activity.setRole("MANAGER");
                activity.setChangedInfo(seatField.getText()
                                        + "-" + Airplane.airplanes.get(editIndex).getAirplaneId());
                activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                activity.setAction("edited Airplane " + Airplane.airplanes.get(editIndex).getAirplaneId() + " seats");
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

                //show in table view :
                AirplaneManagementController.airplanesTbl2.getItems().clear();
                for (Airplane airplane : Airplane.airplanes) {
                    AirplaneManagementController.airplanesTbl2.getItems().add(airplane);
                }
                AirplaneManagementController.airplanesTbl2.refresh();

                //close page :
                ((Stage) submitBtn.getScene().getWindow()).close();
            }
        });
    }
}
