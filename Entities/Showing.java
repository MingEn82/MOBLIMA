
package Entities;

import java.util.ArrayList;
import java.util.Date;

import Utils.DateParser;

public class Showing {
    private String movieTitle;
    private String movieType;
    private Date startDate;
    private ArrayList<SeatRow> seatRows;

    public Showing(String movieTitle, String movieType, Date startDate, ArrayList<SeatRow> seatRows) {
        this.movieTitle = movieTitle;
        this.movieType = movieType;
        this.startDate = startDate;
        this.seatRows = seatRows;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieType() {
        return movieType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ArrayList<SeatRow> getSeatRows() {
        return seatRows;
    }

    public boolean isSeatBooked(String seatID) {
        String row = seatID.substring(0, 1).toUpperCase();
        for (SeatRow seatRow : seatRows) {
            if (!seatRow.getRowID().equals(row))
                continue;
            for (Seat seat : seatRow.getSeats()) {
                if (seat != null && !seat.getSeatNumber().equals(seatID)) {
                    return seat.getIsSeat() && seat.getIsBooked();
                }
            }
        }

        return true;
    }

    public void print(ArrayList<Integer> aisleIndex) {
        DateParser dp = new DateParser("dd-MM-YYYY HH:mm");
        System.out.println("Showing: " + movieTitle);
        System.out.println("Start date: " + dp.formatDate(startDate));
        System.out.println("");
        this.printSeatRows(aisleIndex);
        System.out.println("");
    }

    public void printSeatRows(ArrayList<Integer> aisleIndex) {
        for (SeatRow seatRow : seatRows) {
            System.out.print(seatRow.getRowID() + " ");
            seatRow.printSeats(aisleIndex);
            System.out.println("");
        }
    }
}
