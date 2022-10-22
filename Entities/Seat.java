package Entities;

public class Seat {
    private boolean isBooked;
    private String SeatNumber;
    private boolean isSeat;
    private String emptySquare = "[ ]";
    private String filledSquare = "[x]";

    public Seat(String SeatNumber, boolean isBooked) {
        this.SeatNumber = SeatNumber;
        this.isBooked = isBooked;
        this.isSeat = true;
    }

    public Seat(boolean isSeat) {
        this.SeatNumber = null;
        this.isBooked = false;
        this.isSeat = false;
    }

    public boolean getIsBooked() {
        return this.isBooked;
    }

    public boolean getIsSeat() {
        return this.isSeat;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public String getSeatNumber() {
        return this.SeatNumber;
    }

    public void setEmptySquare(String emptySquare) {
        this.emptySquare = emptySquare;
    }

    public void setFilledSquare(String filledSquare) {
        this.filledSquare = filledSquare;
    }

    public void print() {
        if (!isSeat) {
            System.out.print("   ");
        } else if (isBooked) {
            System.out.print(filledSquare);
        } else {
            System.out.print(emptySquare);
        }
    }
}
