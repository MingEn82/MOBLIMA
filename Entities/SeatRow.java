package Entities;

import java.util.ArrayList;

public class SeatRow {
    private char rowID;
    private ArrayList<Space> seatArray;

    public SeatRow(char rowID, ArrayList<Space> seatArray) {
        this.rowID = rowID;
        this.seatArray = seatArray;
    }

    public char getRowID() {
        return rowID;
    }

    public void printSeats() {
        for (Space space : seatArray) {
            space.print();
        }
    }

}
