package Controller;

import java.util.Scanner;
import Entities.SystemSettings;
import Boundary.MainMenuUI;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class SystemSettingController {

    SystemSettingsDatabaseController sSDBCtrl = new SystemSettingsDatabaseController();
    // Create a temp ss object which will be used for modification later
    SystemSettings ss = new SystemSettings();
    


    public SystemSettings getSystemSetting() {
        setSystemSetting(ss);
        return this.ss;
    } 
    /**
     * This method temporary set ss object using the data retrieved from database
     * So that this object can be modified and pass to ssDBCtrl for writing to DB
     * 
     * @param ss SystemSettings Entity Object
     */
    public void setSystemSetting(SystemSettings ss) {
        ss.setPublicHolidays(sSDBCtrl.getPublicHolidays());
        ss.setweekdayPrices(sSDBCtrl.getweekdayPrices());
        ss.setweekendPrices(sSDBCtrl.getweekendPrices());
        ss.setpHPrices(sSDBCtrl.getpHPrices());
        ss.setstudentDiscount(sSDBCtrl.getstudentDiscount());
        ss.setseniorDiscount(sSDBCtrl.getseniorDiscount());
        ss.setthreeeDAddOn(sSDBCtrl.getthreeeDAddOn());
        ss.setblockbusterAddOn(sSDBCtrl.getblockbusterAddOn());
        ss.setblockbusterAddOn(sSDBCtrl.getblockbusterAddOn());
        ss.setIMAXAddOn(sSDBCtrl.getIMAXAddOn());
        ss.setplatinumAddOn(sSDBCtrl.getplatinumAddOn());
    }

    /**
     * This method will display a menu of functions which staff has access to
     */
    public void displaySystemSetting() {

        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to System Configuration");

        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Menu:");
            System.out.println("");
            System.out.println("1. Display Public Holidays");
            System.out.println("2. Add Public Holidays");
            System.out.println("3. Remove Public Holidays");
            System.out.println("4. Display Ticket Prices");
            System.out.println("5. Edit Ticket Prices");
            System.out.println("6. Return to Staff Portal");
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            choice = scanner.nextInt();
            setSystemSetting(ss);

            switch (choice) {
                case 1:
                    System.out.println("Displaying Public Holidays...");
                    System.out.println(""); // print empty line
                    ss.printPHSettings();
                    break;

                case 2:
                    System.out.println("Entering Public Holiday Management System...");
                    System.out.println(""); // print empty line
                    addPHSetting();
                    break;

                case 3:
                    System.out.println("Entering Public Holiday Management System...");
                    System.out.println(""); // print empty line
                    removePHSetting();
                    break;

                case 4:
                    System.out.println("Displaying Ticket Prices...");
                    System.out.println(""); // print empty line
                    ss.printTicketSettings();
                    break;

                case 5:
                    System.out.println("Entering Ticket Price Management System...");
                    System.out.println(""); // print empty line
                    // Call updateTicketSetting method in this class
                    updateTicketSetting();
                    break;

                case 6:
                    System.out.println("Returning to Staff Portal...");
                    System.out.println(""); // print empty line
                    MainMenuUI mMUI = new MainMenuUI();
                    mMUI.displayStaffMenu();
                    break;


                default:
                    System.out.println("Please enter a valid choice");
                    System.out.println("");
                    break;
            }
        } while (choice != 0);

    }

    public void addPHSetting() {
        System.out.println("Please enter a new public holiday to be added in yyyyMMdd format:");
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.next();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        try {
            ss.addPublicHolidays(dateFormatter.parse(dateString));
            sSDBCtrl.writeFile(ss);
        } catch (ParseException e) {
        }
        System.out.println("");
        System.out.println("Returning to previous menu...");
        displaySystemSetting();
    }

    public void removePHSetting() {
        System.out.println("Please select the date to be removed from the system::");
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        ss.removePublicHolidays(index);
        sSDBCtrl.writeFile(ss);
        System.out.println("");
        System.out.println("Returning to previous menu...");
        displaySystemSetting();
    }


    public void updateTicketSetting() {
        setSystemSetting(ss);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Options:");
            System.out.println("");
            System.out.println("************** Ticket Prices **************");
            System.out.println("1. Edit Weekday Ticket Pricing");
            System.out.println("2. Edit Weekend Ticket Pricing");
            System.out.println("3. Edit Public Holiday Ticket Pricing");
            System.out.println("");
            System.out.println("**************** Discounts ****************");
            System.out.println("4. Edit Student Discount");
            System.out.println("5. Edit Senior Citizen Discount");
            System.out.println("");
            System.out.println("************ Additional Charges ***********");
            System.out.println("6. Edit 3D Movie Charges");
            System.out.println("7. Edit 3D Blockbuster Movie Charges");
            System.out.println("8. Edit 3D IMAX Movie Charges");
            System.out.println("9. Edit 3D Platinum Movie Suite Charges");
            System.out.println("");
            System.out.println("10. Return to previous menu");
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            choice = scanner.nextInt();
            float newPrice;
            float oldPrice;

            switch (choice) {
                case 1:
                    oldPrice = ss.getweekdayPrices();
                    System.out.println("Exisiting Price for Weekday Ticket: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setweekdayPrices(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getweekdayPrices()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;
                    
                case 2:
                    oldPrice = ss.getweekendPrices();
                    System.out.println("Exisiting Price for Weekend Ticket: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setweekendPrices(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getweekendPrices()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 3:
                    oldPrice = ss.getpHPrices();
                    System.out.println("Exisiting Price for Weekend Ticket: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setpHPrices(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getpHPrices()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 4:
                    oldPrice = ss.getstudentDiscount();
                    System.out.println("Exisiting Discount for Student: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setstudentDiscount(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getstudentDiscount()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 5:
                    oldPrice = ss.getseniorDiscount();
                    System.out.println("Exisiting Discount for Senior Citizen: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setseniorDiscount(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getseniorDiscount()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 6:
                    oldPrice = ss.getthreeeDAddOn();
                    System.out.println("Exisiting Charges for 3D Movie: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setthreeeDAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getthreeeDAddOn()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 7:
                    oldPrice = ss.getblockbusterAddOn();
                    System.out.println("Exisiting Charges for Blockbuster Movie: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setblockbusterAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getblockbusterAddOn()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 8:
                    oldPrice = ss.getIMAXAddOn();
                    System.out.println("Exisiting Charges for IMAX Movie: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setIMAXAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getIMAXAddOn()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 9:
                    oldPrice = ss.getplatinumAddOn();
                    System.out.println("Exisiting Charges for Platinum Movie Suite: $" + String.format("%.2f", oldPrice));
                    System.out.println("");
                    System.out.println("Please enter the updated price or any character to return to previous menu.");
                    System.out.println("");
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setplatinumAddOn(newPrice);
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + String.format("%.2f", oldPrice) + " --> $" + String.format("%.2f", ss.getplatinumAddOn()));
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    } else {
                        System.out.println("Returning to previous menu...");
                        System.out.println("");
                        updateTicketSetting();
                    }
                    break;

                case 10:
                    System.out.println("Returning to previous menu...");
                    System.out.println(""); // print empty line
                    displaySystemSetting();
                    break;

                default:
                    System.out.println("Please enter a valid choice");
                    System.out.println("");
                    break;
            }
        } while (choice != 0);

    }
}