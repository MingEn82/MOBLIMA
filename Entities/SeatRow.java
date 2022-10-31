package Entities;

import java.util.ArrayList;

/**
 * This is a class for defining the SeatRow Object.
 */
public class SeatRow {
    private String rowID;
    private ArrayList<Seat> seatArray;

    /**
     * This is a constructor for the SeatRow Class.
     * @param rowID
     */
    public SeatRow(String rowID) {
        this.rowID = rowID;
        this.seatArray = new ArrayList<Seat>();
    }

    /**
     * This is a setter function for retrieving Row ID.
     * @return
     */
    public String getRowID() {
        return rowID;
    }

    /**
     * This is a function to return a string of the object.
     */
    public String toString() {
        String str = this.getRowID() + ", ";
        for (Seat seat : seatArray) {
            if (seat.getIsSeat()) {
                str += seat.getSeatNumber() + ", ";
            }
        }
        return str.substring(0, str.length()-2);
    }

    /**
     * This is a function to add a new seat.
     * @param s
     */
    public void addSeat(Seat s) {
        seatArray.add(s);
    }

    /**
     * This is a setter function to get the seats.
     * @return
     */
    public ArrayList<Seat> getSeats() {
        return this.seatArray;
    }

    /**
     * This is a function to print all the seats.
     * @param aisleArray
     */
    public void printSeats(ArrayList<Integer> aisleArray) {
        for (int i = 0; i < seatArray.size(); i++) {
            if (aisleArray.contains(i)) {
                System.out.print(" ");
            }
            seatArray.get(i).print();
        }
    }

}
