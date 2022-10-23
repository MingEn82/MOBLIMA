package Controller;
import java.util.Scanner;
// import Entities.User;
// import Entities.Staff;

import Entities.Staff;


public class LoginController {
    Staff currentStaff;
    Staff newStaff;
    StaffDatabaseController sDBCtrl = new StaffDatabaseController();
    Scanner sc = new Scanner(System.in);

    public LoginController() {

    }
    // public Staff getCurrenStaffUser(){
    //     return currentStaff;
    // }

    public void displayLogin() {
        String username, password;
        System.out.println("Welcome Staff Login Portal");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Enter Username: ");
        username = sc.next();
        System.out.println("Enter Password: ");
        password = sc.next();
        System.out.println("");
        System.out.println("---------------------------------------------------------");
        this.currentStaff = new Staff(username, password);
    }

    public void displaySignup() {
        String username, password;
        System.out.println("Register New Staff Account");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Enter Username: ");
        username = sc.next();
        System.out.println("Enter Password: ");
        password = sc.next();
        System.out.println("");
        System.out.println("---------------------------------------------------------");
        this.newStaff = new Staff(username, password);
        createNewStaff();
    }
    

    public void createNewStaff() {
        System.out.println("");
        System.out.println("Registering a new account for [" + newStaff.getUsername() + "] ...");
        System.out.println("");
        sDBCtrl.addNewStaff(newStaff.getUsername(), newStaff.getPassword());
    }
    

    public boolean getIsLoggedIn(){
        if (currentStaff == null){
            return false;
        }
        return sDBCtrl.login(this.currentStaff.getUsername(), this.currentStaff.getPassword());
    }
}
