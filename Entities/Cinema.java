package Entities;

import java.util.ArrayList;

/**
 * This is an abstract class for the Cinema. 
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public abstract class Cinema {
    /**
     * Type of Cinema
     */
    protected CinemaType cinemaType;

    /**
     * Arraylist of Showings
     */
    protected ArrayList<Showing> showings;

    /**
     * Name of Cinema
     */
    protected String cinemaName;

    /**
     * Number of Cinema
     */
    protected String cinemaNumber;

    /**
     * Constructor for the Cinema
     * @param cinemaType    cinema type
     * @param showings      all showings of cinema
     * @param cinemaName    name of cinema
     * @param cinemaNumber  number of cinema
     */
    public Cinema(CinemaType cinemaType, ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        this.cinemaType = cinemaType;
        this.showings = showings;
        this.cinemaName = cinemaName;
        this.cinemaNumber = cinemaNumber;
    }

    /**
     * This method prints the details of the cinemas.
     */
    public void print() {
        System.out.println("--------- Cinema Details ---------");
        System.out.println("Cinema Type: " + cinemaType.toString());
        System.out.println("Cinema Name: " + cinemaName);
        System.out.println("Cinema Number: " + cinemaNumber);
        System.err.println("");
        System.out.println("--------- Showings Details ---------");
        if (showings.size() > 0) {
            for (Showing showing : showings) {
                showing.printSeats(getAisles());
            }
        } else {
            System.out.println("No showings");
            System.out.println("");
        }
    }

    /**
     * This is a getter function for the Cinema Type.
     * @return cinema type
     */
    public String getCinemaType() {
        return this.cinemaType.toString();
    }

    /**
     * This is a getter function for the Cinema Name.
     * @return Cinema Name
     */
    public String getCinemaName() {
        return this.cinemaName;
    }

    /**
     * This is a getter function for the Cinema Number.
     * @return Cinema Number
     */
    public String getCinemaNumber() {
        return this.cinemaNumber;
    }

    /**
     * This is a getter function for the showings.
     * @return Showings Array
     */
    public ArrayList<Showing> getShowings() {
        return this.showings;
    }

    /**
     * This is a setter function for setting the showings list.
     * @param showings  new showings
     */
    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }

    /**
     * This is a function to add a new showing into the showing list.
     * @param s Showing Object
     */
    public void addShowing(Showing s) {
        showings.add(s);
    }

    /**
     * This is a function to remove a showing from the showing list.
     * @param s Showing Object
     */
    public void removeShowing(Showing s) {
        int idx = -1;
        int i = 0;
        for (Showing showing : showings) {
            if (showing.getStartDate().compareTo(s.getStartDate()) == 0 && showing.getMovieTitle().equals(s.getMovieTitle())) {
                idx = i;
                break;
            }
            i++;
        }
        
        if (idx > -1) {
            showings.remove(idx);
        }
    }

    /**
     * Abstract function to get indexes of aisles
     * @return  indexes of aisles
     */
    abstract public ArrayList<Integer> getAisles();

    /**
     * Abstract function to get seat arrangement
     * @return seat arrangement
     */
    abstract public String getSeatArrangement();

    /**
     * Abstract function to get seat numbers
     * @return seat numbers
     */
    abstract public String getSeatNumbers();

    /**
     * Abstract function to get screen layout
     * @return screen layout
     */
    abstract public String getScreenLayout();

    /**
     * Abstract function to get entrance layout
     * @return entrance layout
     */
    abstract public String getEntranceLayout();

    /**
     * Abstract function to print screen layout
     */
    abstract public void printScreenLayout();

    /**
     * Abstract function to print seat numbers
     */
    abstract public void printSeatNumbers();

    /**
     * Abstract function to print entrance layout
     */
    abstract public void printEntranceLayout();

    /**
     * Abstract function to get wide seat rows
     * @return wide seat rows
     */
    abstract public String[] getWideSeatRows();

    /**
     * Abstract function to set wide seat rows
     * @param wideSeatRows  new seat rows
     */
    abstract public void setWideSeatRows(String[] wideSeatRows);
}
