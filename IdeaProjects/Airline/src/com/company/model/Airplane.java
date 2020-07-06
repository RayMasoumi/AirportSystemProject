package com.company.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Airplane implements Showable, java.io.Serializable {

    public static ArrayList<Airplane> airplanes = new ArrayList<>();

    private String airplaneId;
    private String seats;
    private List<Flight> flights;

//constructors :

    public Airplane(String airplaneId, String seats, List<Flight> flights) {
        this.airplaneId = airplaneId;
        this.seats = seats;
        this.flights = flights;
    }

    public Airplane() {

    }

//setters and getters :

    public static ArrayList<Airplane> getAirplanes() {
        return airplanes;
    }

    public static void setAirplanes(ArrayList<Airplane> airplanes) {
        Airplane.airplanes = airplanes;
    }

    public String getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(String airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }


//show method :

    @Override
    public String show() {
        return null;
    }

//write to file :

    public static void writeAirplane () {
        try {
            //create file :
            java.io.FileOutputStream airplaneOutFile = new java.io.FileOutputStream("airplanes.txt");
            //serialized :
            java.io.ObjectOutputStream airplaneOut = new java.io.ObjectOutputStream(airplaneOutFile);
            airplaneOut.writeInt(airplanes.size());
            for (int i=0 ; i<airplanes.size() ; i++) {
                airplaneOut.writeObject(airplanes.get(i));
            }
            airplaneOut.close();
            airplaneOutFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error/saving to file");
        }
    }

//read from file :

    public static void readAirplane(){
        try {
            java.io.FileInputStream airplaneFileIn = new java.io.FileInputStream("airplanes.txt");
            java.io.ObjectInputStream airplaneIn = new java.io.ObjectInputStream(airplaneFileIn);
            int size = airplanes.size();
            for (int i=0 ; i<size ; i++) {
                Airplane airplane = (Airplane) airplaneIn.readObject();
                airplanes.add(airplane);
            }
            airplaneIn.close();
            airplaneFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

