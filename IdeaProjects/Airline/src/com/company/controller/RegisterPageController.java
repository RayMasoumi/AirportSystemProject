package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Manager;
import com.company.model.Passenger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPageController implements Initializable {

    @FXML TextField nameField;
    @FXML TextField lastNameField;
    @FXML TextField emailField;
    @FXML TextField idField;
    @FXML TextField usernameField;
    @FXML TextField budgetField;
    @FXML TextField phoneField;
    @FXML Button backBtn;
    @FXML Button exitBtn;
    @FXML Button registerBtn;
    @FXML PasswordField passwordField;
    @FXML PasswordField passwordField2;
    //@FXML Label passCheckLbl;

    public static int registerIndex;
    public static boolean rEnter; //entered through register page


    //check fields not to be empty :

    public boolean emptyCheck (TextField nameField, TextField lastNameField, TextField usernameField,
                               TextField idField,TextField budgetField, PasswordField passwordField,
                               PasswordField passwordField2, TextField emailField,TextField phoneField ) {

        boolean emptyName;
        boolean emptyLastName;
        boolean emptyBudget;
        boolean emptyId;
        boolean emptyEmail;
        boolean emptyUserName;
        boolean emptyPass;
        boolean emptyPass2;
        boolean emptyPhone;
        boolean empty = false;


        if (nameField.getText().equals("")) {
            nameField.setStyle("-fx-background-color: red");
            emptyName = true;
        }else {
            nameField.setStyle("-fx-background-color: white");
            emptyName = false;
        }
        if (lastNameField.getText().equals("")) {
            nameField.setStyle("-fx-background-color: red");
            emptyLastName = true;
        }else {
            lastNameField.setStyle("-fx-background-color: white");
            emptyLastName = false;
        }
        if (usernameField.getText().equals("")) {
            usernameField.setStyle("-fx-background-color: red");
            emptyUserName = true;
        }else {
            usernameField.setStyle("-fx-background-color: white");
            emptyUserName = false;
        }
        if (passwordField.getText().equals("")) {
            passwordField.setStyle("-fx-background-color: red");
            emptyPass = true;
        }else {
            passwordField.setStyle("-fx-background-color: white");
            emptyPass = false;
        }
        if (idField.getText().equals("")) {
            idField.setStyle("-fx-background-color: red");
            emptyId = true;
        }else {
            idField.setStyle("-fx-background-color: white");
            emptyId = false;
        }
        if (passwordField2.getText().equals("")) {
            passwordField2.setStyle("-fx-background-color: red");
            emptyPass2 = true;
        }else {
            passwordField2.setStyle("-fx-background-color: white");
            emptyPass2=false;
        }
        if (budgetField.getText().equals("")) {
            budgetField.setStyle("-fx-background-color: red");
            emptyBudget = true;
        }else {
            budgetField.setStyle("-fx-background-color: white");
            emptyBudget = false;
        }
        if (emailField.getText().equals("")) {
            emailField.setStyle("-fx-background-color: red");
            emptyEmail = true;
        }else {
            emailField.setStyle("-fx-background-color: white");
            emptyEmail = false;
        } if (phoneField.getText().equals("")) {
            phoneField.setStyle("-fx-background-color: red");
            emptyPhone = true;
        }else {
            phoneField.setStyle("-fx-background-color: white");
            emptyPhone = false;
        }

        //if they're all fill :

        if (emptyBudget || emptyEmail || emptyId || emptyLastName || emptyName || emptyPass || emptyPass2
                || emptyUserName || emptyPhone ) {
            empty = true;
        }else empty = false;

        return empty;

    }


    //check passwords to match :

    public static boolean passMatch(PasswordField passwordField2, PasswordField passwordField) {
        boolean match;
        if (passwordField.getText().equals(passwordField2.getText())) {
            match = true;
        }else {
            match = false;
            passwordField2.setStyle("-fx-background-color: red");
            //passCheckLbl.setStyle("-fx-background-color: red");
        }

        return match;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    //pressing confirm :

        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            //input check :

                //email check :
                boolean email = true;

                if (!emptyCheck(nameField,lastNameField,usernameField,idField,budgetField,passwordField,
                        passwordField2,emailField,phoneField) && passMatch(passwordField2,passwordField) ) {

                    //validate email :

                    //"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
                    //"\\b[w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b"
                    //"^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"

                    String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(emailField.getText());

                    if (matcher.matches()) {

                        //add account :
                        Passenger passenger = new Passenger();
                        passenger.setName(nameField.getText());
                        passenger.setLastName(lastNameField.getText());
                        passenger.setUsername(usernameField.getText());
                        passenger.setPassword(passwordField.getText());
                        passenger.setBudget(budgetField.getText());
                        passenger.setID(idField.getText());
                        passenger.setPhoneNumber(phoneField.getText());
                        passenger.setEmail(emailField.getText());
                        Passenger.passengers.add(passenger);
                        registerIndex = Passenger.passengers.indexOf(passenger);
                        rEnter = true;

                        //report activity :
                        Activity activity = new Activity();
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        activity.setTime(dateTimeFormatter.format(now));
                        activity.setPerson(Passenger.passengers.get(registerIndex).getUsername());
                        activity.setAction("registered");
                        activity.setRole("PASSENGER");
                        activity.setChangedInfo(nameField.getText()
                                + "-" + lastNameField.getText()
                                + "-" + idField.getText()
                                + "-" + usernameField.getText()
                                + "-" + passwordField.getText()
                                + "-" + emailField.getText()
                                + "-" + budgetField.getText()
                                + "-" + phoneField.getText());
                        Activity.getActivities().add(activity);

                        //write file :
                        try {
                            Manager.writeAll();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("error writing file");
                        }

                        //close previous page (register page) :

                        ((Stage)registerBtn.getScene().getWindow()).close();

                        // passengers menu :

                        FXMLLoader passengerMenu = new FXMLLoader((Main.class.getResource("view/PassengerMenu.fxml")));
                        try {
                            passengerMenu.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("error loading passenger menu");
                        }
                        Stage passengerMenuStage = new Stage();
                        passengerMenuStage.setScene(new Scene(passengerMenu.getRoot()));
                        passengerMenuStage.show();

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

                    if (!matcher.matches()) {
                        //passenger.setEmail("wrong");
                        emailField.setStyle("-fx-background-color: red");
                    }
                }
            }
        });

     //pressing back button :

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ((Stage)backBtn.getScene().getWindow()).close();
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
