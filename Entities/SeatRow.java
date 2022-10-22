package Entities;

import java.util.ArrayList;

public class SeatRow {
    private String rowID;
    private ArrayList<Space> seatArray;

    public SeatRow(String rowID) {
        this.rowID = rowID;
        this.seatArray = new ArrayList<Space>();
    }

    public String getRowID() {
        return rowID;
    }

    public String toString() {
        String s = this.getRowID() + ", ";
        Seat seat;
        for (Space space : seatArray) {
            if (space instanceof Seat) {
                seat = (Seat) space;
                s += seat.getSeatNumber() + ", ";
            }
        }
        return s.substring(0, s.length()-2);
    }

    public void addSpace(Space s) {
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
