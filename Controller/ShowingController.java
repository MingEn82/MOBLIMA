package Controller;

import java.util.Scanner;

public class ShowingController {
    private ShowingsDatabaseController showingsDC;
    private MovieDatabaseController moviesDC;
    private BookingsDatabaseController bookingsDC;
    Scanner sc;

    public ShowingController() {
        showingsDC = new ShowingsDatabaseController();
        moviesDC = new MovieDatabaseController();
        bookingsDC = new BookingsDatabaseController();
        sc = new Scanner(System.in);
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
        System.out.println("Enter Date (YYYYMMddHHmm format): ");
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
        String movieTitle = sc.nextLine();
        System.out.println("Enter new showing status (Coming soon, Preview, Now Showing, End of Showing): ");
        String newShowingStatus = sc.nextLine();

        if (moviesDC.changeShowingStatus(movieTitle, newShowingStatus)) {
            System.out.println(movieTitle + " successfully updated with " + newShowingStatus);

            if (newShowingStatus.equals("Coming Soon") || newShowingStatus.equals("End of Showing")) {
                showingsDC.deleteShowings(movieTitle);
                bookingsDC.deleteBookings(movieTitle);
            }
        } else {
            System.out.println("Aborting..");
            return;
        }
    }

    public void deleteShowing() {
        sc.nextLine();
        System.out.println("Enter movie Title: ");
        String movieTitle = sc.nextLine();
        System.out.println("Enter Cineplex Name: ");
        String cineplexName = sc.nextLine();
        System.out.println("Enter Cinema Name: ");
        String cinemaName = sc.nextLine();
        System.out.println("Enter Date (YYYYMMddHHmm format): ");
        String date = sc.nextLine();

        showingsDC.deleteShowing(movieTitle, cineplexName, cinemaName, date);
        bookingsDC.deleteBookings(movieTitle);
        System.out.println("Successfully deleted showing");
    }

    public void addBookedSeats() {

    }
}
