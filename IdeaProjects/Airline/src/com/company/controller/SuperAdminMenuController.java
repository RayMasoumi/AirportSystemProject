package com.company.controller;

import com.company.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuperAdminMenuController implements Initializable {


@FXML Button managerBtn;
@FXML Button activityBtn;
@FXML Button editBtn;
@FXML Button backBtn;
@FXML Button exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //pressing manager button :
        managerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load manager management :
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

        //pressing financial button :
        activityBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //loading activity report page :
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/ActivityReportPage.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading activity report page");
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
                FXMLLoader SuperAdminEditPageLoader = new FXMLLoader(Main.class.getResource("view/SuperAdminEditPage.fxml"));
                try {
                    SuperAdminEditPageLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading SuperAdmin edit page");
                }
                Stage SuperAdminEditStage = new Stage();
                SuperAdminEditStage.setScene(new Scene(SuperAdminEditPageLoader.getRoot()));
                SuperAdminEditStage.show();
            }
        });

        //pressing back button :
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //close this page :
                ((Stage)backBtn.getScene().getWindow()).close();

                //show previous page :
                FXMLLoader loginPageLoader = new FXMLLoader(Main.class.getResource("view/LoginPage.fxml"));
                try {
                    loginPageLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading login page");
                }
                Stage loginStage = new Stage();
                loginStage.setScene(new Scene(loginPageLoader.getRoot()));
                loginStage.show();
            }
        });

        //pressing exit button :
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)exitBtn.getScene().getWindow()).close();
            }
        });
    }
}
