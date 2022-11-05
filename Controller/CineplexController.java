package Controller;

import java.util.ArrayList;
import java.util.Date;

import Entities.Cinema;
import Entities.Cineplex;
import Entities.Showing;
import Utils.DateParser;

/**
 * Controller for cineplexes
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-03
 */
public class CineplexController {
    /**
     * Create CineplexDatabaseController instance
     */
    CineplexDatabaseController cineplexDC;

    /**
     * Create an arraylist of cineplexes
     */
    ArrayList<Cineplex> cineplexes;

    /**
     * Constructor for CineplexController object
     */
    public CineplexController() {
        cineplexDC = new CineplexDatabaseController();
        cineplexes = cineplexDC.getCineplexes();
    }

    /**
     * Finds Cinema using cineplex name and cinema name
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @return Cinema object if found, null otherwise
     */
    public Cinema findCinema(String cineplexName, String cinemaName) {
        for (Cineplex cineplex : cineplexes) {
            if (!cineplex.getCineplexName().equals(cineplexName))
                continue;
            for (Cinema cinema : cineplex.getCinemas()) {
                if (cinema.getCinemaName().equals(cinemaName))
                    return cinema;
            }
        }
        return null;
    }

    /**
     * Generates UID String using cineplex name and cinema name 
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @return UID String if found, empty string otherwise
     */
    public String generateUID(String cineplexName, String cinemaName) {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        String UID = "";
        
        for (Cineplex cineplex : cineplexes) {
            if (!cineplex.getCineplexName().equals(cineplexName))
                continue;
            UID += cineplex.getCineplexID();
            for (Cinema cinema : cineplex.getCinemas()) {
                if (cinema.getCinemaName().equals(cinemaName))
                    return UID += cinema.getCinemaNumber() + dp.formatDate(new Date());
            }
        }

        return UID;
    }

    /**
     * Returns cinema type using cineplex name and cinema name
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @return cinemax type if found, null otherwise
     */
    public String getCinemaType(String cineplexName, String cinemaName) {
        for (Cineplex cineplex : cineplexes) {
            if (!cineplex.getCineplexName().equals(cineplexName))
                continue;
            for (Cinema cinema : cineplex.getCinemas()) {
                if (cinema.getCinemaName().equals(cinemaName))
                    return cinema.getCinemaType();
            }
        }
        return null;
    }

    /**
     * Books seat for a particular showing
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @param movieTitle    name of movie
     * @param startDate     date of showing
     * @param seatID        seat ID
     */
    public void bookSeat(String cineplexName, String cinemaName, String movieTitle, Date startDate, String seatID) {
        for (Cineplex cineplex : cineplexes) {
            if (!cineplex.getCineplexName().equals(cineplexName))
                continue;

            for (Cinema cinema : cineplex.getCinemas()) {
                if (!cinema.getCinemaName().equals(cinemaName))
                    continue;

                for (Showing showing : cinema.getShowings()) {
                    if (!showing.getMovieTitle().equals(movieTitle) || !(showing.getStartDate().compareTo(startDate) == 0))
                        continue;
                    
                    showing.bookSeat(seatID);
                }
            }
        }

        this.refreshData();
    }

    /**
     * Refreshes cineplexes
     */
    public void refreshData() {
        cineplexDC = new CineplexDatabaseController();
        cineplexes = cineplexDC.getCineplexes();
    }
}
