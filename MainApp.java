import java.util.ArrayList;
import java.util.Date;

import Controller.BookingsDatabaseController;
import Controller.CineplexDatabaseController;
import Controller.ShowingsDatabaseController;
import Controller.StaffDatabaseController;
import Controller.SystemSettingsDatabaseController;
import Entities.CinemaDetails;
import Entities.Seat;
import Entities.SeatRow;

// import UI.MainMenuUI;

public class MainApp {
    public static void main(String[] args) {
        // MainMenuUI mainMenuPage = new MainMenuUI();
        // mainMenuPage.run();

        // ShowingsDatabaseController showingsDC = new ShowingsDatabaseController();
        // showingsDC.updateDatabase("Minions 2", "Cineplex 1", "Cinema 2",
        // "202210231900", "B09");
        // showingsDC.displayAllShowings();

        // CineplexDatabaseController cineplexDC = new CineplexDatabaseController();
        // String cineplexName = "Cineplex 4";
        // String cineplexID = "4";
        // String cinemaName = "Cinema 1";
        // String cinemaNumber = "01";
        // int aisleIndex = 5;
        // CinemaDetails cinemaType = CinemaDetails.IMAX;
        // ArrayList<SeatRow> seatRows = new ArrayList<SeatRow>();
        // SeatRow rowA = new SeatRow("A");
        // SeatRow rowB = new SeatRow("B");
        // for (int i = 1; i <= 10; i++) {
        // Seat s = new Seat(String.valueOf(i), false);
        // rowA.addSeat(s);
        // rowB.addSeat(s);
        // }
        // seatRows.add(rowA);
        // seatRows.add(rowB);
        // cineplexDC.createNewCinema(cineplexName, cineplexID, cinemaName,
        // cinemaNumber, cinemaType, seatRows, aisleIndex);
        // cineplexDC.printAllCineplexes();

        // StaffDatabaseController StaffDC = new StaffDatabaseController();
        // System.out.println(StaffDC.login());
        // StaffDC.createNewStaff("subra", "suresh");

        // SystemSettingsDatabaseController systemSettingsDC = new
        // SystemSettingsDatabaseController();
        // systemSettingsDC.printSettings();

        // BookingsDatabaseController bookingsDC = new BookingsDatabaseController();
        // bookingsDC.addNewBooking("401202210232250", "91234567", "Ling San",
        // "NTU-Provost@ntu.edu.sg", "Cineplex 4", "Cinema 1", "C03", "Minority Report",
        // 145, new Date(), 8.5);
        // bookingsDC.printBookings();
    }
}