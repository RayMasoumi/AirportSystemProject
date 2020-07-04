package model;

import java.util.ArrayList;

public class Passenger extends Person implements Showable {

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

//functions :

//register first !

//###profile management (change password and other details) :

//###increase budget ;

//###send feedback :


    @Override
    public String show() {
        return null;
    }
}
