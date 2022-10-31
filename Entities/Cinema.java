package Entities;

import java.util.ArrayList;

/**
 * This is an abstract class for the Cinema. 
 */
public abstract class Cinema {
    protected CinemaType cinemaType;
    protected ArrayList<Showing> showings;
    protected String cinemaName;
    protected String cinemaNumber;

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
     */
    String getCinemaType() {
        return this.cinemaType.toString();
    }

    /**
     * This is a getter function for the Cinema Name.
     * @return
     */
    public String getCinemaName() {
        return this.cinemaName;
    }

    /**
     * This is a getter function for the Cinema Number.
     * @return
     */
    public String getCinemaNumber() {
        return this.cinemaNumber;
    }

    /**
     * This is a getter function for the showings.
     * @return
     */
    public ArrayList<Showing> getShowings() {
        return this.showings;
    }

    /**
     * This is a setter function for setting the showings list.
     * @param showings
     */
    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }

    /**
     * This is a function to add a new showing into the showing list.
     * @param s
     */
    public void addShowing(Showing s) {
        showings.add(s);
    }

    /**
     * This is a function to remove a showing from the showing list.
     * @param s
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

    abstract public ArrayList<Integer> getAisles();
    abstract public String getSeatArrangement();
    abstract public String getScreenLayout();
    abstract public String getEntranceLayout();
    abstract public void printScreenLayout();
    abstract public void printEntranceLayout();
}
