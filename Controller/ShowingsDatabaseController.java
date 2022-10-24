package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ShowingsDatabaseController implements DatabaseController {
    private String filePath = "Database/ShowingsDatabase.txt";
    private File file;
    private ArrayList<String[]> showingsData;

    public ShowingsDatabaseController() {
        file = new File(this.filePath);
        showingsData = new ArrayList<String[]>();
        this.readFile();
    }

    public ShowingsDatabaseController(String filePath) {
        file = new File(filePath);
        showingsData = new ArrayList<String[]>();
        this.readFile();
    }

    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String showingDataLine = br.readLine();
            while (showingDataLine != null) {
                showingsData.add(showingDataLine.split(", "));
                showingDataLine = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> filterShowings(String cineplexName, String cinemaName) {
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[1].equals(cineplexName) && showing[2].equals(cinemaName)) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    public ArrayList<String[]> filterShowings(String movieName) {
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[0].equals(movieName)) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    public ArrayList<String[]> filterShowings(String cineplexName, String cinemaName, String movieName) {
        ArrayList<String[]> filteredShowings = new ArrayList<String[]>();
        for (String[] showing : showingsData) {
            if (showing[0].equals(movieName) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName)) {
                filteredShowings.add(showing);
            }
        }

        return filteredShowings;
    }

    /**
     * Deletes all showings of movie
     * @param movieTitle
     */
    public void deleteShowings(String movieTitle) {
        showingsData.removeIf(showing -> showing[0].equals(movieTitle));
        this.updateDatabase();
    }

    /**
     * Deletes a particular showing
     * @param movieTitle
     * @param cineplexName
     * @param cinemaName
     * @param date
     */
    public void deleteShowing(String movieTitle, String cineplexName, String cinemaName, String date) {
        showingsData.removeIf(showing -> showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date));
        this.updateDatabase();
    }   

    public void updateDatabase(String movieTitle, String cineplexName, String cinemaName, String date, String seatID) {
        boolean isNewShowing = true;
        ArrayList<String[]> updatedShowings = new ArrayList<String[]>();

        for (String[] showing : showingsData) {
            if (showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date)) {
                isNewShowing = false;
                ArrayList<String> tmpArray = new ArrayList<String>(Arrays.asList(showing));
                tmpArray.add(seatID);
                String[] updatedshowing = tmpArray.toArray(new String[0]);
                updatedShowings.add(updatedshowing);
            } else {
                updatedShowings.add(showing);
            }
        }

        if (isNewShowing) {
            String[] newShowing = { movieTitle, cineplexName, cinemaName, date, seatID };
            updatedShowings.add(newShowing);
        }

        this.showingsData = updatedShowings;
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

    public void addBooking(String movieTitle, String cineplexName, String cinemaName, String date, String seatID) {
        ArrayList<String[]> updatedShowings = new ArrayList<String[]>();
        System.out.println("Adding booking to showings database...");

        for (String[] showing : showingsData) {
            if (showing[0].equals(movieTitle) && showing[1].equals(cineplexName) && showing[2].equals(cinemaName) && showing[3].equals(date)) {
                String updatedShowing = String.join(", ", showing) + ", " + seatID;
                updatedShowings.add(updatedShowing.split(", "));
                System.out.println("Added!");
            } else {
                updatedShowings.add(showing);
            }
        }
        this.showingsData = updatedShowings;
        this.updateDatabase();
        System.out.println("Exiting...");
    }

    public void updateDatabase() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            PrintWriter pw = new PrintWriter(bw);
            for (String[] showing : showingsData) {
                pw.println(String.join(", ", showing));
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayAllShowings() {
        for (String[] showing : showingsData) {
            System.out.println(Arrays.toString(showing));
        }
    }
}