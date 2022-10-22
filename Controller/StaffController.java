package Controller;


public class StaffController {

    LoginController loginController = new LoginController();

    public void processStaffChoice(int choice)
    {
        System.out.println("OK staff choice works, choice is "+ choice);
    }

    public boolean checkLoggedin(){
        return loginController.getIsLoggedIn();
    }

    public void triggerLogin(){
        //display login stuff then trigger login from logincontroller;
    }
}
