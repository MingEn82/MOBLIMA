package Controller;

import java.util.Scanner;
import Entities.SystemSettings;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class SystemSettingController {

    SystemSettingsDatabaseController sSDBCtrl = new SystemSettingsDatabaseController();
    // Create a temp ss object which will be used for modification later
    SystemSettings ss = new SystemSettings();
    Scanner scanner = new Scanner(System.in);

    /**
     * This method fetch the existing system settings from db and store in systemSettings object
     */
    public SystemSettingController() {
        ss = sSDBCtrl.fetchSystemSettings();
    }

    /**
     * This method will return the systemSettings object
     * @return systemSettings object
     */
    public SystemSettings getSystemSetting() {
        return this.ss;
    } 
    

    /**
     * This method will display a menu of functions which staff has access to
     */
    public void displaySystemSetting() {

        int choice;
        System.out.println("Welcome to System Configuration");

        do {
            System.out.println(""); 
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|              Please select your option                |");
            System.out.println("---------------------------------------------------------");
            System.out.println("| 1. Display Public Holidays                            |");
            System.out.println("| 2. Add Public Holidays                                |");
            System.out.println("| 3. Remove Public Holidays                             |");
            System.out.println("| 4. Display Ticket Prices                              |");
            System.out.println("| 5. Edit Ticket Prices                                 |");
            System.out.println("---------------------------------------------------------");
            System.out.println("|           Enter 0 to return to Staff Portal           |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println(""); 
            System.out.print("Choice chosen is: "); 
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(""); // print empty line
                    System.out.println("Displaying Public Holidays...");
                    ss.printPHSettings();
                    System.out.println("Returning to previous menu...");
                    break;

                case 2:
                    System.out.println(""); // print empty line
                    System.out.println("Entering Public Holiday Management System...");
                    addPHSetting();
                    break;

                case 3:
                    System.out.println(""); // print empty line
                    System.out.println("Entering Public Holiday Management System...");
                    removePHSetting();
                    break;

                case 4:
                    System.out.println(""); // print empty line
                    System.out.println("Displaying Ticket Prices...");
                    ss.printTicketSettings();
                    break;

                case 5:
                    System.out.println(""); // print empty line
                    System.out.println("Entering Ticket Price Management System...");
                    updateTicketSetting();
                    break;

                case 0:
                    System.out.println(""); // print empty line
                    System.out.println("Returning to Staff Portal...");
                    break;

                default:
                    System.out.println("");
                    System.out.println("Please enter a valid choice");
                    break;
            }
        } while (choice != 0);

    }

    public void addPHSetting() {
        System.out.println(""); 
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|           Public Holiday Management System            |");
        System.out.println("---------------------------------------------------------");
        System.out.println(""); 
        System.out.println("Please enter a new public holiday");
        System.out.print("to be added in yyyyMMdd format: ");
        String dateString = scanner.next();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        try {
            ss.addPublicHolidays(dateFormatter.parse(dateString));
            sSDBCtrl.writeFile(ss);
        } catch (ParseException e) {
        }
        System.out.println("Returning to previous menu...");
        System.out.println("");
        
    }

    public void removePHSetting() {
        ss.printPHSettings();
        System.out.println("Please select the date to be removed from the system: ");
        System.out.println("");
        System.out.print("Choice chosen is: ");
        int index = scanner.nextInt();
        System.out.println("");
        ss.removePublicHolidays(index);
        sSDBCtrl.writeFile(ss);
        System.out.println("");
        System.out.println("Returning to previous menu...");
        System.out.println("");
        
    }


    public void updateTicketSetting() {
        int choice;

        do {
            System.out.println(""); 
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|            Ticket Price Management System             |");
            System.out.println("---------------------------------------------------------");
            System.out.println("|____________________ Ticket Prices ____________________|");
            System.out.println("|                                                       |");
            System.out.println("| 1. Weekday Ticket:                             $  " + String.format("%.2f", ss.getweekdayPrices()) + "|");               
            System.out.println("| 2. Weekend Ticket:                             $  " + String.format("%.2f", ss.getweekendPrices()) + "|"); 
            System.out.println("| 3. Public Holiday Ticket:                      $ " + String.format("%.2f", ss.getpHPrices()) + "|");
            System.out.println("|                                                       |");
            System.out.println("|______________________ Discounts ______________________|");
            System.out.println("|                                                       |");
            System.out.println("| 4. Student Discount:                         - $  " + String.format("%.2f", ss.getstudentDiscount()) + "|"); 
            System.out.println("| 5. Senior Citizen Discount:                  - $  " + String.format("%.2f", ss.getseniorDiscount()) + "|");
            System.out.println("|                                                       |");
            System.out.println("|__________________ Additional Charges _________________|"); 
            System.out.println("|                                                       |");
            System.out.println("| 6. 3D Movie:                                 + $  " + String.format("%.2f", ss.getthreeDAddOn()) + "|"); 
            System.out.println("| 7. Blockbuster Movie:                        + $  " + String.format("%.2f", ss.getblockbusterAddOn()) + "|"); 
            System.out.println("| 8. IMAX Movie:                               + $  " + String.format("%.2f", ss.getIMAXAddOn()) + "|"); 
            System.out.println("| 9. Platinum Movie Suite:                     + $  " + String.format("%.2f", ss.getplatinumAddOn()) + "|");  
            System.out.println("|                                                       |");
            System.out.println("---------------------------------------------------------");
            System.out.println("|           Enter 0 to return to previous menu          |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println(""); 
            System.out.println("Please select the item above to be edited."); 
            System.out.println(""); 
            System.out.print("Choice chosen is: "); 
            System.out.print("");

            choice = scanner.nextInt();
            
            float newPrice;
            float oldPrice;

            switch (choice) {
                case 1:
                    oldPrice = ss.getweekdayPrices();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Price for Weekday Ticket:              $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Price:  $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setweekdayPrices(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getweekdayPrices()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;
                    
                case 2:
                    oldPrice = ss.getweekendPrices();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Price for Weekend Ticket:              $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Price:  $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setweekendPrices(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getweekendPrices()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                    }
                    break;

                case 3:
                    oldPrice = ss.getpHPrices();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Price for Public Holiday Ticket:      $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Price:  $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setpHPrices(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getpHPrices()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;

                case 4:
                    oldPrice = ss.getstudentDiscount();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Discount for Student:               -  $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Discount: - $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setstudentDiscount(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Discount has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getstudentDiscount()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;

                case 5:
                    oldPrice = ss.getseniorDiscount();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Discount for Senior Citizen:        -  $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Discount: - $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setseniorDiscount(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Discount has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getstudentDiscount()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;

                case 6:
                    oldPrice = ss.getthreeDAddOn();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Charge for 3D Movies:                + $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Charge: + $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setthreeDAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Charge has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getthreeDAddOn()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;

                case 7:
                    oldPrice = ss.getblockbusterAddOn();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Charge for Blockbuster Movies:       + $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Charge: + $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setblockbusterAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Charge has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getthreeDAddOn()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;

                case 8:
                    oldPrice = ss.getIMAXAddOn();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Charge for IMAX Movies:              + $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Charge: + $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setIMAXAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Charge has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getthreeDAddOn()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;

                case 9:
                    oldPrice = ss.getplatinumAddOn();
                    System.out.println(""); 
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println("|            Please enter the updated price             |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                                                       |");
                    System.out.println("| Existing Charge for Platinum Movie Suite:     + $ " + String.format("%.2f", oldPrice) + "|");
                    System.out.println("|                                                       |");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|    Enter any character to return to previous menu     |");
                    System.out.println("+-------------------------------------------------------+");
                    System.out.println(""); 
                    System.out.print("Updated Charge: + $ ");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setplatinumAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("");
                        System.out.println("Charge has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getthreeDAddOn()));
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    } else {
                        scanner.next().charAt(0);
                        System.out.println("");
                        System.out.println("Returning to previous menu...");
                    }
                    break;

                case 0:
                    System.out.println(""); // print empty line
                    System.out.println("Returning to previous menu...");
                    break;

                default:
                    System.out.println("");
                    System.out.println("Please enter a valid choice");
                    break;
            }

            
        } while (choice != 0);

    }
}