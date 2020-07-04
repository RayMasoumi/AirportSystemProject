package model;

import java.util.ArrayList;

public class Employee extends Person implements Showable {

    private String address;
    private String phoneNumber;
    private String salary;
    public static ArrayList<Employee> employees = new ArrayList<>();

 //constructor:

    public Employee(String name, String lastName, String ID, String username, String password, String email,
                    String address, String phoneNumber, String salary) {
        super(name, lastName, ID, username, password, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

 //getters and setters :

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

//functions :

//###profile management:

//edit profile (change password and information) :

//###send feedback :

//###flight management;

//add flight :
//remove flight :
//edit flight :
//watch/edit flight information (each flight and each passengers information separately) :

    @Override
    public String show() {
        return null;
    }
}
