package com.company.model;

public abstract class Person {

    private String name;
    private String lastName;
    private String ID;
    private String Username;
    private String Password;
    private String email;

//constructor :

    public Person() {

    }

    public Person(String name, String lastName, String ID, String username, String password, String email) {
        this.name = name;
        this.lastName = lastName;
        this.ID = ID;
        Username = username;
        Password = password;
        this.email = email;
    }

    //getters :

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getID() {
        return ID;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return email;
    }

//setters :

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

