package com.company.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Manager extends Person implements Showable, java.io.Serializable {

    private String address;
    private String phoneNumber;
    private String salary;
    boolean isSuperAdmin;
    public static ArrayList<Manager> managers = new ArrayList<>();
    public static ArrayList<Manager> superAdmins = new ArrayList<>();

//constructor :

    public Manager(String name, String lastName, String ID, String username, String password, String email,
                   String address, String phoneNumber, String salary) {
        super(name, lastName, ID, username, password, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public Manager() {

    }

//getters and setters :

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(String salary) { this.salary = salary; }

    public String getSalary() { return salary; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public static ArrayList<Manager> getManagers() {
        return managers;
    }

    public static void setManagers(ArrayList<Manager> managers) {
        Manager.managers = managers;
    }

    public static ArrayList<Manager> getSuperAdmins() {
        return superAdmins;
    }

    public static void setSuperAdmins(ArrayList<Manager> superAdmins) {
        Manager.superAdmins = superAdmins;
    }

//show method :

    @Override
    public String show() {
        String info = "";
        info += "name : " + getName() + "- ";
        info += "last name : " + getLastName() + "- ";
        info += "identification : " + getID() + "- ";
        info += "username : " + getUsername() + "- ";
        info += "password : " + getPassword() + "- ";
        info += "email : " + getEmail() + "- ";
        info += "phone number : " + getPhoneNumber() + "- ";
        info += "address : " + getAddress() + "- ";
        info += "salary : " + getSalary();

        return  info;
    }

//check if there is super admin :

    public static boolean superAdminExists() {
        boolean exists;
        exists = new File("manager.txt").exists();
        return exists;
    }

//create a super admin :

    public static void addSuperAdmin() throws IOException {
        //create file :
        java.io.FileOutputStream superAdminFileOut = new java.io.FileOutputStream("superAdmin.txt");
        Manager superAdmin = new Manager();
        superAdmin.setUsername("ray");
        superAdmin.setPassword("1234");
        superAdmin.setEmail("ray.masoumi80@gmail.com");
        superAdmin.isSuperAdmin = true;
        superAdmins.add(superAdmin);
        writeSuperAdmin();
    }

//write managers to file :

    public static void writeManager () throws IOException {

        try {
            //output file :
            java.io.FileOutputStream managerFileOut = new java.io.FileOutputStream("managers.txt");
            //serialize :
            java.io.ObjectOutputStream managerOut = new java.io.ObjectOutputStream(managerFileOut);
            managerOut.writeInt(managers.size());
            for (Manager manager : managers) {
                managerOut.writeObject(manager);
            }
            managerOut.close();
            managerFileOut.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read manager file :

    public static void readManager() {

        try {
            //input file :
            java.io.FileInputStream managerFileIn = new java.io.FileInputStream("managers.txt");
            //serialize :
            java.io.ObjectInputStream managerIn = new java.io.ObjectInputStream(managerFileIn);
            int size = managerIn.readInt();
            for (int i=0 ; i<size ; i++) {
               Manager manager = (Manager) managerIn.readObject();
               managers.add(manager);
            }
            managerIn.close();
            managerFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


//write super admin to file :

    public static void writeSuperAdmin () throws IOException {
        try {
            //output file :
            java.io.FileOutputStream superAdminFileOut = new java.io.FileOutputStream("superAdmin.txt");
            //serialize :
            java.io.ObjectOutputStream superAdminOut = new java.io.ObjectOutputStream(superAdminFileOut);
            superAdminOut.writeInt(superAdmins.size());
            for (Manager superAdmin : superAdmins) {
                superAdminOut.writeObject(superAdmin);
            }
            superAdminOut.close();
            superAdminFileOut.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read super admin file :

    public static void readSuperAdmin() {
        try {
            //input file :
            java.io.FileInputStream superAdminFileIn = new java.io.FileInputStream("superAdmin.txt");
            //serialize :
            java.io.ObjectInputStream superAdminIn = new java.io.ObjectInputStream(superAdminFileIn);
            int size = superAdminIn.readInt();
            for (int i=0 ; i<size ; i++) {
                Manager superAdmin = (Manager) superAdminIn.readObject();
                superAdmins.add(superAdmin);
            }
            superAdminIn.close();
            superAdminFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    ////////////////////////////////          FILE         //////////////////////////////////////

//write ALL FILES :
    public static void writeAll() throws IOException {
        writeManager();
        writeSuperAdmin();
        Airplane.writeAirplane();
        Employee.writeEmployee();
        Flight.writeFlight();
        Feedback.writeFeedback();
        Passenger.writePassenger();
        Ticket.writeTicket();
        Activity.writeActivity();
    }

//read ALL FILES :
    public static void readALl() throws IOException, ClassNotFoundException {
        readManager();
        readSuperAdmin();
        Airplane.readAirplane();
        Employee.readEmployee();
        Flight.readFlight();
        Feedback.readFeedback();
        Passenger.readPassenger();
        Ticket.readTicket();
        Activity.readActivity();
        //Feedback.feedbackWrite(Feedback.feedBacks);

    }


//manager functions :

//###employee management :

// add employee :
// fire(remove) employee :
//edit/watch list of details ;

//###profile management (password etc) :

//###passengers management :
//watch/edit :
//remove :

//###airplane and flight management :
//edit/watch :
//remove :
//add :

//###feedbacks management :
//watch :
//add :
}

