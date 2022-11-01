package Entities;

/**
 * Seat Class
 */
public class Seat {
    private boolean isBooked;
    private String seatNumber;
    private boolean isSeat;
    private String emptySquare = "[ ]";
    private String filledSquare = "[x]";

    /**
     * Creates a new seat with the given seat number and booking status
     * @param seatNumber
     * @param isBooked
     */
    public Seat(String seatNumber, boolean isBooked) {
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
        this.isSeat = true;
    }

    /**
     * Creates an empty space
     */
    public Seat() {
        this.seatNumber = null;
        this.isBooked = false;
        this.isSeat = false;
    }

    /**
     * Checks booking status of seat
     * @return  true if seat is booked, false otherwise
     */
    public boolean getIsBooked() {
        return this.isBooked;
    }

    /**
     * Sets booked status of seat
     * @param isBooked
     */
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
    
    /**
     * Checks whether seat is a seat
     * @return  true if is seat, false otherwise
     */
    public boolean getIsSeat() {
        return this.isSeat;
    }

    /**
     * Gets seat number of seat
     * @return seat number
     */
    public String getSeatNumber() {
        return this.seatNumber;
    }

    /**
     * Changes representation of an empty seat
     * @param emptySquare
     */
    public void setEmptySquare(String emptySquare) {
        this.emptySquare = emptySquare;
    }

    /**
     * Changes representation of a filled seat
     * @param filledSquare
     */
    public void setFilledSquare(String filledSquare) {
        this.filledSquare = filledSquare;
    }

    /**
     * Prints seat
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
