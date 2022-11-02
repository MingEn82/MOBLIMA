package Entities;

import java.util.ArrayList;

/**
 * This is a class for defining the SeatRow Object.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class SeatRow {
    /**
     * ID of Row
     */
    private String rowID;

    /**
     * Array of Seat
     */
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
     * @return Row ID
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
     * @param s SeatObject
     */
    public void addSeat(Seat s) {
        seatArray.add(s);
    }

    /**
     * This is a setter function to get the seats.
     * @return Arraylist of Seat
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
