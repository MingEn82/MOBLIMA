package Controller;

import Entities.Booking;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            String TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieType, cinemaType;
            Date startDate;
            int movieDuration;
            double price;
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
                movieTitle = bookingLine[7];
                movieDuration = Integer.parseInt(bookingLine[8]);
                movieType = bookingLine[9];
                cinemaType = bookingLine[10];
                try {
                    startDate = new SimpleDateFormat("yyyyMMddHHmm").parse(bookingLine[9]);
                } catch (ParseException e) {
                    startDate = new Date();
                }
                price = Double.parseDouble(bookingLine[10]);
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
    public void addNewBooking(String TID, String phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer, String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, String movieType, String cinemaType, Date startDate, double price) {
        Booking newBooking = new Booking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration,movieType, cinemaType, startDate, price);
        bookings.add(newBooking);

        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            pw = new PrintWriter(bf);
            String content = newBooking.toString();

            if (bookings.size() > 0) {
                pw.println("");
            }
            pw.print(content);

        } catch (IOException e) {
            e.printStackTrace(); 
        } finally {
            try { 
                pw.close();
            } catch (Exception e) {} 
        }
    }
}
