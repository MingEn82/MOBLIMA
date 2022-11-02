package Entities;

/**
 * This is a enumeration for Age Rating.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public enum AgeRating {
    G( "G" ),
    PG( "PG" ),
    PG13( "PG13" ),
    NC16( "NC16" ),
    M18( "M18" ),
    R21( "R21" );

    /**
     * Age rating of movie
     */
    private String ageRating;

    /**
     * Constructor for age rating
     * @param ageRating Age Rating
     */
    AgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * This method is used to return a string output of the corresponding enum type.
     */
    public String toString() {
        return this.ageRating;
    }
}