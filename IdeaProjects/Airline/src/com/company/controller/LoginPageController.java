package com.company.controller;

import com.company.Main;
import com.company.model.Activity;
import com.company.model.Employee;
import com.company.model.Manager;
import com.company.model.Passenger;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML Button loginBtn;
    @FXML Button registerBtn;
    @FXML Button forgotPasswordBtn;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML Label blankUsernameLbl;
    @FXML Label blankPasswordLbl;
    @FXML Button exitBtn;
    @FXML Button superBtn;
    @FXML Label passLbl;

    public static boolean lEnter;
    public static int loginIndex;


//empty username field :

    public static boolean emptyCheck(TextField usernameField, PasswordField passwordField,
                                     Label blankPasswordLbl, Label blankUsernameLbl) {

        boolean empty;
        boolean emptyPass;
        boolean emptyUsername;

        if (usernameField.getText().equals("")) {
            blankUsernameLbl.setStyle("-fx-background-color: red");
            emptyUsername = true;
        } else {
            blankUsernameLbl.setStyle("-fx-background-color: white");
            emptyUsername = false;
        }

        //empty password field :

        if (passwordField.getText().equals("")) {
            blankPasswordLbl.setStyle("-fx-background-color: red");
            emptyPass = true;
        } else {
            blankPasswordLbl.setStyle("-fx-background-color: white");
            emptyPass = false;
        }

        if (emptyUsername || emptyPass ) {
            empty = true;
        }else {
            empty = false;
        }

        return empty;
    }

//check input data after pressing login button :

    public static boolean available = false;
    public static boolean isSuperAdmin = false;
    public static boolean isPassenger = false;
    public static boolean isManager = false;
    public static boolean isEmployee = false;

    public void checkLogin(TextField usernameField, PasswordField passwordField) {

            for (Manager manager : Manager.managers) {
                if (usernameField.getText().equals(manager.getUsername()) &&
                        passwordField.getText().equals(manager.getPassword())) {
                    loginIndex = Manager.managers.indexOf(manager);
                    isManager = true;
                    available = true;
                    break;
                }
               // break;
            }
            if (!available) {
                for (Passenger passenger : Passenger.passengers) {
                    if (usernameField.getText().equals(passenger.getUsername()) &&
                            passwordField.getText().equals(passenger.getPassword())) {
                        loginIndex = Passenger.passengers.indexOf(passenger);
                        available = true;
                        isPassenger = true;
                        break;
                    }
                    //break;
                }
            }
            if (!available) {
                for (Employee employee : Employee.employees) {
                    if (usernameField.getText().equals(employee.getUsername()) &&
                            passwordField.getText().equals(employee.getPassword())) {
                        loginIndex = Employee.employees.indexOf(employee);
                        available = true;
                        isEmployee = true;
                        break;
                    }
                    //break;
                }
            }
//            if (!available) {
//                for (Manager superAdmin : Manager.superAdmins) {
//                    if (usernameField.getText().equals(superAdmin.getUsername()) &&
//                            passwordField.getText().equals(superAdmin.getPassword())) {
//                        loginIndex = Manager.managers.indexOf(superAdmin);
//                        available = true;
//                        isSuperAdmin = true;
//                    }
//                    break;
//                }
//            }

        if (!available) {

            blankUsernameLbl.setText("not found ! enter again");
            blankUsernameLbl.setStyle("-fx-background-color: red");
            blankPasswordLbl.setText("not found ! enter again");
            blankPasswordLbl.setStyle("-fx-background-color: red");
        }

        //after input matches :
        if (available) {

            //login flag :
            lEnter = true;

            if (isEmployee) {

                //close previous page (login page) :
                    ((Stage)loginBtn.getScene().getWindow()).close();

                //employee menu :
                    FXMLLoader employeeMenu = new FXMLLoader(Main.class.getResource("view/EmployeeMenu.fxml"));
                    try {
                        employeeMenu.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error loading employee menu");
                    }

                    Stage employeeMenuStage = new Stage();
                    employeeMenuStage.setScene(new Scene(employeeMenu.getRoot()));
                    employeeMenuStage.show();

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
            else if (isManager) {

                //close previous page (login page) :
                    ((Stage)loginBtn.getScene().getWindow()).close();

                //load manager menu :
                    FXMLLoader managerMenu = new FXMLLoader(Main.class.getResource("view/ManagerMenu.fxml"));
                    try {
                        managerMenu.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("error loading manager menu !");
                    }
                    Stage managerMenuStage = new Stage();
                    managerMenuStage.setScene(new Scene(managerMenu.getRoot()));
                    managerMenuStage.show();

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
            if (isPassenger) {

                //close previous page (register page) :
                ((Stage)loginBtn.getScene().getWindow()).close();

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
//            else if (isSuperAdmin) {
//
//                //close the previous page (login page) :
//                    ((Stage)loginBtn.getScene().getWindow()).close();
//
//                //load super admin menu :
//                    FXMLLoader superAdminMenuLoader = new FXMLLoader(Main.class.getResource("view/SuperAdminMenu.fxml"));
//                    try {
//                        superAdminMenuLoader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println("error loading super admin menu !");
//                    }
//                    Stage superAdminMenuStage = new Stage();
//                    superAdminMenuStage.setScene(new Scene(superAdminMenuLoader.getRoot()));
//                    superAdminMenuStage.show();
//
//                //success pop up :
//                FXMLLoader successPopUpLoader = new FXMLLoader(Main.class.getResource("view/SuccessPopUp.fxml"));
//                try {
//                    successPopUpLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.println("error loading pop up");
//                }
//                Stage successPopUpStage = new Stage();
//                successPopUpStage.setScene(new Scene(successPopUpLoader.getRoot()));
//                successPopUpStage.show();
//            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    //pressing login button :
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //check input for all not S:
                if (!emptyCheck(usernameField,passwordField,blankPasswordLbl,blankUsernameLbl)) {

                    checkLogin(usernameField, passwordField);

                    //report :
                    Activity activity = new Activity();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    activity.setTime(dateTimeFormatter.format(now));
                    if (isEmployee) {
                        activity.setRole("EMPLOYEE");
                        activity.setPerson(Employee.employees.get(loginIndex).getUsername());
                    }
                    else if (isManager) {
                        activity.setRole("MANAGER");
                        activity.setPerson(Manager.managers.get(loginIndex).getUsername());
                    }
                    else if (isPassenger) {
                        activity.setRole("PASSENGER");
                        activity.setPerson(Passenger.passengers.get(loginIndex).getUsername());
                    }
                    else if (isSuperAdmin) {
                        activity.setRole("SUPERADMIN");
                        activity.setPerson(Manager.superAdmins.get(loginIndex).getUsername());
                    }
                    activity.setChangedInfo(" - ");
                    activity.setAction("logged in");
                    Activity.getActivities().add(activity);
//
//                    //check super admin existence :
//                    if ((Manager.superAdminExists())) {
//
//                        //check input data :
//                        checkLogin(usernameField,passwordField);
//
//                    }
//                    if (!Manager.superAdminExists()){
//                        //create super admin :
//                        try {
//                            Manager.addSuperAdmin();
//
//                            //load superAdmin menu :
//                            FXMLLoader SuperAdminMenuLoader = new FXMLLoader(Main.class.getResource("view/SuperAdminMenu.fxml"));
//                            try {
//                                SuperAdminMenuLoader.load();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                System.out.println("error loading SuperAdmin menu");
//                            }
//                            Stage SuperAdminMenuStage = new Stage();
//                            SuperAdminMenuStage.setScene(new Scene(SuperAdminMenuLoader.getRoot()));
//                            SuperAdminMenuStage.show();
//
//                            //success pop up :
//                            FXMLLoader successPopUpLoader = new FXMLLoader(Main.class.getResource("view/SuccessPopUp.fxml"));
//                            try {
//                                successPopUpLoader.load();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                System.out.println("error loading pop up");
//                            }
//                            Stage successPopUpStage = new Stage();
//                            successPopUpStage.setScene(new Scene(successPopUpLoader.getRoot()));
//                            successPopUpStage.show();
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            }
        });

     //pressing super admin button :
        superBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (Manager.superAdminExists()) {

                    if (!emptyCheck(usernameField, passwordField, blankPasswordLbl, blankUsernameLbl)) {

                        //check if it exists:
                        for (Manager superAdmin : Manager.superAdmins) {
                            if (usernameField.getText().equals(superAdmin.getUsername()) &&
                                    passwordField.getText().equals(superAdmin.getPassword())) {
                                loginIndex = Manager.managers.indexOf(superAdmin);
                                available = true;
                                isSuperAdmin = true;
                                break;
                            }
                        }

                        if (isSuperAdmin) {

                            //report :
                            Activity activity = new Activity();
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            activity.setTime(dateTimeFormatter.format(now));
                            activity.setRole("SUPERADMIN");
                            activity.setChangedInfo(" - ");
                            activity.setPerson("superAdmin");
                            activity.setAction("logged in");
                            Activity.getActivities().add(activity);
                            //close the previous page (login page) :
                                ((Stage)loginBtn.getScene().getWindow()).close();

                            //load super admin menu :
                                FXMLLoader superAdminMenuLoader = new FXMLLoader(Main.class.getResource("view/SuperAdminMenu.fxml"));
                                try {
                                    superAdminMenuLoader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.out.println("error loading super admin menu !");
                                }
                                Stage superAdminMenuStage = new Stage();
                                superAdminMenuStage.setScene(new Scene(superAdminMenuLoader.getRoot()));
                                superAdminMenuStage.show();

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

                    }
                }

                //if doesn't exist :
                if (!Manager.superAdminExists()){
                        //create super admin :
                        try {
                            Manager.addSuperAdmin();

                            //load superAdmin menu :
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

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            }
        });


     //pressing forgot password :
        forgotPasswordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //load forgot password page :
                FXMLLoader forgotPasswordLoader = new FXMLLoader(Main.class.getResource("view/ForgotPassword.fxml"));
                try {
                    forgotPasswordLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error loading forgot password page");
                }
                Stage forgotPasswordStage = new Stage();
                forgotPasswordStage.setScene(new Scene(forgotPasswordLoader.getRoot()));
                forgotPasswordStage.show();
            }
        });


     //pressing register :
        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ( (Stage)registerBtn.getScene().getWindow() ).close();

                FXMLLoader registerLoader = new FXMLLoader(Main.class.getResource("view/RegisterPage.fxml"));
                try {
                    registerLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage registerStage = new Stage();
                registerStage.setScene(new Scene(registerLoader.getRoot()));
                registerStage.show();
            }
        });


     //pressing exit :

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //close
                ((Stage)exitBtn.getScene().getWindow()).close();
            }
        });
    }
}

