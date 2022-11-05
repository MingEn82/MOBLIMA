package Entities;

import java.util.ArrayList;

/**
 * This class is used for defining the Cineplex Objects and it's classes.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public class Cineplex {
    /**
     * Array List of Cinemas
     */
    private ArrayList<Cinema> cinemas;

    /**
     * Name of Cinema
     */
    private String cineplexName;

    /**
     * ID of Cineplex
     */
    private String cineplexID;

    /**
     * Constructor for cineplex
     * @param cineplexName   cineplex name
     * @param cineplexID     cineplex ID
     */
    public Cineplex(String cineplexName, String cineplexID) {
        this.cinemas = new ArrayList<Cinema>();
        this.cineplexName = cineplexName;
        this.cineplexID = cineplexID;
    }

    /**
     * This function prints Cineplex Details.
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

    /**
     * This is a getter function for retrieving Cinemas.
     * @return Cinemas Array
     */
    public ArrayList<Cinema> getCinemas() {
        return this.cinemas;
    }

    /**
     * This is a setter function for setting Cinemas.
     * @param cinemas   new cinemas
     */
    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }


    /**
     * This is a function to add a new cinema into the cinema list.
     * @param cinema    new cinema
     */
    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
    }

    /**
     * This is a function to remove a cinema from the cinema list.
     * @param c CinemaObject
     */
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

    /**
     * This is a getter function for retrieving the Cineplex Name.
     * @return Cineplex Name
     */
    public String getCineplexName() {
        return this.cineplexName;
    }

    /**
     * This is a getter function for retrieving the Cineplex ID.
     * @return Cineplex ID
     */
    public String getCineplexID() {
        return this.cineplexID;
    }
    
}
