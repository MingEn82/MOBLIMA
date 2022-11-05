package Controller;

import Entities.Booking;
import Utils.DateParser;

import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * BookingsDatabaseController is a controller that is used to write and read from the booking text file.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class BookingsDatabaseController implements DatabaseController {

    /**
     * Variable holds the location of Text File
     */
    private String fileString = "./Database/BookingsDatabase.txt";

    /**
     * Delimiter
     */
    private static final String delimiter = "<b>";

    /**
     * Variable for File Object
     */
    private File file;

    /**
     * Variable for BufferedWriter
     */
    private BufferedWriter bf;

    /**
     * Variable for PrintWriter
     */
    private PrintWriter pw;

    /**
     * Array List of Booking
     */
    private ArrayList<Booking> bookings;

    /**
     * Constructor for Class
     */
    public BookingsDatabaseController() {
        file = new File(fileString);    
        this.bookings = new ArrayList<Booking>();
        this.readFile();
    }

    /**
     * Constructor for Class, but takes in filepath.
     * @param filePath  file path to booking database
     */
    public BookingsDatabaseController(String filePath) {
        file = new File(filePath);
        this.bookings = new ArrayList<Booking>();
        this.readFile();
    }

    /**
     * Function to fetch array list of booking
     * @return  all bookings
     */
    public ArrayList<Booking> fetchBookings() {
        this.readFile();
        return this.bookings;
    }

    /**
     * This function reads the file from the bookings text file.
     */
    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] bookingLine;
            String TID, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieType, cinemaType;
            Date startDate;
            int movieDuration, phoneNumberOfMovieGoer;
            float price;
            Booking booking;
            DateParser dp = new DateParser("yyyyMMddHHmm");
            while (line != null) {
                bookingLine = line.split(delimiter);
                if (bookingLine.length < 12)
                    continue;

                TID = bookingLine[0];
                phoneNumberOfMovieGoer = Integer.parseInt(bookingLine[1]);
                nameOfMovieGoer = bookingLine[2];
                emailOfMovieGoer = bookingLine[3];
                cineplexName = bookingLine[4];
                cinemaName = bookingLine[5];
                seatID = bookingLine[6];
                movieTitle = bookingLine[7];
                movieDuration = Integer.parseInt(bookingLine[8]);
                movieType = bookingLine[9];
                cinemaType = bookingLine[10];
                startDate = dp.parseDate(bookingLine[11]);
                price = Float.parseFloat(bookingLine[12]);

                booking = new Booking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration,movieType, cinemaType, startDate, price);
                bookings.add(booking);

                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This function prints all the bookings.
     */
    public void printBookings() {
        for (Booking b : bookings) {
            b.printBooking();
            System.out.println("");
        }
    }

    /**
     * This function takes in a booking object and add the new booking object into database by writing into the text file.
     * @param bookingObject new booking object
     */
    public void addNewBooking(Booking bookingObject) {
        //System.out.println("The new booking object is " +newBooking);
        bookings.add(bookingObject);
        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            pw = new PrintWriter(bf);
            pw.println(bookingObject.toString());
            pw.close();

        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    /**
     * This function deletes bookings if the bookings has the relevant movieTitle.
     * @param movieTitle    name of movie
     */
    public void deleteBookings(String movieTitle) {
        bookings.removeIf(b -> b.getMovieTitle().equals(movieTitle));
        this.updateDatabase();
    }

    /**
     * This function deletes bookings if movie title, cineplex name, cinema name and date is the same.
     * @param movieTitle    name of movie
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @param date          date of showing
     */
    public void deleteBookings(String movieTitle, String cineplexName, String cinemaName, String date) {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        Date startDate = dp.parseDate(date);

        bookings.removeIf(b -> b.getMovieTitle().equals(movieTitle) && b.getCineplexName().equals(cineplexName) && b.getCinemaName().equals(cinemaName) && b.getStartDate().compareTo(startDate) == 0);
        this.updateDatabase();
    }

    /**
     * This function writes the entire booking object held by controller into database when needed.
     */
    public void updateDatabase() {
        try {
            bf = new BufferedWriter(new FileWriter(file, false));
            pw = new PrintWriter(bf);
            for (Booking b : bookings) {
                pw.println(b.toString());
            }
            pw.close();

        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    /**
     * Get the total sales of the movie
     * @param movieTitle    name of movie
     * @return total sales
     */
    public int getTotalSales(String movieTitle) {
        int totalSales = 0;
        for (Booking b : bookings) {
            if (b.getMovieTitle().equals(movieTitle))
                totalSales += 1;
        }
        return totalSales;
    }
}
