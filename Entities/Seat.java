package Entities;

/**
 * This class is the Seat class entity for defining the seat object attributes and methods.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public abstract class Seat {
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
     * Print function for Seat
     */
    abstract public void print();

}
