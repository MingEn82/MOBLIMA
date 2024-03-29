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
     * @param filePath  filepath to showings database
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
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @return              ArrayList of showings details
     */
    public ArrayList<String[]> filterShowings(String cineplexName, String cinemaName) {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && dp.parseDate(showing[3], "yyyyMMddHHmm").compareTo(new Date()) >= 0) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    /**
     * This function filters the showings for a particular movie
     * @param movieName name of movie
     * @return          ArrayList of showings details
     */
    public ArrayList<String[]> filterShowings(String movieName) {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[0].equals(movieName) && dp.parseDate(showing[3], "yyyyMMddHHmm").compareTo(new Date()) >= 0) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    /**
     * This function filters the showings for a particular movie in a particular cinema
     * @param cineplexName  cineplex name
     * @param cinemaName    cinema name
     * @param movieTitle    name of movie
     * @return              ArrayList of showings details
     */
    public ArrayList<String[]> filterShowings(String cineplexName, String cinemaName, String movieTitle) {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && dp.parseDate(showing[3], "yyyyMMddHHmm").compareTo(new Date()) >= 0) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    /**
     * This function returns showing details
     * @param cineplexName  cineplex name
     * @param cinemaName    cinema name
     * @param movieTitle    movie name
     * @param date          date of showing
     * @return              String[] showing
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
     * @param oldShowing    old showing
     * @param newShowing    new showing
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
     * @param movieTitle    name of movie
     */
    public void deleteShowings(String movieTitle) {
        showingsData.removeIf(showing -> showing[0].equals(movieTitle));
        this.updateDatabase();
    }

    /**
     * This function deletes a particular showing
     * @param movieTitle    movie name
     * @param cineplexName  cineplex name
     * @param cinemaName    cinema name
     * @param date          date of showing
     */
    public void deleteShowing(String movieTitle, String cineplexName, String cinemaName, String date) {
        showingsData.removeIf(showing -> showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date));
        this.updateDatabase();
    }   

    /**
     * This function adds a new showing to the database
     * @param movieTitle        name of movie
     * @param cineplexName      name of cineplex
     * @param cinemaName        name of cinema
     * @param date              date of showing
     * @param movieType         type of movie (2D, 3D, blockbuster)
     * @return                  true if showing was added, false otherwise
     */
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
     * This function adds a new booking to the showing database
     * @param movieTitle    name of movie
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @param date          date of showing
     * @param seatID        seat ID
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