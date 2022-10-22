package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.SystemSettings;

public class SystemSettingController {

    SystemSettingsDatabaseController systemSettingsDBCtrl = new SystemSettingsDatabaseController();

    /**
     * This method will call printSettings() method in
     * SystemSettingsDatabaseController to print out the existing system settings in
     * database
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
                    systemSettingsDBCtrl.printSettings();
                    break;

                case 2:
                    System.out.println("Entering Ticket Price Management System...");
                    System.out.println(""); // print empty line
                    do {
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Menu:");
                        System.out.println("");
                        System.out.println("1. Update");
                        System.out.println("2. Update");
                        System.out.println("3. Update");
                        System.out.println("---------------------------------------------------------");
                        System.out.println("");
                        choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                System.out.println("Exisiting Price for ");
                                System.out.println(""); // print empty line
                                systemSettingsDBCtrl.printSettings();
                                break;

                            case 3:
                                System.out.println("Thank you for your time! See you again!");
                                System.out.println(""); // print empty line
                                System.exit(0);
                                break;

                            default:
                                System.out.println(
                                        "You have not selected the right option. Please re-enter your option.");
                                System.out.println(""); // print empty line
                                break;
                        }
                    } while (choice != 0);

                    SystemSettings systemSettings = new SystemSettings();
                    //systemSettingsDBCtrl.writeFile();
                    break;

                case 3:
                    System.out.println("Thank you for your time! See you again!");
                    System.out.println(""); // print empty line
                    System.exit(0);
                    break;

                default:
                    System.out.println("You have not selected the right option. Please re-enter your option.");
                    System.out.println(""); // print empty line
                    break;
            }
        } while (choice != 0);

    }

    /**
     * This method will call writeFile() method in SystemSettingsDatabaseController
     * to update the database using the params given
     * 
     * @param publicHolidays                       an array of date of public
     *                                             holidays
     * @param mondayToWednesdayRegularTicketPrices Regular tickets price for Monday
     *                                             to Wednesday
     * @param thursdayRegularTicketPrices          Regular ticket price for Thursday
     * @param fridayRegularPeakTicketPrices        Regular ticket price for Friday
     *                                             Peak
     * @param fridayRegularNonPeakTicketPrices     Regular ticket price for Friday
     *                                             Non Peak
     * @param weekendRegularTicketPrices           Regular ticket price for Weekends
     * @param studentRegularTicketPrices           Regular ticket price for Students
     * @param seniorRegularTicketPrices            Regular ticket price for Seniors
     * @param mondayToWednesday3DTicketPrices      3D ticket price for Monday to
     *                                             Wednesday
     * @param thursday3DTicketPrices               3D ticket price for Thursday
     * @param friday3DPeakTicketPrices             3D ticket price for Friday Peak
     * @param friday3DNonPeakTicketPrices          3D ticket price for Friday Non
     *                                             Peak
     * @param weekend3DTicketPrices                3D ticket price for Weekends
     * @param student3DTicketPrices                3D ticket price for Students
     * @param blockbusterAdditionalPrice           Blockbuster additional price
     *                                             charges
     * @param platinumMovieSuiteAdditionalPrice    Platinium Suite additional price
     *                                             charges
     * @param IMAXAdditionalPrice                  IMAX additional price charges
     */
    public void updateSystemSetting(ArrayList<Date> publicHolidays, float mondayToWednesdayRegularTicketPrices,
            float thursdayRegularTicketPrices, float fridayRegularPeakTicketPrices,
            float fridayRegularNonPeakTicketPrices, float weekendRegularTicketPrices,
            float studentRegularTicketPrices, float seniorRegularTicketPrices, float mondayToWednesday3DTicketPrices,
            float thursday3DTicketPrices, float friday3DPeakTicketPrices, float friday3DNonPeakTicketPrices,
            float weekend3DTicketPrices, float student3DTicketPrices, float blockbusterAdditionalPrice,
            float platinumMovieSuiteAdditionalPrice, float IMAXAdditionalPrice) {

        systemSettingsDBCtrl.writeFile(publicHolidays, mondayToWednesdayRegularTicketPrices,
                thursdayRegularTicketPrices, fridayRegularPeakTicketPrices, fridayRegularNonPeakTicketPrices,
                weekendRegularTicketPrices, studentRegularTicketPrices, seniorRegularTicketPrices,
                mondayToWednesday3DTicketPrices, thursday3DTicketPrices, friday3DPeakTicketPrices,
                friday3DNonPeakTicketPrices, weekend3DTicketPrices, student3DTicketPrices, blockbusterAdditionalPrice,
                platinumMovieSuiteAdditionalPrice, IMAXAdditionalPrice);
    }
}