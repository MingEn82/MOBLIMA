package Entities;

public class Seat extends Space {
    private boolean isBooked;
    private String SeatNumber;
    private String emptySquare = "[ ]";
    private String filledSquare = "[x]";

    public Seat(String SeatNumber, boolean isBooked) {
        this.SeatNumber = SeatNumber;
        this.isBooked = isBooked;
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
            System.out.print(filledSquare);
        } else {
            System.out.print(emptySquare);
        }
    }
}
