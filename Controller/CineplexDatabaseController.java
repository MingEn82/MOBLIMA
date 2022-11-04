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
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-03
 */
public class CineplexDatabaseController implements DatabaseController {
    /**
     * Default path for Cineplex Database
     */
    private String filePath = "Database/CineplexDatabase.txt";

    /**
     * Delimiter for database text file
     */
    private static final String delimiter = "<b>";

    /**
     * Create file instance
     */
    private File file;

    /**
     * Create ArrayList of cineplexes
     */
    private ArrayList<Cineplex> cineplexes;

    /**
     * Create Arraylist of ID of added cineplexes
     */
    private ArrayList<String> addedCineplexesID;

    /**
     * Constructor for CineplexDatabaseController instance
     */
    public CineplexDatabaseController() {
        file = new File(this.filePath);
        cineplexes = new ArrayList<Cineplex>();
        addedCineplexesID = new ArrayList<String>();
        this.readFile();
    }

    /**
     * Overloaded constructor for cineplexDatabaseController in case database files are found elsewhere
     * @param filePath
     */
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
                cinemaData = cinemaLineData.split(delimiter);
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

        ArrayList<Showing> showings = this.generateShowings(cineplexName, cinemaName, newCinema.getSeatArrangement().split(", "), newCinema.getWideSeatRows());
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
     * @return ArrayList of Showings of that particular cinema
     */
    private ArrayList<Showing> generateShowings(String cineplexName, String cinemaName, String[] seatsData, String[] wideSeatRows) {
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
            ArrayList<SeatRow> seatRows = this.generateSeatRows(seatsData, bookedSeats, wideSeatRows);
            Showing showing = new Showing(movieTitle, movieType, startDate, seatRows);
            showings.add(showing);
        }

        return showings;
    }

    /**
     * Helper function to parse seat data
     * @param seatsData
     * @param bookedSeats
     * @return ArrayList of SeatRows
     */
    private ArrayList<SeatRow> generateSeatRows(String[] seatsData, String[] bookedSeats, String[] wideSeatRows) {
        String rowID = "", currentString, paddedSeatNumber;
        int rowStart = 0;
        int current = 0;
        ArrayList<SeatRow> seatRows = new ArrayList<SeatRow>();
        SeatRow seatRow = null;
        Seat seat;
        boolean isWideRow = false;
        float wideSeatAddOn = new SystemSettingController().getSystemSetting().getWideSeatAddOn();

        for (int i = 0; i < seatsData.length; i++) {
            currentString = seatsData[i];
            if (currentString.matches("[A-Z]")) {
                rowID = currentString;
                isWideRow = Arrays.asList(wideSeatRows).contains(rowID);
                rowStart = current = i;
                if (seatRow != null) {
                    seatRows.add(seatRow);
                }
                seatRow = new SeatRow(rowID);
            } else if (Integer.parseInt(currentString) == (current - rowStart)) {
                paddedSeatNumber = String.format("%02d", Integer.parseInt(currentString));
                if (bookedSeats == null) {
                    seat = isWideRow ? new WideSeat(rowID+paddedSeatNumber, false, wideSeatAddOn) : new StandardSeat(rowID+paddedSeatNumber, false);
                } else {
                    seat = isWideRow 
                    ? new WideSeat(rowID+paddedSeatNumber, Arrays.asList(bookedSeats).contains(rowID+paddedSeatNumber), wideSeatAddOn)
                    : new StandardSeat(rowID+paddedSeatNumber, Arrays.asList(bookedSeats).contains(rowID+paddedSeatNumber));
                }
                seatRow.addSeat(seat);
            } else {
                seat = new StandardSeat();
                seatRow.addSeat(seat);
                i--;
            }
            current++;
        }
        seatRows.add(seatRow);

        return seatRows;
    }
}
