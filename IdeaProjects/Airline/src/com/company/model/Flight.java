package com.company.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Showable, java.io.Serializable {

    private String flightId;
    private Airplane airplane;
    private Ticket ticket;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private String soldTickets;
    private List<Passenger> passengers;
    private String duration;
    public enum FlightStatus { DEPARTED,LANDED,FLYING;}
    public static ArrayList<Flight> flights = new ArrayList<>();

//constructors :

    public Flight() {
    }

    public Flight(String flightId, Airplane airplane, Ticket ticket, String origin, String destination, String date,
                  String time, String soldTickets, List<Passenger> passengers, String duration) {
        this.flightId = flightId;
        this.airplane = airplane;
        this.ticket = ticket;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.soldTickets = soldTickets;
        this.passengers = passengers;
        this.duration = duration;
    }

//setters and getters :

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(String soldTickets) {
        this.soldTickets = soldTickets;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static void setFlights(ArrayList<Flight> flights) {
        Flight.flights = flights;
    }

    //write file :

    public static void writeFlight() {
        try {
            //create file :
            java.io.FileOutputStream flightFileOut = new java.io.FileOutputStream("flights.txt");
            //serialized :
            java.io.ObjectOutputStream flightOut = new java.io.ObjectOutputStream(flightFileOut);
            flightOut.writeInt(flights.size());
            for (int i=0 ; i<flights.size() ; i++)  {
                flightOut.writeObject(flights.get(i));
            }
            flightOut.close();
            flightFileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read file :

    public static void readFlight () {
        try {
            java.io.FileInputStream flightFileIn = new java.io.FileInputStream("flights.txt");
            java.io.ObjectInputStream flightIn = new java.io.ObjectInputStream(flightFileIn);
            int size = flightIn.readInt();
            for (int i=0 ; i<size ; i++) {
                Flight flight = (Flight) flightIn.readObject();
                flights.add(flight);
            }
            flightIn.close();
            flightFileIn.close();
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

