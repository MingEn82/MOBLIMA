package Controller;

import Entities.*;
import Utils.DateParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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

    // Reads the file and generates the arraylist of cineplexes
    // Uses addedCineplexesID to keep track of added cineplexes for efficiency
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

    // Prints all cineplexes with their respective cinemas and showings
    public void printAllCineplexes() {
        for (Cineplex cineplex : cineplexes) {
            cineplex.print();
        }
    }

    // For creating a new cinema
    public void createNewCinema(String cineplexName, String cineplexID, String cinemaName, String cinemaNumber, CinemaDetails cinemaDetails) {
        for (Cineplex cineplex : cineplexes) {
            if (cineplex.getCineplexID().equals(cineplexID)) {
                for (Cinema cinema : cineplex.getCinemas()) {
                    if (cinema.getCinemaNumber().equals(cinemaNumber)) {
                        System.out.println("Error! Duplicate Cinema!");
                        return;
                    }
                }
            }
        }

        // Not a duplicate cinema, appending to file
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            ArrayList<String> outputArray = new ArrayList<String>();
            outputArray.add(cineplexName);
            outputArray.add(cineplexID);
            outputArray.add(cinemaName);
            outputArray.add(cinemaNumber);
            outputArray.add(cinemaDetails.getType());

            String output = String.join(", ", outputArray);
            pw.append("\n");
            pw.append(output);
            pw.close();

            // Update cineplexes
            this.addNewCinema(output.split(", "));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper function to add a new cinema to cineplexes array
    private void addNewCinema(String[] cinemaData) {
        String cineplexName = cinemaData[0];
        String cineplexID = cinemaData[1];
        String cinemaName = cinemaData[2];
        String cinemaNumber = cinemaData[3];
        CinemaDetails cinemaDetails;
        switch (cinemaData[4]) {
            case "Standard":
                cinemaDetails = CinemaDetails.STANDARD;
                break;
            case "IMAX":
                cinemaDetails = CinemaDetails.IMAX;
                break;
            case "Platinum Movie Suite":
                cinemaDetails = CinemaDetails.PLATINUM_MOVIE_SUITE;
                break;
            default:
                cinemaDetails = CinemaDetails.STANDARD;
        }

        String[] seatsData = cinemaDetails.getSeatArrangement();
        ArrayList<Showing> showings = this.generateShowings(cineplexName, cinemaName, seatsData);
        Cinema cinema = new Cinema(cinemaDetails, showings, cinemaName, cinemaNumber);

        int cineplexIndex = addedCineplexesID.indexOf(cineplexID);
        if (cineplexIndex > -1) {
            Cineplex tmpCineplex = cineplexes.get(cineplexIndex);
            tmpCineplex.addCinema(cinema);
            cineplexes.set(cineplexIndex, tmpCineplex);
        } else {
            Cineplex tmpCineplex = new Cineplex(cineplexName, cineplexID);
            tmpCineplex.addCinema(cinema);
            cineplexes.add(tmpCineplex);
            addedCineplexesID.add(cineplexID);
        }
    }

    // Helper function to generate all showings for a particular cinema
    private ArrayList<Showing> generateShowings(String cineplexName, String cinemaName, String[] seatsData) {
        ArrayList<Showing> showings = new ArrayList<Showing>();
        ShowingsDatabaseController sdc = new ShowingsDatabaseController();
        ArrayList<String[]> allShowings = sdc.filterShowings(cineplexName, cinemaName);
        for (String[] showingData : allShowings) {
            String movieTitle = showingData[0];
            DateParser dp = new DateParser("YYYYMMddHHmm");
            Date startDate = dp.parseDate(showingData[3]);
            String[] bookedSeats = null;
            if (showingData.length > 4) {
                bookedSeats = Arrays.copyOfRange(showingData, 4, showingData.length);
            }
            ArrayList<SeatRow> seatRows = this.generateSeatRows(seatsData, bookedSeats);
            Showing showing = new Showing(movieTitle, startDate, seatRows);
            showings.add(showing);
        }

        return showings;
    }

    // Helper function to parse seat data
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
                seat = new Seat(false);
                seatRow.addSeat(seat);
                i--;
            }
            current++;
        }

        return seatRows;
    }
}
