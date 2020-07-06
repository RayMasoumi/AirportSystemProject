package com.company.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Passenger extends Person implements Showable, java.io.Serializable {

    private String phoneNumber;
    private String budget;
    public static ArrayList<Passenger> passengers = new ArrayList<>();

//constructor :

    public Passenger(String name, String lastName, String ID, String username, String password, String email,
                     String phoneNumber, String budget) {
        super(name, lastName, ID, username, password, email);
        this.phoneNumber = phoneNumber;
        this.budget = budget;
    }

//setters and getters :

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

//write files :

    public static void writePassenger() {
        try {
            //create file :
            java.io.FileOutputStream passengerFileOut = new java.io.FileOutputStream("passengers.txt");
            //serialize :
            java.io.ObjectOutputStream passengerOut = new java.io.ObjectOutputStream(passengerFileOut);
            passengerOut.writeInt(passengers.size());
            for (int i = 0 ; i<passengers.size() ; i++) {
                passengerOut.writeObject(passengers.get(i));
            }
            passengerOut.close();
            passengerFileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read file :

    public static void readPassenger() {
        try {
            java.io.FileInputStream passengerFileIn = new java.io.FileInputStream("passengers.txt");
            java.io.ObjectInputStream passengerIn = new java.io.ObjectInputStream(passengerFileIn);
            int size = passengerIn.readInt();
            for (int i=0 ; i<size ; i++) {
                Passenger passenger = (Passenger) passengerIn.readObject();
                passengers.add(passenger);
            }
            passengerIn.close();
            passengerFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//functions :

//register first !

//###profile management (change password and other details) :

//###increase budget ;

//###send feedback :

//show method :

    @Override
    public String show() {
        return null;
    }
}
