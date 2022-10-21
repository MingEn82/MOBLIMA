package Entities;

public enum CinemaType {
    STANDARD( "Standard" ),
    IMAX( "IMAX" ),
    PLATINUM_MOVIE_SUITE( "Platinum Movie Suite" );

    private String type;

    CinemaType( String type ) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
