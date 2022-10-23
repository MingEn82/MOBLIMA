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
    
    public void printBookings() {
        for (Booking b : bookings) {
            b.printBooking();
            System.out.println("");
        }
    }

    // To include price calculation
    public void addNewBooking(Booking bookingObject) {
        Booking newBooking = bookingObject;
        //System.out.println("The new booking object is " +newBooking);
        bookings.add(newBooking);
        

        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            pw = new PrintWriter(bf);
            String content = newBooking.toString();

            pw.append("\n");
            pw.append(content);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public void deleteBookings(String movieTitle) {
        ArrayList<Booking> remainingBookings = new ArrayList<Booking>();

        for (Booking b : bookings) {
            if (!b.getMovieTitle().equals(movieTitle))
                remainingBookings.add(b);
        }

        bookings = remainingBookings;
        this.updateDatabase();
    }

    public void deleteBookings(String movieTitle, String cineplexName, String cinemaName, String date) {
        ArrayList<Booking> remainingBookings = new ArrayList<Booking>();
        DateParser dp = new DateParser("YYYYMMddHHmm");
        Date startDate = dp.parseDate(date);

        for (Booking b : bookings) {
            if (b.getMovieTitle().equals(movieTitle) && b.getCineplexName().equals(cineplexName) && b.getCinemaName().equals(cinemaName) && b.getStartDate().compareTo(startDate) == 0)
                continue;
            remainingBookings.add(b);
        }

        bookings = remainingBookings;
        this.updateDatabase();
    }

    public void updateDatabase() {
        try {
            bf = new BufferedWriter(new FileWriter(file, false));
            pw = new PrintWriter(bf);
            for (Booking b : bookings) {
                pw.println(b.toString());
            }

        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
