package Controller;

import java.util.Scanner;

import Entities.Staff;

/**
 * This is a controller class that handles the Login UI
 */
public class LoginController {
    Staff currentStaff;
    Staff newStaff;
    StaffDatabaseController sDBCtrl = new StaffDatabaseController();
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
        System.out.println("Welcome Staff Login Portal");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Username: ");
        username = sc.next();
        System.out.print("Enter Password: ");
        password = sc.next();
        System.out.println("");
        System.out.println("---------------------------------------------------------");
        this.currentStaff = new Staff(username, password);
    }

    /**
     * This method displays the Signup UI for the staff to input Username and
     * Password for the new staff
     */
    public void displaySignup() {
        String username, password;
        System.out.println("Register New Staff Account");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Username: ");
        username = sc.next();
        System.out.print("Enter Password: ");
        password = sc.next();
        System.out.println("");
        System.out.println("---------------------------------------------------------");
        this.newStaff = new Staff(username, password);
        createNewStaff();
    }

    /**
     * This method calls the SystemSettingDatabaseController to create a new staff
     * on the database
     */
    public void createNewStaff() {
        System.out.println("");
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
        }
        return sDBCtrl.login(this.currentStaff.getUsername(), this.currentStaff.getPassword());
    }
}
