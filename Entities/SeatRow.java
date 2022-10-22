package Entities;

import java.util.ArrayList;

public class SeatRow {
    private String rowID;
    private ArrayList<Seat> seatArray;

    public SeatRow(String rowID) {
        this.rowID = rowID;
        this.seatArray = new ArrayList<Seat>();
    }

    public String getRowID() {
        return rowID;
    }

    public String toString() {
        String str = this.getRowID() + ", ";
        for (Seat seat : seatArray) {
            if (seat.getIsSeat()) {
                str += seat.getSeatNumber() + ", ";
            }
        }
        return str.substring(0, str.length()-2);
    }

    public void addSeat(Seat s) {
        seatArray.add(s);
    }

    public void printSeats(int aisleIndex) {
        for (int i = 0; i < seatArray.size(); i++) {
            if (i == aisleIndex) {
                System.out.print(" ");
            }
            seatArray.get(i).print();
        }
    }

}
