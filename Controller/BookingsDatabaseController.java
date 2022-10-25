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
 */
public class BookingsDatabaseController implements DatabaseController {
    private String fileString = "./Database/BookingsDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private ArrayList<Booking> bookings;

    public BookingsDatabaseController() {
        file = new File(fileString);    
        this.bookings = new ArrayList<Booking>();
        this.readFile();
    }

    public BookingsDatabaseController(String filePath) {
        file = new File(filePath);
        this.bookings = new ArrayList<Booking>();
        this.readFile();
    }

    public ArrayList<Booking> fetchBookings(){
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
                bookingLine = line.split(", ");
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
     * @param bookingObject
     */
    public void addNewBooking(Booking bookingObject) {
        //System.out.println("The new booking object is " +newBooking);
        bookings.add(bookingObject);
        this.updateDatabase();
    }

    /**
     * This function deletes bookings if the bookings has the relevant movieTitle.
     * @param movieTitle
     */
    public void deleteBookings(String movieTitle) {
        bookings.removeIf(b -> b.getMovieTitle().equals(movieTitle));
        this.updateDatabase();
    }

    /**
     * This function deletes bookings if movie title, cineplex name, cinema name and date is the same.
     * @param movieTitle
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
            pw.println("hello?");
            pw.close();

        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public int getTotalSales(String movieTitle) {
        int totalSales = 0;
        for (Booking b : bookings) {
            if (b.getMovieTitle().equals(movieTitle))
                totalSales += 1;
        }
        return totalSales;
    }
}
