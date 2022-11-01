package Controller;

import Entities.*;
import Utils.DateParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Database Controller for cineplexes
 */
public class CineplexDatabaseController implements DatabaseController {
    private String filePath = "Database/CineplexDatabase.txt";
    private File file;
    private ArrayList<Cineplex> cineplexes;
    private ArrayList<String> addedCineplexesID;

    public CineplexDatabaseController() {
        file = new File(this.filePath);
        cineplexes = new ArrayList<Cineplex>();
        addedCineplexesID = new ArrayList<String>();
        this.readFile();
    }

    public CineplexDatabaseController(String filePath) {
        file = new File(filePath);
        cineplexes = new ArrayList<Cineplex>();
        addedCineplexesID = new ArrayList<String>();
        this.readFile();
    }

    /**
     * Reads file to create cineplexes
     */
    public void readFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String cinemaLineData = br.readLine();
            String[] cinemaData;
            while (cinemaLineData != null) {
                cinemaData = cinemaLineData.split(", ");
                this.addNewCinema(cinemaData);
                cinemaLineData = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {}
        }
    }

    /**
     * Returns ArrayList of Cinplexes
     * @return ArrayList of Cinplexes
     */
    public ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }

    /**
     * Helper function to create cinema and add it to its cineplex
     * @param cinemaData
     */
    private void addNewCinema(String[] cinemaData) {
        String cineplexName = cinemaData[0];
        String cineplexID = cinemaData[1];
        String cinemaName = cinemaData[2];
        String cinemaNumber = cinemaData[3];
        Cinema newCinema = null;
        switch (cinemaData[4]) {
            case "Standard":
                newCinema = new StandardCinema(null, cinemaName, cinemaNumber);
                break;
            case "IMAX":
                newCinema = new IMAXCinema(null, cinemaName, cinemaNumber);
                break;
            case "Platinum Movie Suite":
                newCinema = new PlatinumMovieSuitesCinema(null, cinemaName, cinemaNumber);
                break;
        }

        ArrayList<Showing> showings = this.generateShowings(cineplexName, cinemaName, newCinema.getSeatArrangement().split(", "));
        newCinema.setShowings(showings);

        int cineplexIndex = addedCineplexesID.indexOf(cineplexID);
        if (cineplexIndex > -1) {
            Cineplex tmpCineplex = cineplexes.get(cineplexIndex);
            tmpCineplex.addCinema(newCinema);
            cineplexes.set(cineplexIndex, tmpCineplex);
        } else {
            Cineplex tmpCineplex = new Cineplex(cineplexName, cineplexID);
            tmpCineplex.addCinema(newCinema);
            cineplexes.add(tmpCineplex);
            addedCineplexesID.add(cineplexID);
        }
    }

    /**
     * Helper function to generate all showings for a particular cinema
     * @param cineplexName
     * @param cinemaName
     * @param seatsData
     * @return
     */
    private ArrayList<Showing> generateShowings(String cineplexName, String cinemaName, String[] seatsData) {
        ArrayList<Showing> showings = new ArrayList<Showing>();
        ShowingsDatabaseController sdc = new ShowingsDatabaseController();
        ArrayList<String[]> allShowings = sdc.filterShowings(cineplexName, cinemaName);
        for (String[] showingData : allShowings) {
            String movieTitle = showingData[0];
            DateParser dp = new DateParser("yyyyMMddHHmm");
            Date startDate = dp.parseDate(showingData[3]);
            String movieType = showingData[4];
            String[] bookedSeats = null;
            if (showingData.length > 5) {
                bookedSeats = Arrays.copyOfRange(showingData, 5, showingData.length);
            }
            ArrayList<SeatRow> seatRows = this.generateSeatRows(seatsData, bookedSeats);
            Showing showing = new Showing(movieTitle, movieType, startDate, seatRows);
            showings.add(showing);
        }

        return showings;
    }

    /**
     * Helper function to parse seat data
     * @param seatsData
     * @param bookedSeats
     * @return
     */
    private ArrayList<SeatRow> generateSeatRows(String[] seatsData, String[] bookedSeats) {
        String rowID = "", currentString, paddedSeatNumber;
        int rowStart = 0;
        int current = 0;
        ArrayList<SeatRow> seatRows = new ArrayList<SeatRow>();
        SeatRow seatRow = null;
        Seat seat;

        for (int i = 0; i < seatsData.length; i++) {
            currentString = seatsData[i];
            if (currentString.matches("[A-Z]")) {
                rowID = seatsData[i];
                rowStart = current = i;
                if (seatRow != null) {
                    seatRows.add(seatRow);
                }
                seatRow = new SeatRow(rowID);
            } else if (Integer.parseInt(currentString) == (current - rowStart)) {
                paddedSeatNumber = String.format("%02d", Integer.parseInt(currentString));
                if (bookedSeats == null) {
                    seat = new Seat(rowID+paddedSeatNumber, false);
                } else {
                    seat = new Seat(rowID+paddedSeatNumber, Arrays.asList(bookedSeats).contains(rowID+paddedSeatNumber));
                }
                seatRow.addSeat(seat);
            } else {
                seat = new Seat();
                seatRow.addSeat(seat);
                i--;
            }
            current++;
        }

        return seatRows;
    }
}
