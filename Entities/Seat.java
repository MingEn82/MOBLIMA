package Entities;

/**
 * This class is the Seat class entity for defining the seat object attributes and methods.
 */
public class Seat {
    /**
     * Check whether seat is booked
     */
    private boolean isBooked;

    /**
     * Seat Number
     */
    private String seatNumber;

    /**
     * Check whether seat exists
     */
    private boolean isSeat;

    /**
     * String to represent empty seat
     */
    private String emptySquare = "[ ]";

    /**
     * String to represent filled seat
     */
    private String filledSquare = "[x]";

    /**
     * Constructor for seat
     * @param SeatNumber
     * @param isBooked
     */
    public Seat(String seatNumber, boolean isBooked) {
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
        this.isSeat = true;
    }

    /**
     * Constructor for Seat
     */
    public Seat() {
        this.seatNumber = null;
        this.isBooked = false;
        this.isSeat = false;
    }

    /**
     * Getter function for IsBooked
     * @return True/false whether if seat is booked
     */
    public boolean getIsBooked() {
        return this.isBooked;
    }

    /**
     * Getter function for IsSeat
     * @return True/false whether seat exist
     */
    public boolean getIsSeat() {
        return this.isSeat;
    }

    /**
     * Setter function for IsBooked
     * @param isBooked
     */
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    /**
     * Getter function for SeatNumber
     * @return Seat Number
     */
    public String getSeatNumber() {
        return this.seatNumber;
    }

    /**
     * Setter function for EmptySquare
     * @param emptySquare
     */
    public void setEmptySquare(String emptySquare) {
        this.emptySquare = emptySquare;
    }

    /**
     * Setter function for FilledSquare
     * @param filledSquare
     */
    public void setFilledSquare(String filledSquare) {
        this.filledSquare = filledSquare;
    }

    /**
     * Print function for Seat
     */
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
