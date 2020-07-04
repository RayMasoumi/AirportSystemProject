package model;

import java.util.ArrayList;

public class Flight implements Showable {

    private String flightId;
    private Airplane airplane;
    private Ticket ticket;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private String soldTickets;
    //liste mosaferan (arrayList?)
    private String duration;
    public enum FlightStatus {
        DEPARTED,LANDED,FLYING;
    }
    public static ArrayList<Flight> flights = new ArrayList<>();

    @Override
    public String show() {
        return null;
    }
}
