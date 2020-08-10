package com.company.controller;

import com.company.model.Activity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityReportController implements Initializable {

    @FXML TableView activityTbl;
    @FXML TableColumn timeCln;
    @FXML TableColumn userCln;
    @FXML TableColumn roleCln;
    @FXML TableColumn actionCln;
    @FXML TableColumn changedCln;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeCln.setCellValueFactory(new PropertyValueFactory<>("time"));
        userCln.setCellValueFactory(new PropertyValueFactory<>("person"));
        roleCln.setCellValueFactory(new PropertyValueFactory<>("role"));
        actionCln.setCellValueFactory(new PropertyValueFactory<>("action"));
        changedCln.setCellValueFactory(new PropertyValueFactory<>("changedInfo"));

        for (Activity activity : Activity.getActivities()) {
            activityTbl.getItems().add(activity);
        }
        activityTbl.refresh();
    }
}
