package Entities;

import java.util.ArrayList;

public class Cineplex {
    private ArrayList<Cinema> cinemas;
    private String cineplexName;
    private String cineplexID;

    public Cineplex(String cineplexName, String cinepexID) {
        this.cinemas = new ArrayList<Cinema>();
        this.cineplexName = cineplexName;
        this.cineplexID = cinepexID;
    }

    public void print() {
        System.out.println("--------- Cineplex Details ---------");
        System.out.println("Cineplex Name: " + cineplexName);
        System.out.println("Cineplex ID: " + cineplexID);
        System.out.println("");
        for (Cinema cinema : cinemas) {
            cinema.print();
        }
    }

    public ArrayList<Cinema> getCinemas() {
        return this.cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
    }

    public void removeCinema(Cinema c) {
        int idx = -1;
        int i = 0;
        for (Cinema cinema : cinemas) {
            if (cinema.getCinemaNumber().equals(c.getCinemaNumber())) {
                idx = i;
                break;
            }
            i++;
        }
        
        if (idx > -1) {
            cinemas.remove(idx);
        }
    }

    public String getCineplexName() {
        return this.cineplexName;
    }

    public String getCineplexID() {
        return this.cineplexID;
    }
    
}
