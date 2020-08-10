package com.company;

import com.company.model.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        //read file :

        Manager.readALl();

        //load login page :

        FXMLLoader loginLoader = new FXMLLoader(Main.class.getResource("view/LoginPage.fxml"));
        try {
            loginLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(loginLoader.getRoot()));
        loginStage.show();
    }


//    public static void main(String[] args) {
//
//
//    }


}
