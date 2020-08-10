package com.company.controller;

import com.company.Main;
import com.company.model.Manager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sun.applet.AppletResourceLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMenuController implements Initializable {

    @FXML Button employeeBtn;
    @FXML Button feedbackBtn;
    @FXML Button passengerBtn;
    @FXML Button ticketBtn;
    @FXML Button editBtn;
    @FXML Button flightBtn;
    @FXML Button airplaneBtn;
    @FXML Button backBtn;
    @FXML Button exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        //pressing employee button :
//        employeeBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                //load employee management :
//                FXMLLoader employeeManagementPageLoader = new FXMLLoader(Main.class.getResource("EmployeeManagement.fxml"));
//                try {
//                    employeeManagementPageLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.println("error loading employee Management page");
//                    Stage employeeManagementStage = new Stage();
//                    employeeManagementStage.setScene(new Scene(employeeManagementPageLoader.getRoot()));
//                    employeeManagementStage.show();
//                }
//            }
//        });


        //pressing employee button :
        employeeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load employee management Page :
                FXMLLoader employeeManagementLoader = new FXMLLoader(Main.class.getResource("view/EmployeeManagement.fxml"));
                try {
                    employeeManagementLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage employeeManagementStage = new Stage();
                employeeManagementStage.setScene(new Scene(employeeManagementLoader.getRoot()));
                employeeManagementStage.show();
            }
        });

        //pressing feedback button :
        feedbackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //loading feedback page :
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/ShowFeedBacksPage.fxml"));
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.getRoot()));
                stage.show();

//                //loading feedback page :
//                FXMLLoader showFeedbackLoader = new FXMLLoader(Main.class.getResource("view/ShowFeedBacksPage"));
//                try {
//                    showFeedbackLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.println("error loading show feedback page");
//                }
//                Stage showFeedbackStage = new Stage();
//                showFeedbackStage.setScene(new Scene(showFeedbackLoader.getRoot()));
//                showFeedbackStage.show();

            }
        });

        //pressing passengers button :
        passengerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //loading passenger management page :
                FXMLLoader passengerManagementLoader = new FXMLLoader(Main.class.getResource("view/PassengerManagement.fxml"));
                try {
                    passengerManagementLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage passengerManagementStage = new Stage();
                passengerManagementStage.setScene(new Scene(passengerManagementLoader.getRoot()));
                passengerManagementStage.show();
            }
        });

        //pressing ticket button :
        ticketBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //************
            }
        });

        //pressing edit button :
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //load manager edit page :
                FXMLLoader managerEditPageLoader = new FXMLLoader(Main.class.getResource("view/ManagerEditPage.fxml"));
                try {
                    managerEditPageLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading manager edit page");
                }
                Stage managerEditStage = new Stage();
                managerEditStage.setScene(new Scene(managerEditPageLoader.getRoot()));
                managerEditStage.show();
            }
        });

//        //pressing edit :
//        editBtn.setOnAction(event -> {
//
//            //load edit page :
//          FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ManagerEditPage.fxml"));
//            try {
//                fxmlLoader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Stage stage = new Stage();
//            stage.setScene(new Scene(fxmlLoader.getRoot()));
//            stage.show();
//
//        });

        //pressing flight button :
        flightBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //**********
            }
        });

        //pressing airplane button :
        airplaneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load airplane Management :
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/AirplaneManagementPage.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.show();
            }
        });

        //pressing back button
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //close this page :
                ((Stage)backBtn.getScene().getWindow()).close();

            }
        });

        //pressing exit button
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)exitBtn.getScene().getWindow()).close();
            }
        });
    }
}
