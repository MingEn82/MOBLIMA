package Entities;

/**
 * WideSeat class extends the Seat class
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public class WideSeat extends Seat {
    /**
     * String to represent empty seat
     */
    private String emptySquare = "\\ /";

    /**
     * String to represent filled seat
     */
    private String filledSquare = "\\x/";

    /**
     * Constructor for StandardSeat
     * @param seatNumber    seat number
     * @param isBooked      whether seat is booked
     */
    public WideSeat(String seatNumber, boolean isBooked) {
        super(seatNumber, isBooked);
    }

    /**
     * Constructor for StandardSeat
     */
    public WideSeat() {
        super();
    }

    /**
     * Getter function for empty square
     * @return String representation of empty square
     */
    public String getEmptySquare() {
        return this.emptySquare;
    }

    /**
     * Getter function for filled square
     * @return String representation of filled square
     */
    public String getFilledSquare() {
        return this.filledSquare;
    }

    /**
     * Print function for StandardSeat
     */
    public void print() {
        if (!getIsSeat()) {
            System.out.print("   ");
        } else if (getIsBooked()) {
            System.out.print(filledSquare);
        } else {
            System.out.print(emptySquare);
        }
    }
}
