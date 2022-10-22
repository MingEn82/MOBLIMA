package Controller;

import Boundary.MainMenuUI;

public class StaffController {

    LoginController loginController = new LoginController();
    

    public void processStaffChoice(int choice)
    {
        switch(choice){
            case 1:
            System.out.println("Entering Movie Mangement System...");

            break;

            case 2:
            System.out.println("Entering Showing Mangement System...");
            
            break;

            case 3:
            System.out.println("Showing Top 5 Movies...");
            
            break;

            case 4:
            System.out.println("Entering System Configuration...");
            System.out.println("");
            SystemSettingController sSCtrl = new SystemSettingController();
            sSCtrl.displaySystemSetting();
            break;

            default:
            System.out.println("Please enter a valid choice");
            System.out.println("");
            break;
        }
    }

    public boolean checkLoggedin(){
        return loginController.getIsLoggedIn();
    }

    public void triggerLogin(){
        //display login stuff then trigger login from logincontroller;
    }
}
