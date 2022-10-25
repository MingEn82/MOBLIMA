package Controller;

import java.util.Date;
import java.util.Scanner;

import Utils.DateParser;

public class ShowingController {
    private ShowingsDatabaseController showingsDC;
    private BookingController bookingsController;
    Scanner sc;
    DateParser dp;

    public ShowingController() {
        showingsDC = new ShowingsDatabaseController();
        bookingsController = new BookingController();
        sc = new Scanner(System.in);
        dp = new DateParser("yyyyMMddHHmm");
    }

    public void displayMenu() {
        int choice;

        do {
            System.out.println("============= Showing Controller =============");
            System.out.println("1. Create New Showing");
            System.out.println("2. Update Showing");
            System.out.println("3. Delete Showing");
            System.out.println("4. Return");
            System.out.println("==============================================");
            System.out.println("");
            System.out.println("Enter choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    this.createShowing();
                    break;
                case 2:
                    this.updateShowing();
                    break;
                case 3:
                    this.deleteShowing();
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
    
    public void createShowing() {
        sc.nextLine();
        System.out.println("Enter movie Title: ");
        String movieTitle = sc.nextLine();
        System.out.println("Enter Cineplex Name: ");
        String cineplexName = sc.nextLine();
        System.out.println("Enter Cinema Name: ");
        String cinemaName = sc.nextLine();
        System.out.println("Enter Date (yyyyMMddHHmm format): ");
        String date = sc.nextLine();
        System.out.println("Enter movie type (3D, 2D, Blockbuster): ");
        String movieType = sc.nextLine();

        // Sanity Check
        if (!movieType.equals("2D") && !movieType.equals("3D") && !movieType.equals("Blockbuster")) {
            System.out.println("Invalid Movie Type");
            return;
        }

        System.out.println("Creating New Showing");
        // To do: Add time check
        if (showingsDC.addNewShowing(movieTitle, cineplexName, cinemaName, date, movieType)) {
            System.out.println("Showing successfully created");
        } else {
            System.out.println("Error! Showing already exists");
        }
    }

    public void updateShowing() {
        sc.nextLine();
        System.out.println("Enter movie Title: ");
        String oldMovieTitle = sc.nextLine();
        System.out.println("Enter Cineplex Name: ");
        String oldCineplexName = sc.nextLine();
        System.out.println("Enter Cinema name: ");
        String oldCinemaName = sc.nextLine();
        Date oldStartDate = null;
        do {
            System.out.println("Enter Date (yyyyMMddHHmm format): ");
            String date = sc.nextLine();
            System.out.println(date);
            oldStartDate = dp.parseDate(date, "yyyyMMddHHmm");
        } while (oldStartDate == null);
        System.out.println(oldStartDate);

        // Checks if there are existing bookings for showing
        if (bookingsController.bookingExistForShowing(oldCineplexName, oldCinemaName, oldMovieTitle, oldStartDate)) {
            System.out.println("Error! There exist bookings for this showing. Cancelling update...");
            return;
        }

        // Checks if showing exists
        String[] oldShowing = showingsDC.getShowing(oldCineplexName, oldCinemaName, oldMovieTitle, dp.formatDate(oldStartDate, "yyyyMMddHHmm"));
        if (oldShowing == null) {
            System.out.println("Error! Showing is not found");
            return;
        }

        String newMovieTitle = oldMovieTitle, 
            newCineplexName = oldCineplexName, 
            newCinemaName = oldCinemaName, 
            newStartDate = oldShowing[3], 
            newMovieType = oldShowing[4];
        int choice;
        do {
            System.out.println("""
                +-----------------------------------------------------------+
                |                   Update Showing Menu                     |
                |-----------------------------------------------------------|
                | 1. Change Movie                                           |
                | 2. Change Movie Type                                      |
                | 3. Change Cineplex                                        |
                | 4. Change Cinema                                          |
                | 5. Change Start Date                                      |
                |-----------------------------------------------------------|
                |    Enter 0 to save changes and go back to Showing menu    |
                +-----------------------------------------------------------+
            """);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter new movie title: ");
                    newMovieTitle = sc.nextLine();
                    // To do: check if movie is valid using movie controller
                    break;

                case 2:
                    do {
                        System.out.println("Enter new movie type (2D, 3D, Blockbuster): ");
                        newMovieType = sc.nextLine();
                        if (!newMovieType.equals("2D") && !newMovieType.equals("3D") && !newMovieType.equals("Blockbuster"))
                            System.out.println("Invalid movie type, try again");
                    } while (!newMovieType.equals("2D") && !newMovieType.equals("3D") && !newMovieType.equals("Blockbuster"));
                    break;

                case 3:
                    System.out.println("Enter new cineplex name: ");
                    newCineplexName = sc.nextLine();
                    // To do: check if cineplex is valid using cineplex controller
                    break;

                case 4:
                    System.out.println("Enter new cinema name: ");
                    newCinemaName = sc.nextLine();
                    // To do: check if cinema is valid using cineplex controller
                    break;
                
                case 5:
                    System.out.println("Enter new start date (yyyyMMddHHmm format): ");
                    newStartDate = sc.nextLine();
                    // To do: check if start date is valid using showing controller
                    break;
                
                case 0:
                    String[] newShowing = { newMovieTitle, newCineplexName, newCinemaName, newStartDate, newMovieType };
                    showingsDC.updateShowing(oldShowing, newShowing);
                    System.out.println("Showing updated");
                    break;
                
                default:
                    System.out.println("Invalid choice. Try Again");
                    
            }
        } while (choice != 0);
    }

    public void deleteShowing() {
        sc.nextLine();
        System.out.println("Enter movie Title: ");
        String movieTitle = sc.nextLine();
        System.out.println("Enter Cineplex Name: ");
        String cineplexName = sc.nextLine();
        System.out.println("Enter Cinema Name: ");
        String cinemaName = sc.nextLine();
        System.out.println("Enter Date (yyyyMMddHHmm format): ");
        Date startDate = dp.parseDate(sc.nextLine(), "yyyyMMddHHmm");

        // Checks if there are existing bookings for showing
        if (bookingsController.bookingExistForShowing(cineplexName, cinemaName, movieTitle, startDate)) {
            System.out.println("Error! There exist bookings for this showing. Cancelling update...");
            return;
        }

        // Checks if showing exists
        String[] oldShowing = showingsDC.getShowing(cineplexName, cinemaName, movieTitle, dp.formatDate(startDate, "yyyyMMddHHmm"));
        if (oldShowing == null) {
            System.out.println("Error! Showing is not found");
        }

        showingsDC.deleteShowing(movieTitle, cineplexName, cinemaName, dp.formatDate(startDate, "yyyyMMddHHmm"));
        System.out.println("Successfully deleted showing");
    }

    public void addBookedSeats() {

    }
}
