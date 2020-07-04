package model;

import java.util.ArrayList;

public class Manager extends Person implements Showable{

    private String address;
    private String phoneNumber;
    public static ArrayList<Manager> managers = new ArrayList<>();
    public static ArrayList<Manager> superAdmins = new ArrayList<>();


//constructor :

    public Manager(String name, String lastName, String ID, String username, String password, String email,
                   String address, String phoneNumber) {
        super(name, lastName, ID, username, password, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String show() {
        return null;
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
