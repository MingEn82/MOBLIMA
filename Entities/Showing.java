package Entities;

import java.util.ArrayList;
import java.util.Date;

import Utils.DateParser;

public class Showing {
    private String movieTitle;
    private Date startDate;
    private ArrayList<SeatRow> seatRows;

    public Showing(String movieTitle, Date startDate, ArrayList<SeatRow> seatRows) {
        this.movieTitle = movieTitle;
        this.startDate = startDate;
        this.seatRows = seatRows;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ArrayList<SeatRow> getSeatRows() {
        return seatRows;
    }

    public void print(int aisleIndex) {
        DateParser dp = new DateParser("dd-MM-YYYY HH:mm");
        System.out.println("Showing: " + movieTitle);
        System.out.println("Start date: " + dp.formatDate(startDate));
        this.printSeatRows(aisleIndex);
        System.out.println("");
    }

    public void printSeatRows(int aisleIndex) {
        for (SeatRow seatRow : seatRows) {
            System.out.print(seatRow.getRowID() + " ");
            seatRow.printSeats(aisleIndex);
            System.out.println(" " + seatRow.getRowID());
        }
    }
}
