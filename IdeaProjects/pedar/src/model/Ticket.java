package model;

import java.util.ArrayList;

public class Ticket implements Showable {

    private String id;
    private String price;
    private String fine;
    public static ArrayList<Ticket> tickets = new ArrayList<>();

//constructor :

    public Ticket(String id, String price, String fine) {
        this.id = id;
        this.price = price;
        this.fine = fine;
    }

//setters and getters :

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    @Override
    public String show() {
        return null;
    }
}
