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
import java.util.UUID;

public class AddAirplanePageController implements Initializable {

@FXML TextField seatField;
@FXML Button submitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       //pressing submit button :
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //add to arrayList :
                Airplane airplane = new Airplane();
                airplane.setAirplaneId(UUID.randomUUID().toString());
                airplane.setSeats(seatField.getText());
                Airplane.airplanes.add(airplane);
                int addIndex = Airplane.airplanes.indexOf(airplane);

                //report :
                Activity activity = new Activity();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                activity.setTime(dateTimeFormatter.format(now));
                activity.setRole("MANAGER");
                activity.setChangedInfo(Airplane.airplanes.get(addIndex).getSeats()
                        +"-"+Airplane.airplanes.get(addIndex).getAirplaneId());
                activity.setPerson(Manager.managers.get(LoginPageController.loginIndex).getUsername());
                activity.setAction("added airplane " + Airplane.airplanes.get(addIndex).getAirplaneId());
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
                AirplaneManagementController.airplanesTbl2.getItems().clear();
                for (int i=0 ; i<Airplane.airplanes.size() ; i++) {
                    AirplaneManagementController.airplanesTbl2.getItems().add(Airplane.airplanes.get(i));
                }
                AirplaneManagementController.airplanesTbl2.refresh();

                //close page :
                ((Stage)submitBtn.getScene().getWindow()).close();
            }
        });
    }
}
