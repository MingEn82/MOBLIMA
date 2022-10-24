package Boundary;

import java.util.Scanner;
import Controller.StaffController;
import Controller.MovieGoerController;

public class MainMenuUI {
    StaffController staffController = new StaffController();
    MovieGoerController movieGoerController = new MovieGoerController();

    Scanner scanner = new Scanner(System.in);

    public MainMenuUI() {
    }

    public void displayMainMenu() {
        System.out.println(""); // print empty line
        System.out.println("Welcome to Moblima!");
        int choice;

        do {
            System.out.println(""); // print empty line
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|               Welcome to Main Menu                    |");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("| 1. Movie Goer                                         |");               
            System.out.println("| 2. Cinema Staff                                       |");                     
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|             Enter 0 to Exit Program                   |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println(""); // print empty line


            choice = scanner.nextInt();
            System.out.println("Choice chosen is: " + choice);

            switch (choice) {
                case 1:
                    System.out.println("Entering Movie Goer's portal...");
                    System.out.println(""); // print empty line
                    displayMovieGoerMenu();
                    break;

                case 2:
                    System.out.println("Entering Cinema's Staff portal...");
                    System.out.println(""); // print empty line
                    displayStaffMenu();
                    break;

                case 0:
                    System.out.println("Thank you for your time! See you again!");
                    System.out.println(""); // print empty line
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("You have not selected the right option. Please re-enter your option.");
                    System.out.println(""); // print empty line
                    break;
            }
        } while (choice != 0);

    }

    public void displayStaffMenu() {

        int choice;

        while (!staffController.checkLoggedin()) {
            System.out.println("You are not logged in.");
            System.out.println("Redirecting to login page for staff....");
            System.out.println(""); // print empty line
            staffController.triggerLogin();
        }

        System.out.println("User is logged in.");

        do {
            System.out.println(""); // print empty line
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|               Welcome to Staff Portal                 |");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("| 1. Create/Update/Remove Movie Listings                |");               
            System.out.println("| 2. Create/Update/Remove Showings                      |");                     
            System.out.println("| 3. Show top 5 movies                                  |");
            System.out.println("| 4. Configure System Settings                          |");
            System.out.println("| 5. Register New Staff Account                         |");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|             Enter 0 to go back to Main Menu           |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println(""); // print empty line

            choice = scanner.nextInt();

            if (choice != 0) {
                staffController.processStaffChoice(choice);
            }
        } while (choice != 0);

    }

    public void displayMovieGoerMenu() {
        int choice;
        do {
            System.out.println(""); // print empty line
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|              Welcome to Movie Goer Menu               |");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("| 1. Find movies                                        |");
            System.out.println("| 2. View booking history                               |");
            System.out.println("| 3. List Top 5 Movies                                  |");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|             Enter 0 to go back to Main Menu           |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println(""); // print empty line

            choice = scanner.nextInt();

            if (choice != 0) {
                movieGoerController.processMovieGoerChoice(choice);
            }
        } while (choice != 0);
    }

}
