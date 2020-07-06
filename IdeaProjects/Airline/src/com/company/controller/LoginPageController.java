package com.company.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML Button loginBtn;
    @FXML Button registerBtn;
    @FXML Button forgotPasswordBtn;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML Label blankUsernameLbl;
    @FXML Label blankPasswordLbl;


//checking input data to login (username & password) :

//    public void login() throws FileNotFoundException {
//
//        if (model.Manager.superAdminExists()) {
//            //read files
//        }else {
//            model.Manager.addSuperAdmin();
//        }
//    }

//check fields not to be empty :

    public static boolean loginEmptyFiledCheck(TextField usernameField, PasswordField passwordField) {
        boolean empty;
        if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
            empty = true;
        }else empty = false;

        return empty;
    }

//check login input to match :

    //    public static boolean loginMatchCheck (TextField usernameField, PasswordField passwordField) {
//            boolean match;
//            if (usernameField.getText().equals(Manager.su) || passwordField.getText().equals("")) {
//                match
//            }
//    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}

