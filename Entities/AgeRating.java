package Entities;

/**
 * This is a enumeration for Age Rating.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public enum AgeRating {
    /**
     * AgeRating of G
     */
    G( "G" ),

    /**
     * AgeRating of PG
     */
    PG( "PG" ),

    /**
     * AgeRating of PG13
     */
    PG13( "PG13" ),

    /**
     * AgeRating of NC16
     */
    NC16( "NC16" ),

    /**
     * AgeRating of M18
     */
    M18( "M18" ),

    /**
     * AgeRating of R21
     */
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