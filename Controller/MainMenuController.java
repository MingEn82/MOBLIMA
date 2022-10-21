package Controller;

public class MainMenuController {
    public void processChoice(int choice) {
        AdminUI adminUI = new AdminUI();
        MovieGoerUI movieGoerUI = new MovieGoerUI();

        switch (choice) {
            case 1:
                adminUI.run();
                break;
            case 2:
                movieGoerUI.run();
                break;
            default:
                ;
        }
    }
}
