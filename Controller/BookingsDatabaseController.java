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

    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            String[] bookingLine;
            String TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, cinemaType;
            Date startDate;
            int movieDuration;
            float price;
            Booking booking;
            while (line != null) {
                bookingLine = line.split(", ");
                TID = bookingLine[0];
                phoneNumberOfMovieGoer = bookingLine[1];
                nameOfMovieGoer = bookingLine[2];
                emailOfMovieGoer = bookingLine[3];
                cineplexName = bookingLine[4];
                cinemaName = bookingLine[5];
                seatID = bookingLine[6];
                cinemaType = bookingLine[7];
                movieTitle = bookingLine[8];
                movieDuration = Integer.parseInt(bookingLine[9]);
                DateParser dp = new DateParser("yyyyMMddHHmm");
                startDate = dp.parseDate(bookingLine[10]);
                price = Float.parseFloat(bookingLine[11]);
                booking = new Booking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, cinemaType, movieTitle, movieDuration, startDate, price);
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
    public void addNewBooking(String TID, String phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer, String cineplexName, String cinemaName, String seatID, String cinemaType, String movieTitle, int movieDuration, Date startDate, float price) {
        Booking newBooking = new Booking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, cinemaType, movieTitle, movieDuration, startDate, price);
        bookings.add(newBooking);

        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            pw = new PrintWriter(bf);
            String content = newBooking.toString();

            if (bookings.size() > 0) {
                pw.append("");
            }
            pw.append(content);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
