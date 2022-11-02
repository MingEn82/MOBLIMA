package Controller;

/**
 * This class is a controller class that is designed to handle the different
 * options of a cinema staff.
 * 
 * @author Teoh Xi Sheng
 * @version 1.0
 * @since 2022-11-02
 */
public class StaffController {

    /**
     * Create a login controller object
     */
    LoginController loginController = new LoginController();

    /**
     * Create a adminMovieController object
     */
    MovieController adminMovieController = new AdminMovieController();

    /**
     * Create a showingController object
     */
    ShowingController showingController = new ShowingController();

    /**
     * Create a systemSettingController object
     */
    SystemSettingController systemSettingController = new SystemSettingController();

    /**
     * This method takes in the choice input by user from MainMenuUI and redirect
     * user to different UI based on the parameter
     * 
     * @param choice user input choice from MainMenuUI
     */
    public void processStaffChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("");
                System.out.println("Entering Movie Management System...");
                System.out.println("");
                adminMovieController.displayMenu();
                break;

            case 2:
                System.out.println("");
                System.out.println("Entering Showing Management System...");
                System.out.println("");
                showingController.displayMenu();
                break;

            case 3:
                System.out.println("");
                System.out.println("Showing Top 5 Movies...");
                System.out.println("");
                adminMovieController.viewTopMoviesForStaff();
                break;

            case 4:
                System.out.println("");
                System.out.println("Entering System Configuration...");
                System.out.println("");
                systemSettingController.displaySystemSetting();
                break;

            case 5:
                System.out.println("Entering Staff Registration System...");
                System.out.println("");
                loginController.displaySignup();
                break;

            case 6:
                System.out.println("");
                System.out.println("Returning to homepage...");
                System.out.println("");
                break;

            default:
                System.out.println("");
                System.out.println("Please enter a valid choice");
                System.out.println("");
                break;
        }
    }

    /**
     * This method checks with loginController if user login successfully
     * 
     * @return True if user is loggedIn, Else False
     */
    public boolean checkLoggedin() {
        if (loginController.getIsLoggedIn() == true) {
            System.out.println("");
            System.out.println("You are currently logged in as [" + loginController.currentStaff.getUsername() + "]");
            return true;
        }
        System.out.println("");
        return false;
    }

    /**
     * This method will call loginController to display the LoginUI
     */
    public void triggerLogin() {
        loginController.displayLogin();
    }

    /**
     * This method will log the current staff out upon exiting staff menu
     */
    public void triggerLogout() {
        loginController.logout();
    }
}
