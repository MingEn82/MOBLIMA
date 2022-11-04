import Boundary.MainMenuUI;

/**
 * Main App that is executed when the program first runs.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
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