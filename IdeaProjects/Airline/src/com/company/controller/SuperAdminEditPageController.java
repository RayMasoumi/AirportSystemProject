package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
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
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuperAdminEditPageController implements Initializable {

    @FXML Button backBtn;
    @FXML Button submitBtn;
    @FXML Button exitBtn;
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML TextField emailField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usernameField.setText(Manager.superAdmins.get(LoginPageController.loginIndex).getUsername());
        passwordField.setText(Manager.superAdmins.get(LoginPageController.loginIndex).getPassword());
        emailField.setText(Manager.superAdmins.get(LoginPageController.loginIndex).getEmail());

        //pressing submit button :
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //validate email :
                String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(emailField.getText());

                if (!matcher.matches()) {
                    emailField.setStyle("-fx-background-color: red");
                }

                if (matcher.matches()) {

                    Manager.superAdmins.get(LoginPageController.loginIndex).setUsername(usernameField.getText());
                    Manager.superAdmins.get(LoginPageController.loginIndex).setPassword(passwordField.getText());
                    Manager.superAdmins.get(LoginPageController.loginIndex).setEmail(emailField.getText());

                    //write file :
                    try {
                        Manager.writeAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error writing files");
                    }

                    //close this page :
                    ((Stage) submitBtn.getScene().getWindow()).close();

//                    //show previous page (superAdmin menu) :
//                    FXMLLoader SuperAdminMenuLoader = new FXMLLoader(Main.class.getResource("view/SuperAdminMenu.fxml"));
//                    try {
//                        SuperAdminMenuLoader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println("error loading SuperAdmin menu");
//                    }
//                    Stage SuperAdminMenuStage = new Stage();
//                    SuperAdminMenuStage.setScene(new Scene(SuperAdminMenuLoader.getRoot()));
//                    SuperAdminMenuStage.show();

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

                    //report activity :
                    Calendar calendar = new Calendar() {
                        @Override
                        protected void computeTime() {

                        }

                        @Override
                        protected void computeFields() {

                        }

                        @Override
                        public void add(int field, int amount) {

                        }

                        @Override
                        public void roll(int field, boolean up) {

                        }

                        @Override
                        public int getMinimum(int field) {
                            return 0;
                        }

                        @Override
                        public int getMaximum(int field) {
                            return 0;
                        }

                        @Override
                        public int getGreatestMinimum(int field) {
                            return 0;
                        }

                        @Override
                        public int getLeastMaximum(int field) {
                            return 0;
                        }
                    };
                    Activity activity = new Activity();
                    activity.setTime(calendar.getTime().toString());
                    activity.setChangedInfo(Manager.superAdmins.get(LoginPageController.loginIndex).getUsername() + "  "
                            + Manager.superAdmins.get(LoginPageController.loginIndex).getPassword() + "  "
                            + Manager.superAdmins.get(LoginPageController.loginIndex).getEmail());
                    activity.setAction("edited her profile");
                    activity.setPerson(Manager.superAdmins.get(LoginPageController.loginIndex).getUsername());
                    activity.setRole("SUPERADMIN");
                    Activity.getActivities().add(activity);

                    //write file :
                    try {
                        Manager.writeAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error writing filesttt");
                    }

                }
             }
        });

                    //pressing back button :
                    backBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            //close this page :
                            ((Stage) backBtn.getScene().getWindow()).close();

                            //show previous page (superAdmin menu) :
                            FXMLLoader SuperAdminMenuLoader = new FXMLLoader(Main.class.getResource("view/SuperAdminMenu.fxml"));
                            try {
                                SuperAdminMenuLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("error loading SuperAdmin menu");
                            }
                            Stage SuperAdminMenuStage = new Stage();
                            SuperAdminMenuStage.setScene(new Scene(SuperAdminMenuLoader.getRoot()));
                            SuperAdminMenuStage.show();
                        }
                    });

                    //pressing exit button :
                    exitBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            ((Stage) backBtn.getScene().getWindow()).close();
                        }
                    });
                }
            }