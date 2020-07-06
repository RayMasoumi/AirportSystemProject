package com.company.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Employee extends Person implements Showable, java.io.Serializable {

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

//write to file :

    public static void writeEmployee() {
        try {
            //create file :
            java.io.FileOutputStream employeeFileOut = new java.io.FileOutputStream("employees.txt");
            //serialized :
            java.io.ObjectOutputStream employeeOut = new java.io.ObjectOutputStream(employeeFileOut);
            employeeOut.writeInt(employees.size());
            for(int i=0 ; i<employees.size() ; i++) {
                employeeOut.writeObject(employees.get(i));
            }
            employeeOut.close();
            employeeFileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read file :

    public static void readEmployee () {
        try {
            java.io.FileInputStream employeeFileIn = new java.io.FileInputStream("employees.txt");
            java.io.ObjectInputStream employeeIn = new java.io.ObjectInputStream(employeeFileIn);
            int size = employeeIn.readInt();
            for (int i=0 ; i<size ; i++) {
                Employee employee = (Employee) employeeIn.readObject();
                employees.add(employee);
            }
            employeeIn.close();
            employeeFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

//show method :

    @Override
    public String show() {
        return null;
    }
}

