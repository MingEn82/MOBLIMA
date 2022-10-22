package Controller;

import java.util.Scanner;

public class MovieGoerMovieController extends MovieController {
    private Scanner sc;

    public MovieGoerMovieController() {
        sc = new Scanner(System.in);
    }

    public void findMovies() {
        int choice;
        do {
            System.out.println("================ Find Movies (Customer) ================");
            System.out.println("1. List all movies");
            System.out.println("2. Coming Soon");
            System.out.println("3. Preview");
            System.out.println("4. Now Showing");
            System.out.println("5. Search movie by title");
            System.out.println("6. Back to Customer Main Menu");
            System.out.println("=======================================================");
            System.out.println("");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    displayMovies(choice);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (choice < 1 || choice > 6);
    }
}
