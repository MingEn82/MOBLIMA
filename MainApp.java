

import Boundary.MainMenuUI;


// import UI.MainMenuUI;

/**
 * Main App that is executed when the program first runs.
 */
public class MainApp {

    public static void main(String[] args) {

        try{
            MainMenuUI mainMenu = new MainMenuUI();
            mainMenu.displayMainMenu();
        }
        catch (Exception e)
        {
            /*
             * Ensure that if the application has any exception, it will be caught and printed out for traceabilty.
             */
            e.getMessage();
            e.printStackTrace();
        }
        

    }
}