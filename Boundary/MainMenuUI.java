package Boundary;

import java.util.Scanner;
import Controller.StaffController;
import Controller.MovieGoerController;

public class MainMenuUI
{
    StaffController staffController = new StaffController();
    MovieGoerController movieGoerController = new MovieGoerController();


    Scanner scanner = new Scanner(System.in);

    public MainMenuUI() {
    }

    public void displayMainMenu(){
        
        System.out.println("Welcome to Moblima!");
        int choice;

        while(!staffController.checkLoggedin())
        {
            System.out.println(""); // print empty line
            System.out.println("Directing to login page for staff.");
            System.out.println(""); // print empty line
            staffController.triggerLogin();
        }

        
        do
            {
                
                System.out.println(""); // print empty line
                System.out.println("---------------------------------------------------------");
                System.out.println("Main Menu:");
                System.out.println("1. Movie Goer");
                System.out.println("2. Cinema Staff");
                System.out.println("3. Exit");
                System.out.println("---------------------------------------------------------");
                System.out.println(""); // print empty line

                choice = scanner.nextInt();
                System.out.println("Choice chosen is: " + choice);

                switch(choice){
                    case 1:
                    System.out.println("Entering Movie Goer's portal...");
                    System.out.println(""); // print empty line
                    
                    System.exit(0);
                    break;
        
                    case 2:
                    System.out.println("Entering Cinema's Staff portal...");
                    System.out.println(""); // print empty line
                    System.exit(0);
                    break;
        
                    case 3:
                    System.out.println("Thank you for your time! See you again!");
                    System.out.println(""); // print empty line
                    System.exit(0);
                    break;
        
                    default:
                    System.out.println("You have not selected the right option. Please re-enter your option.");
                    System.out.println(""); // print empty line
                    System.exit(0);
                    break;
                }
            }
            while (choice != 0);

        
        
    }

    public void displayStaffLogin(){

        int choice;
        

        do
        {
            System.out.println(""); // print empty line
            System.out.println("---------------------------------------------------------");
            System.out.println("Staff Menu:");
            System.out.println("1. Create/Update/Remove Movie Listings");
            System.out.println("2. Create/Update/Remove Showings");
            System.out.println("3. Show top 5 movies");
            System.out.println("4. Configure System Settings");
            System.out.println("5. Exit to homepage");
            System.out.println("---------------------------------------------------------");
            System.out.println(""); // print empty line

            choice = scanner.nextInt();

            if (choice != 5)
            {
                staffController.processStaffChoice(choice);
            }
        }
        while (choice != 5);
        
        
    }

    public void displayMovieGoerMenu()
    {

    }

}

    