package Controller.DatabaseController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import Entities.Showing;

public class ShowingsDatabaseController extends DatabaseController {
    private String fileString = "./Database/ShowingDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private ArrayList<Showing> showings;

    public ShowingsDatabaseController() {
        file = new File(fileString);    
        this.showings = new ArrayList<Showing>();
        this.readFile();
    }

    public ShowingsDatabaseController(String filePath) {
        file = new File(filePath);
        this.showings = new ArrayList<Showing>();
        this.readFile();
    }

    public void readFile() {
        String movieTitle;
        String cinplexName;
        String cinemaName;
        
        try {
            BufferedReader brStream = new BufferedReader(new FileReader(file));
            String line = brStream.readLine();

            while (line != null) {
                String[] showingLine = line.split(", ");

            }
            
            
            brStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
}
