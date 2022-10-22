package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.SystemSettings;

public class SystemSettingController {

    SystemSettingsDatabaseController sSDBCtrl = new SystemSettingsDatabaseController();
    // Create a temp ss object which will be used for modification later
    SystemSettings ss = new SystemSettings();

    /**
     * This method temporary set ss object using the data retrieved from database
     * So that this object can be modified and pass to ssDBCtrl for writing to DB
     * 
     * @param ss SystemSettings Entity Object
     */
    public void setSystemSetting(SystemSettings ss) {
        // PH
        ss.setPublicHolidays(sSDBCtrl.getPublicHolidays());
        // Regular
        ss.setMondayToWednesdayRegularTicketPrices(sSDBCtrl.getMondayToWednesdayRegularTicketPrices());
        ss.setThursdayRegularTicketPrices(sSDBCtrl.getThursdayRegularTicketPrices());
        ss.setFridayRegularPeakTicketPrices(sSDBCtrl.getFridayRegularPeakTicketPrices());
        ss.setFridayRegularNonPeakTicketPrices(sSDBCtrl.getFridayRegularNonPeakTicketPrices());
        ss.setWeekendRegularTicketPrices(sSDBCtrl.getWeekendRegularTicketPrices());
        ss.setStudentRegularTicketPrices(sSDBCtrl.getStudentRegularTicketPrices());
        ss.setSeniorRegularTicketPrices(sSDBCtrl.getSeniorRegularTicketPrices());
        // 3D
        ss.setMondayToWednesday3DTicketPrices(sSDBCtrl.getMondayToWednesday3DTicketPrices());
        ss.setThursday3DTicketPrices(sSDBCtrl.getThursday3DTicketPrices());
        ss.setFriday3DPeakTicketPrices(sSDBCtrl.getFriday3DPeakTicketPrices());
        ss.setFriday3DNonPeakTicketPrices(sSDBCtrl.getFriday3DNonPeakTicketPrices());
        ss.setWeekend3DTicketPrices(sSDBCtrl.getWeekend3DTicketPrices());
        ss.setStudent3DTicketPrices(sSDBCtrl.getStudent3DTicketPrices());
        // Others
        ss.setBlockbusterAdditionalPrice(sSDBCtrl.getBlockbusterAdditionalPrice());
        ss.setPlatinumMovieSuiteAdditionalPrice(sSDBCtrl.getPlatinumMovieSuiteAdditionalPrice());
        ss.setIMAXAdditionalPrice(sSDBCtrl.getIMAXAdditionalPrice());
    }

    /**
     * This method will display a menu of functions which staff has access to
     */
    public void displaySystemSetting() {

        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Staff Portal");

        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Menu:");
            System.out.println("");
            System.out.println("1. Display Ticket Prices");
            System.out.println("2. Edit Ticket Prices");
            System.out.println("3. Exit Program");
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Displaying Ticket Prices...");
                    System.out.println(""); // print empty line
                    sSDBCtrl.printSettings();
                    break;

                case 2:
                    System.out.println("Entering Ticket Price Management System...");
                    System.out.println(""); // print empty line
                    // Call updateSystemSetting method in this class
                    updateSystemSetting();
                    break;

                case 3:
                    System.out.println("Thank you for your time! See you again!");
                    System.out.println(""); // print empty line
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("You have not selected the right option. Please re-enter your option.");
                    System.out.println(""); // print empty line
                    break;
            }
        } while (choice != 0);

    }

    public SystemSettings getSystemSetting() {
        // SystemSettingController tempSSCtrl = new SystemSettingController();
        setSystemSetting(ss);
        return this.ss;
    }

    public void updateSystemSetting() {
        // Create a temp ss object which will be used for modification later
        SystemSettings ss = new SystemSettings();
        // Temp set ss object using data from database
        this.setSystemSetting(ss);
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Menu:");
            System.out.println("");
            System.out.println("1. Update Public Holidays");
            System.out.println("2. Update Regular Ticket Pricing - Monday to Wednesday");
            System.out.println("3. Update Regular Ticket Pricing - Thursday");
            System.out.println("4. Update Regular Ticket Pricing - Friday (Peak)");
            System.out.println("5. Update Regular Ticket Pricing - Friday (Non Peak)");
            System.out.println("6. Update Regular Ticket Pricing - Weekends");
            System.out.println("7. Update Regular Ticket Pricing - Student");
            System.out.println("8. Update Regular Ticket Pricing - Senior Citizen");
            System.out.println("9. Update 3D Ticket Pricing - Monday to Wednesday");
            System.out.println("10. Update 3D Ticket Pricing - Thursday");
            System.out.println("11. Update 3D Ticket Pricing - Friday (Peak)");
            System.out.println("12. Update 3D Ticket Pricing - Friday (Non Peak)");
            System.out.println("13. Update 3D Ticket Pricing - Weekends");
            System.out.println("14. Update 3D Ticket Pricing - Student");
            System.out.println("15. Update Addittional Charges for Blockbuster");
            System.out.println("16. Update Addittional Charges for Platinun Movie Suite");
            System.out.println("17. Update Addittional Charges for IMAX");
            System.out.println("18. Return to Staff Portal");
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            choice = scanner.nextInt();
            float newPrice;
            float oldPrice;

            switch (choice) {
                case 2:
                    oldPrice = ss.getMondayToWednesdayRegularTicketPrices();
                    System.out.println("Exisiting Price for Regular Ticket - Monday to Wednesday: $" + oldPrice);
                    System.out.println("");
                    System.out.println("Please enter the updated price:");
                    System.out.println("Press any character to return to the previous menu.");

                    // User enter float
                    if (scanner.hasNextFloat()) {
                        newPrice = scanner.nextFloat();
                        ss.setMondayToWednesdayRegularTicketPrices(newPrice);
                        // Write to DB using udpated ss
                        sSDBCtrl.writeFile(ss);
                        System.out.println("Price has been updated sucessfully from $" + oldPrice + " --> $"
                                + ss.getMondayToWednesday3DTicketPrices());
                    } else {
                        System.out.println("Returning to previous menu...");
                        updateSystemSetting();
                    }
                    break;

                case 18:
                    System.out.println("Returning to Staff Portal...");
                    System.out.println(""); // print empty line
                    displaySystemSetting();
                    break;

                default:
                    System.out.println(
                            "You have not selected the right option. Please re-enter your option.");
                    System.out.println(""); // print empty line
                    break;
            }
        } while (choice != 0);

    }
}