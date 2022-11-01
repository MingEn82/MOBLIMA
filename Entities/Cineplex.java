package Entities;

import java.util.ArrayList;

/**
 * Cineplex Class
 */
public class Cineplex {
    private ArrayList<Cinema> cinemas;
    private String cineplexName;
    private String cineplexID;

    /**
     * Constructor for cineplex
     * @param cineplexName
     * @param cinepexID
     */
    public Cineplex(String cineplexName, String cinepexID) {
        this.cinemas = new ArrayList<Cinema>();
        this.cineplexName = cineplexName;
        this.cineplexID = cinepexID;
    }

    /**
     * Prints out the cineplex details
     */
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

    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
    }

    public String getCineplexName() {
        return this.cineplexName;
    }

    public String getCineplexID() {
        return this.cineplexID;
    }
    
}
