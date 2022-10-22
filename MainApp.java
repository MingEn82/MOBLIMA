import java.util.ArrayList;

import Controller.CineplexDatabaseController;
import Controller.ShowingsDatabaseController;
import Controller.StaffDatabaseController;
import Controller.SystemSettingsDatabaseController;
import Entities.CinemaType;
import Entities.Seat;
import Entities.SeatRow;
import Entities.Space;

// import UI.MainMenuUI;

public class MainApp {
    public static void main(String[] args) {
        // MainMenuUI mainMenuPage = new MainMenuUI();
        // mainMenuPage.run();

        // ShowingsDatabaseController showingsDC = new ShowingsDatabaseController();
        // ctrl.updateDatabase("Minions 2", "Cineplex 1", "Cinema 2", "202210231900", "B09");
        // ctrl.displayAllShowings();

        // CineplexDatabaseController cineplexDC = new CineplexDatabaseController();
        // String cineplexName = "Cineplex 4";
        // String cineplexID = "4";
        // String cinemaName = "Cinema 1";
        // String cinemaNumber = "01";
        // int aisleIndex = 5;
        // CinemaType cinemaType = CinemaType.IMAX;
        // ArrayList<SeatRow> seatRows = new ArrayList<SeatRow>();
        // SeatRow rowA = new SeatRow("A");
        // SeatRow rowB = new SeatRow("B");
        // for (int i = 1; i <= 10; i++) {
        //     Space s = new Seat(String.valueOf(i), false);
        //     rowA.addSpace(s);
        //     rowB.addSpace(s);
        // }
        // seatRows.add(rowA);
        // seatRows.add(rowB);
        // cineplexDC.createNewCinema(cineplexName, cineplexID, cinemaName, cinemaNumber, cinemaType, seatRows, aisleIndex);
        // cineplexDC.printAllCineplexes();

        // StaffDatabaseController StaffDC = new StaffDatabaseController();
        // System.out.println(StaffDC.login());
        // StaffDC.createNewStaff("subra", "suresh");

        SystemSettingsDatabaseController systemSettingsDC = new SystemSettingsDatabaseController();
        systemSettingsDC.printSettings();
    }
}