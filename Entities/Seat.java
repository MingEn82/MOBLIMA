package Entities;

public class Seat extends Space {
    private boolean isBooked;
    private String SeatNumber;
    private char emptySquare = '\u25A1';
    private char filledSquare = '\u25A0';

    public Seat(String SeatNumber, boolean isBooked) {
        this.isBooked = isBooked;
        this.SeatNumber = SeatNumber;
    }

    public boolean getIsBooked() {
        return this.isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public String getSeatNumber() {
        return this.SeatNumber;
    }

    @Override
    public void print() {
        if (isBooked) {
            System.out.print(emptySquare);
        } else {
            System.out.print(filledSquare);
        }
    }
}
