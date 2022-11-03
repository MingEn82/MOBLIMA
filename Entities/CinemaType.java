package Entities;

/**
 * This is an enumeration for the Cinema Type.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public enum CinemaType {
    STANDARD( "Standard" ),
    IMAX( "IMAX" ),
    PLATINUM_MOVIE_SUITE( "Platinum Movie Suite" );

    /**
     * Type of Cinema
     */
    private String type;

    /**
     * Constructor For Cinema
     * @param type
     */
    CinemaType( String type ) {
        this.type = type;
    }

    /**
     * This method returns the string of the Cinema Type.
     */
    public String toString() {
        return this.type;
    }
}
