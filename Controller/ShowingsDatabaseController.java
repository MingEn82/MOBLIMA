package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import Utils.DateParser;

/**
 * ShowingsDatabaseController is a controller class that is used to write and read from the showing text file.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-03
 */
public class ShowingsDatabaseController implements DatabaseController {
    /**
     * Default path for Showings Database
     */
    private String filePath = "Database/ShowingsDatabase.txt";

    /**
     * Delimiter for database text file
     */
    private static final String delimiter = "<b>";

    /**
     * Create file instance
     */
    private File file;

    /**
     * Create ArrayList of showing data (in String array format)
     */
    private ArrayList<String[]> showingsData;

    /**
     * Constructor for ShowingsDatabaseController instance
     */
    public ShowingsDatabaseController() {
        file = new File(this.filePath);
        showingsData = new ArrayList<String[]>();
        this.readFile();
    }

    /**
     * Overloaded constructor for ShowingsDatabaseController in case database files are found elsewhere
     * @param filePath
     */
    public ShowingsDatabaseController(String filePath) {
        file = new File(filePath);
        showingsData = new ArrayList<String[]>();
        this.readFile();
    }

    /**
     * This function reads the file from the showings text file.
     */
    public void readFile() {
        try {
            showingsData = new ArrayList<String[]>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String showingDataLine = br.readLine();
            String[] showingData;
            // DateParser dp = new DateParser("yyyyMMddHHmm");
            while (showingDataLine != null) {
                showingData = showingDataLine.split(delimiter);
                // if (dp.parseDate(showingData[3]).compareTo(new Date()) >= 0)
                    // showingsData.add(showingData);
                showingsData.add(showingData);
                showingDataLine = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function filters the showings for a particular cinema
     * @param cineplexName
     * @param cinemaName
     * @return ArrayList of showings details
     */
    public ArrayList<String[]> filterShowings(String cineplexName, String cinemaName) {
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[1].equals(cineplexName) && showing[2].equals(cinemaName)) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    /**
     * This function filters the showings for a particular movie
     * @param movieName
     * @return ArrayList of showings details
     */
    public ArrayList<String[]> filterShowings(String movieName) {
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[0].equals(movieName)) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    /**
     * This function filters the showings for a particular movie in a particular cinema
     * @param cineplexName
     * @param cinemaName
     * @param movieTitle 
     * @return ArrayList of showings details
     */
    public ArrayList<String[]> filterShowings(String cineplexName, String cinemaName, String movieTitle) {
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName)) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    /**
     * This function eturns showing details
     * @param cineplexName
     * @param cinemaName
     * @param movieTitle
     * @param date
     * @return String[] showing
     */
    public String[] getShowing(String cineplexName, String cinemaName, String movieTitle, String date) {
        System.out.println(cineplexName + " " + cinemaName + " " + movieTitle + " " + date);
        for (String[] showing : showingsData) {
            if (showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date)) {
                return showing;
            }
        }

        return null;
    }

    /**
     * This function updates a showing details
     * @param oldShowing
     * @param newShowing
     */
    public void updateShowing(String[] oldShowing, String[] newShowing) {
        showingsData.removeIf(showing -> 
            showing[0].equals(oldShowing[0]) && 
            showing[1].equals(oldShowing[1]) && 
            showing[2].equals(oldShowing[2]) && 
            showing[3].equals(oldShowing[3])
        );

        showingsData.add(newShowing);
        this.updateDatabase();
    }

    /**
     * This function deletes all showings of movie
     * @param movieTitle
     */
    public void deleteShowings(String movieTitle) {
        showingsData.removeIf(showing -> showing[0].equals(movieTitle));
        this.updateDatabase();
    }

    /**
     * This function deletes a particular showing
     * @param movieTitle
     * @param cineplexName
     * @param cinemaName
     * @param date
     */
    public void deleteShowing(String movieTitle, String cineplexName, String cinemaName, String date) {
        showingsData.removeIf(showing -> showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date));
        this.updateDatabase();
    }   

    public boolean addNewShowing(String movieTitle, String cineplexName, String cinemaName, String date, String movieType) {
        ArrayList<String[]> updatedShowings = new ArrayList<String[]>();

        for (String[] showing : showingsData) {
            if (showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date)) {
                return false;
            } else {
                updatedShowings.add(showing);
            }
        }

        String[] newShowing = { movieTitle, cineplexName, cinemaName, date, movieType };
        updatedShowings.add(newShowing);

        this.showingsData = updatedShowings;
        this.updateDatabase();
        return true;
    }

    /**
     * This function adds a new booking to the database
     * @param movieTitle
     * @param cineplexName
     * @param cinemaName
     * @param date
     * @param seatID
     */
    public void addBooking(String movieTitle, String cineplexName, String cinemaName, String date, String seatID) {
        ArrayList<String[]> updatedShowings = new ArrayList<String[]>();

        for (String[] showing : showingsData) {
            if (showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date)) {
                String updatedShowing = String.join(delimiter, showing) + delimiter + seatID;
                updatedShowings.add(updatedShowing.split(delimiter));
            } else {
                updatedShowings.add(showing);
            }
        }
        this.showingsData = updatedShowings;
        this.updateDatabase();
    }

    /**
     * This functions updates Showings Database
     */
    public void updateDatabase() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            PrintWriter pw = new PrintWriter(bw);
            for (String[] showing : showingsData) {
                pw.println(String.join(delimiter, showing));
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}