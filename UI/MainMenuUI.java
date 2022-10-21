package UI;

import java.util.Scanner;
import Controller.MainMenuController;

public class MainMenuUI {
    private static void printWelcome() {
        System.out.println("Are you are an admin staff or a movie goer?");
        System.out.println("1. Admin");
        System.out.println("2. Movie Goer");
    }

    public void run() {
        System.out.println("Hello!");
        printWelcome();

        Scanner sc = new Scanner(System.in);
        MainMenuController mainMenuController = new MainMenuController();
        int choice = sc.nextInt();

        do {
            switch(choice) {
                case 1:
                case 2:
                    mainMenuController.processChoice(choice);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    printWelcome();
            }
        } while (choice < 1 || choice > 2);
        
    }
}
