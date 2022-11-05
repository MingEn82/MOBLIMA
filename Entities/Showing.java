
package Entities;

import java.util.ArrayList;
import java.util.Date;

import Utils.DateParser;

/**
 * This is a class for defining the Showing object.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public class Showing {
    /**
     * Title of Movie
     */
    private String movieTitle;

    /**
     * Type of Movie
     */
    private String movieType;

    /**
     * Start Date of Movie
     */
    private Date startDate;

    /**
     * Seat Rows
     */
    private ArrayList<SeatRow> seatRows;

    /**
     * This is the constructor.
     * @param movieTitle    name of movie
     * @param movieType     type of movie (2D, 3D, Blockbuster)
     * @param startDate     date of showing
     * @param seatRows      Seats for showing
     */
    public Showing(String movieTitle, String movieType, Date startDate, ArrayList<SeatRow> seatRows) {
        this.movieTitle = movieTitle;
        this.movieType = movieType;
        this.startDate = startDate;
        this.seatRows = seatRows;
    }

    /**
     * This is a getter function for the Movie Title.
     * @return Movie Title
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * This is a getter function for Movie Type.
     * @return Movie Type
     */
    public String getMovieType() {
        return movieType;
    }

    /**
     * This is a getter function for start date.
     * @return Start Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This is a getter function for retrieving the seat rows.
     * @return Seat Rows
     */
    public ArrayList<SeatRow> getSeatRows() {
        return seatRows;
    }

    /**
     * This is a function to check whether if a seat is already booked or not.
     * @param seatID    seat ID
     * @return          True/false whether seat is booked
     */
    public boolean isSeatBooked(String seatID) {
        String row = seatID.substring(0, 1).toUpperCase();
        for (SeatRow seatRow : seatRows) {
            if (!seatRow.getRowID().equals(row))
                continue;
            for (Seat seat : seatRow.getSeats()) {
                if (seat.getSeatNumber() != null && seat.getSeatNumber().equals(seatID)) {
                    return seat.getIsSeat() && seat.getIsBooked();
                }
            }
        }

        return true;
    }

    /**
     * This is a function to check whether if a seat is already booked or not.
     * @param seatID seatID
     * @return True/false whether seat is booked
     */
    public boolean isWideSeat(String seatID) {
        String row = seatID.substring(0, 1).toUpperCase();
        for (SeatRow seatRow : seatRows) {
            if (!seatRow.getRowID().equals(row))
                continue;
            for (Seat seat : seatRow.getSeats()) {
                if (seat.getSeatNumber() != null && seat.getSeatNumber().equals(seatID)) {
                    return seat instanceof WideSeat;
                }
            }
        }

        return true;
    }

    /**
     * This is a function for booking a seat based on Seat ID.
     * @param seatID seat ID
     */
    public void bookSeat(String seatID) {
        String row = seatID.substring(0, 1).toUpperCase();
        for (SeatRow seatRow : seatRows) {
            if (!seatRow.getRowID().equals(row))
                continue;
            for (Seat seat : seatRow.getSeats()) {
                if (seat.getSeatNumber() != null && !seat.getSeatNumber().equals(seatID)) {
                    seat.setIsBooked(true);
                }
            }
        }

    }

    /**
     * This is a function that prints the seats.
     * @param aisleIndex indexes of all aisles of cinema
     */
    public void printSeats(ArrayList<Integer> aisleIndex) {
        for (SeatRow seatRow : seatRows) {
            System.out.print(seatRow.getRowID() + " ");
            seatRow.printSeats(aisleIndex);
            System.out.println("");
        }
    }

    /**
     * This is a function that print showing details.
     */
    public void printShowingDetails() {
        DateParser dp = new DateParser("dd-MM-yyyy HH:mm");
        System.out.println("");
        System.out.println("Movie: " + movieTitle);
        System.out.println("Start date: " + dp.formatDate(startDate));
        System.out.println("");
    }
}
