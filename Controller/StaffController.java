package Controller;

import Controller.LoginController;

public class StaffController {

    LoginController loginController = new LoginController();

    public void processStaffChoice(int choice)
    {

    }

    public boolean checkLoggedin(){
        return loginController.getIsLoggedIn();
    }

    public void triggerLogin(){
        //display login stuff then trigger login from logincontroller;
    }
}
