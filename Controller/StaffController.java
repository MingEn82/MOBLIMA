package Controller;

/**
 * This class is a controller class that is designed to handle the different options of a cinema staff.
 */
public class StaffController {

    LoginController loginController = new LoginController();
    MovieController adminMovieController = new AdminMovieController();
    ShowingController showingController = new ShowingController();
    SystemSettingController systemSettingController = new SystemSettingController();
    

    public void processStaffChoice(int choice)
    {
        switch(choice){
            case 1:
            System.out.println("Entering Movie Mangement System...");
            System.out.println("");
            adminMovieController.displayMenu();
            break;

            case 2:
            System.out.println("Entering Showing Mangement System...");
            System.out.println("");
            showingController.displayMenu();
            break;

            case 3:
            System.out.println("Showing Top 5 Movies...");
            System.out.println("");
            adminMovieController.viewTopMovies();
            break;
            
            case 4:
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
            System.out.println("Logging out and returning to homepage...");
            System.out.println("");
            break;

            default:
            System.out.println("Please enter a valid choice");
            System.out.println("");
            break;
        }
    }

    public boolean checkLoggedin(){
        if (loginController.getIsLoggedIn() == true) {
            System.out.println("You are currently logged in as [" + loginController.currentStaff.getUsername() + "]");
            System.out.println("");
            return true;
        }
        System.out.println("Login Unsuccessful. Please Try Again.");
        System.out.println("");
        return false;
    }

    public void triggerLogin(){
        loginController.displayLogin();
    }
}

