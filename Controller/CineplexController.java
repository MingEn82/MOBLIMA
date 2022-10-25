package Controller;

import java.util.ArrayList;
import java.util.Date;

import Entities.Cinema;
import Entities.Cineplex;
import Entities.Showing;
import Utils.DateParser;

public class CineplexController {
    CineplexDatabaseController cineplexDC;
    ArrayList<Cineplex> cineplexes;

    public CineplexController() {
        cineplexDC = new CineplexDatabaseController();
        cineplexes = cineplexDC.getCineplexes();
    }

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

    public void refreshData() {
        cineplexDC = new CineplexDatabaseController();
        cineplexes = cineplexDC.getCineplexes();
    }
}
