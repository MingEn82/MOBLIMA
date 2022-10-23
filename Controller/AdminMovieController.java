package Controller;

import java.util.Scanner;

public class AdminMovieController extends MovieController{
    MovieDatabaseController movieDC;
    Scanner sc;

    
    public AdminMovieController() {
        movieDC = new MovieDatabaseController();
        sc = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;

        do {
            System.out.println("============= Admin Movie Controller =============");
            System.out.println("1. Show all movies");
            System.out.println("2. Create New Movie");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Return");
            System.out.println("==================================================");
            System.out.println("");
            System.out.println("Enter choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    this.createMovie();
                    break;
                case 2:
                    this.updateMovie();
                    break;
                case 3:
                    this.deleteMovie();
                    break;
                case 4:
                    this.createMovie();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    public void createMovie() {

    }

    public void updateMovie() {

    }

    public void deleteMovie() {

    }
}
