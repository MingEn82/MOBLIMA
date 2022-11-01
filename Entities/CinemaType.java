package Entities;

/**
 * This is an enumeration for the Cinema Type.
 */
public enum CinemaType {
    STANDARD( "Standard" ),
    IMAX( "IMAX" ),
    PLATINUM_MOVIE_SUITE( "Platinum Movie Suite" );

    private String type;

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
