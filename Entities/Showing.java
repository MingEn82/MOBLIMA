package Entities;

import java.util.ArrayList;
import java.util.Date;

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

    public void printSeatRows() {
        for (SeatRow seatRow : seatRows) {
            System.out.print(seatRow.getRowID() + " ");
            seatRow.printSeats();
            System.out.println(" " + seatRow.getRowID());
        }
    }
}
