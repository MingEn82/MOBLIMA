package Controller;

import java.util.Scanner;

import Entities.Staff;

/**
 * This is a controller class that handles the Login UI
 * 
 * @author Teoh Xi Sheng
 * @version 1.0
 * @since 2022-11-02
 */
public class LoginController {
    /**
     * Initialise a Staff object for currentStaff
     */
    Staff currentStaff;

    /**
     * Initialise a Staff object for newStaff
     */
    Staff newStaff;

    /**
     * Craete a new object for StaffDatabaseController
     */
    StaffDatabaseController sDBCtrl = new StaffDatabaseController();

    /**
     * Scanner class for user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor for LoginController
     */
    public LoginController() {

    }

    /**
     * This method displays the login UI for staff to input Username and Password
     */
    public void displayLogin() {
        String username, password;
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|               Welcome to Staff Portal                 |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("");
        System.out.print("  Enter Username: ");
        username = sc.next();
        System.out.println("");
        System.out.print("  Enter Password: ");
        password = sc.next();
        System.out.println("");

        if (loginUser(username, password) == true) {
            this.currentStaff = new Staff(username, password);
            System.out.println("Login successful!");
        } else {
            System.out.println("Login unsuccessful. Please try again.");
        }

    }

    /**
     * This method will call Staff DB Controller to login
     * 
     * @param username username input
     * @param password password input
     * @return True if login successful, else False if login unsuccessful
     */
    public boolean loginUser(String username, String password) {
        return sDBCtrl.login(username, password);
    }

    /**
     * This method displays the Signup UI for the staff to input Username and
     * Password for the new staff
     */
    public void displaySignup() {
        String username, password;
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|              Staff Account Registration               |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("");
        System.out.print("  Enter Username: ");
        username = sc.next();
        System.out.println("");
        System.out.print("  Enter Password: ");
        password = sc.next();
        System.out.println("");
        this.newStaff = new Staff(username, password);
        createNewStaff();
    }

    /**
     * This method calls the SystemSettingDatabaseController to create a new staff
     * on the database
     */
    public void createNewStaff() {
        System.out.println("Registering a new account for [" + newStaff.getUsername() + "] ...");
        System.out.println("");
        sDBCtrl.addNewStaff(newStaff.getUsername(), newStaff.getPassword());
    }

    /**
     * This method calls the SystemSettingDatabaseController to login the
     * currentUser object
     * 
     * @return True if login is successful or False if login is unsuccessful
     */
    public boolean getIsLoggedIn() {
        if (currentStaff == null) {
            return false;
        } else {
            return true;
        }

    }

    public void logout() {
        currentStaff = null;
    }
}
