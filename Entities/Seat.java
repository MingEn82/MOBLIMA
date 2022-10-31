package Entities;

/**
 * This class is the Seat class entity for defining the seat object attributes and methods.
 */
public class Seat {
    private boolean isBooked;
    private String SeatNumber;
    private boolean isSeat;
    private String emptySquare = "[ ]";
    private String filledSquare = "[x]";

    /**
     * Constructor for seat
     * @param SeatNumber
     * @param isBooked
     */
    public Seat(String SeatNumber, boolean isBooked) {
        this.SeatNumber = SeatNumber;
        this.isBooked = isBooked;
        this.isSeat = true;
    }

    /**
     * Constructor for Seat
     * @param isSeat
     */
    public Seat(boolean isSeat) {
        this.SeatNumber = null;
        this.isBooked = false;
        this.isSeat = false;
    }

    /**
     * Getter function for IsBooked
     * @return
     */
    public boolean getIsBooked() {
        return this.isBooked;
    }

    /**
     * Getter function for IsSeat
     * @return
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
     * @return
     */
    public String getSeatNumber() {
        return this.SeatNumber;
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
