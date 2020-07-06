package com.company.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Ticket implements Showable, java.io.Serializable {

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

//write file :

    public static void writeTicket() {
        try {
            //create file :
            java.io.FileOutputStream ticketFileOut = new java.io.FileOutputStream("tickets.txt");
            //serialize :
            java.io.ObjectOutputStream ticketOut = new java.io.ObjectOutputStream(ticketFileOut);
            ticketOut.writeInt(tickets.size());
            for (int i = 0 ; i<tickets.size() ; i++) {
                ticketOut.writeObject(tickets.get(i));
            }
            ticketOut.close();
            ticketFileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read file :

    public static void readTicket() {
        try {
            java.io.FileInputStream ticketFileIn = new java.io.FileInputStream("tickets.txt");
            java.io.ObjectInputStream ticketIn = new java.io.ObjectInputStream(ticketFileIn);
            int size = ticketIn.readInt();
            for (int i=0 ; i<size ; i++) {
                Ticket ticket = (Ticket) ticketIn.readObject();
                tickets.add(ticket);
            }
            ticketIn.close();
            ticketFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//show method :

    @Override
    public String show() {
        return null;
    }
}

