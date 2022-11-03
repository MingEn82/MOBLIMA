package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * This is an entity class that is deisgned to create a SystemSettings object
 * 
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class SystemSettings {

    /**
     * Variable to store filepath
     */
    private String filePath = "Database/SystemSettingsDatabase.txt";

    /**
     * Arraylist of public holidays
     */
    private ArrayList<Date> publicHolidays;

    /**
     * Weekday Price
     */
    private float weekdayPrices;

    /**
     * Weekend Price
     */
    private float weekendPrices;

    /**
     * Public Holiday Price
     */
    private float pHPrices;

    /*
     * Student Discount Price
     */
    private float studentDiscount;

    /**
     * Senior Discount Price
     */
    private float seniorDiscount;

    /**
     * Preferred Credit Card / Loyalty Card Discount
     */
    private float prefCreditLoyaltyDiscount;

    /**
     * Add on for 6PM onwards
     */
    private float sixPMAddOn;

    /**
     * 3D Add on price
     */
    private float threeDAddOn;

    /**
     * Block Buster Add on price
     */
    private float blockbusterAddOn;

    /**
     * IMAX add on price
     */
    private float IMAXAddOn;

    /**
     * Platinum add on price
     */
    private float platinumAddOn;

    /**
     * Show options for Top 5 Movies by Sales for Movie Goer
     */
    private int displayTop5bySales;

    /**
     * Show options for Top 5 Movies by Rating for Movie Goer
     */
    private int displayTop5byRating;

    /**
     * Constructor with attributes
     * 
     * @param publicHolidays            array list of public holidays
     * @param weekdayPrices             regular ticket price for weekday
     * @param weekendPrices             regular ticket price for weekend
     * @param pHPrices                  regular ticket price for public holiday
     * @param studentDiscount           discount for student
     * @param seniorDiscount            discount for seniors
     * @param prefCreditLoyaltyDiscount discount for credit / loyalty card
     * @param sixPMAddon                additional price for showing after 6PM
     * @param threeDAddOn               additional price for 3D movies
     * @param blockbusterAddOn          additional price for blockbuster movies
     * @param IMAXAddOn                 additional price for IMAX movies
     * @param platinumAddOn             additional price for platinum movie suite
     * @param displayTop5bySales        display top5 by sales
     * @param displayTop5byRating       display top5 by rating
     */
    public SystemSettings(ArrayList<Date> publicHolidays, float weekdayPrices,
            float weekendPrices, float pHPrices,
            float studentDiscount, float seniorDiscount, float prefCreditLoyaltyDiscount, float sixPMAddon,
            float threeDAddOn,
            float blockbusterAddOn, float IMAXAddOn, float platinumAddOn, int displayTop5bySales,
            int displayTop5byRating) {
        this.publicHolidays = publicHolidays;
        this.weekdayPrices = weekdayPrices;
        this.weekendPrices = weekendPrices;
        this.pHPrices = pHPrices;
        this.studentDiscount = studentDiscount;
        this.seniorDiscount = seniorDiscount;
        this.prefCreditLoyaltyDiscount = prefCreditLoyaltyDiscount;
        this.sixPMAddOn = sixPMAddon;
        this.threeDAddOn = threeDAddOn;
        this.blockbusterAddOn = blockbusterAddOn;
        this.IMAXAddOn = IMAXAddOn;
        this.platinumAddOn = platinumAddOn;
        this.displayTop5byRating = displayTop5byRating;
        this.displayTop5bySales = displayTop5bySales;
    }

    /**
     * Constructor
     */
    public SystemSettings() {

    }

    /** This method prints out the existing ticket prices */
    public void printTicketSettings() {
        System.out.println("");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|            Ticket Price Management System             |");
        System.out.println("---------------------------------------------------------");
        System.out.println("|____________________ Ticket Prices ____________________|");
        System.out.println("|                                                       |");
        System.out.println(
                "| Weekday Ticket:                                $  " + String.format("%.2f", weekdayPrices) + "|");
        System.out.println(
                "| Weekend Ticket:                                $  " + String.format("%.2f", weekendPrices) + "|");
        System.out
                .println("| Public Holiday Ticket:                         $ " + String.format("%.2f", pHPrices) + "|");
        System.out.println("|                                                       |");
        System.out.println("|______________________ Discounts ______________________|");
        System.out.println("|                                                       |");
        System.out.println(
                "| Student Discount:                            - $  " + String.format("%.2f", studentDiscount) + "|");
        System.out.println(
                "| Senior Citizen Discount:                     - $  " + String.format("%.2f", seniorDiscount) + "|");
        System.out.println("| Preferred Credit / Loyalty Card Discount:    - $  "
                + String.format("%.2f", prefCreditLoyaltyDiscount) + "|");
        System.out.println("|                                                       |");
        System.out.println("|__________________ Additional Charges _________________|");
        System.out.println("|                                                       |");
        System.out.println("| Showing after 6PM:                           + $  "
                + String.format("%.2f", sixPMAddOn) + "|");
        System.out.println(
                "| 3D Movie:                                    + $  " + String.format("%.2f", threeDAddOn) + "|");
        System.out.println(
                "| Blockbuster Movie:                           + $  " + String.format("%.2f", blockbusterAddOn) + "|");
        System.out.println(
                "| IMAX Movie:                                  + $  " + String.format("%.2f", IMAXAddOn) + "|");
        System.out.println(
                "| Platinum Movie Suite:                        + $  " + String.format("%.2f", platinumAddOn) + "|");
        System.out.println("|                                                       |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("");
        System.out.println("Returning to previous menu...");
    }

    /**
     * This method prints out the list of public holidays
     */
    public void printPHSettings() {
        System.out.println("");
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|           Public Holiday Management System            |");
        System.out.println("---------------------------------------------------------");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        for (int i = 1; i <= getPublicHolidays().size(); i++) {
            if (i >= 10) {
                System.out.println("| " + i + ". " + dateFormatter.format(getPublicHolidays().get(i - 1))
                        + "                                   |");
            } else {
                System.out.println("| " + i + ". " + dateFormatter.format(getPublicHolidays().get(i - 1))
                        + "                                    |");

            }
        }
        System.out.println("+-------------------------------------------------------+");
        System.out.println("");

    }

    /**
     * Getter method for filePath
     * 
     * @return filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Setter method for filePath
     * 
     * @param filePath the text file file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method append the param date into the ArrayList publicHolidays
     * 
     * @param date new date to be added
     */
    public void addPublicHolidays(Date date) {
        for (Date ph : publicHolidays) {
            if (ph.compareTo(date) == 0) {
                System.out.println("Duplicate public holiday!");
                return;
            }
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        String dateString = dateFormatter.format(date);
        System.out.println("");
        System.out.println("Adding [" + dateString + "] to the system...");
        System.out.println("");
        this.publicHolidays.add(date);
        System.out.println("[" + dateString + "] has been successfully added to the system");
        System.out.println("");
    }

    /**
     * This method removes the date from ArrayList publicHolidays using index
     * 
     * @param index index of the date list to be removed
     */
    public void removePublicHolidays(int index) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        String dateString = dateFormatter.format(getPublicHolidays().get(index - 1));
        System.out.println("Removing [" + dateString + "] from the system...");
        System.out.println("");
        this.publicHolidays.remove(index - 1);
        System.out.println("[" + dateString + "] has been sucessfully removed from the system.");
    }

    /**
     * Getter method for list of public holidays
     * 
     * @return list of public holidays
     */
    public ArrayList<Date> getPublicHolidays() {
        return this.publicHolidays;
    }

    /**
     * Setter method for list of public holidays
     * 
     * @param publicHolidays array list of public holidays
     */
    public void setPublicHolidays(ArrayList<Date> publicHolidays) {
        this.publicHolidays = publicHolidays;
    }

    /**
     * Getter method for regular price for weekdays
     * 
     * @return regular price for weekdays
     */
    public float getweekdayPrices() {
        return this.weekdayPrices;
    }

    /**
     * Setter method for regular prices for weekdays
     * 
     * @param weekdayPrices regular prices for weekdays
     */
    public void setweekdayPrices(float weekdayPrices) {
        this.weekdayPrices = weekdayPrices;
    }

    /**
     * Getter method for regular price for weekends
     * 
     * @return regular price for weekends
     */
    public float getweekendPrices() {
        return this.weekendPrices;
    }

    /**
     * Setter method for regular prices for weekends
     * 
     * @param weekendPrices regular prices for weekends
     */
    public void setweekendPrices(float weekendPrices) {
        this.weekendPrices = weekendPrices;
    }

    /**
     * Getter method for regular price for public holidays
     * 
     * @return regular price for public holidays
     */
    public float getpHPrices() {
        return this.pHPrices;
    }

    /**
     * Setter method for regular prices for public holidays
     * 
     * @param pHPrices regular prices for public holidays
     */
    public void setpHPrices(float pHPrices) {
        this.pHPrices = pHPrices;
    }

    /**
     * Getter method for discount price for students
     * 
     * @return discount price for students
     */
    public float getstudentDiscount() {
        return this.studentDiscount;
    }

    /**
     * Setter method for discount price for senior citizens
     * 
     * @param studentDiscount discount price for senior citizens
     */
    public void setstudentDiscount(float studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

    /**
     * Getter method for discount price for senior citizens
     * 
     * @return discount price for senior citizens
     */
    public float getseniorDiscount() {
        return this.seniorDiscount;
    }

    /**
     * Setter method for discount price for senior citizens
     * 
     * @param seniorDiscount discount price for senior citizens
     */
    public void setseniorDiscount(float seniorDiscount) {
        this.seniorDiscount = seniorDiscount;
    }

    /**
     * Getter method for discount price for credit / loyalty card
     * 
     * @return discount price for credit / loyalty card
     */
    public float getprefCreditLoyaltyDiscount() {
        return this.prefCreditLoyaltyDiscount;
    }

    /**
     * Setter method for discount price for credit / loyalty card
     * 
     * @param prefCreditLoyaltyDiscount discount price for credit / loyalty card
     */
    public void setprefCreditLoyaltyDiscount(float prefCreditLoyaltyDiscount) {
        this.prefCreditLoyaltyDiscount = prefCreditLoyaltyDiscount;
    }

    /**
     * Getter method for additional price for showing after 6pm
     * 
     * @return additional price for showing after 6pm
     */
    public float getsixPMAddOn() {
        return this.sixPMAddOn;
    }

    /**
     * Setter method for additional price for showing after 6pm
     * 
     * @param sixPMAddOn additional price for showing after 6pm
     */
    public void setsixPMAddOn(float sixPMAddOn) {
        this.sixPMAddOn = sixPMAddOn;
    }

    /**
     * Getter method for additional price for 3D movies
     * 
     * @return additional price for 3D movies
     */
    public float getthreeDAddOn() {
        return this.threeDAddOn;
    }

    /**
     * Setter method for additional price for 3D movies
     * 
     * @param threeDAddOn additional price for 3D movies
     */
    public void setthreeDAddOn(float threeDAddOn) {
        this.threeDAddOn = threeDAddOn;
    }

    /**
     * Getter method for additional price for blockbuster movie
     * 
     * @return additional price for blockbuster movie
     */
    public float getblockbusterAddOn() {
        return this.blockbusterAddOn;
    }

    /**
     * Setter method for additional price for blockbuster movie
     * 
     * @param blockbusterAddOn additional price for blockbuster movie
     */
    public void setblockbusterAddOn(float blockbusterAddOn) {
        this.blockbusterAddOn = blockbusterAddOn;
    }

    /**
     * Getter method for additional price for IAMX Movies
     * 
     * @return additional price for IMAX movies
     */
    public float getIMAXAddOn() {
        return this.IMAXAddOn;
    }

    /**
     * Setter method for additional price for IAMX Movies
     * 
     * @param IMAXAddOn additional price for IMAX movies
     */
    public void setIMAXAddOn(float IMAXAddOn) {
        this.IMAXAddOn = IMAXAddOn;
    }

    /**
     * Getter method for additional price for platinum movie suite
     * 
     * @return additional price for platinum movie suite
     */
    public float getplatinumAddOn() {
        return this.platinumAddOn;
    }

    /**
     * Setter method for additional price for platinum movie suite
     * 
     * @param platinumAddOn additional price for platinum movie suite
     */
    public void setplatinumAddOn(float platinumAddOn) {
        this.platinumAddOn = platinumAddOn;
    }

    /**
     * Getter method for displaying top 5 movies menu by sales
     * 
     * @return 1 to display or 0 to hide the menu
     */
    public int getdisplayTop5bySales() {
        return this.displayTop5bySales;
    }

    /**
     * Getter method for displaying top 5 movies menu by sales
     * 
     * @param displayTop5bySales 1 to display or 0 to hide the menu
     */
    public void setdisplayTop5bySales(int displayTop5bySales) {
        this.displayTop5bySales = displayTop5bySales;
    }

    /**
     * Getter method for displaying top 5 movies menu by rating
     * 
     * @return 1 to display or 0 to hide the menu
     */
    public int getdisplayTop5byRating() {
        return this.displayTop5byRating;
    }

    /**
     * Getter method for displaying top 5 movies menu by sales
     * 
     * @param displayTop5byRating 1 to display or 0 to hide the menu
     */
    public void setdisplayTop5byRating(int displayTop5byRating) {
        this.displayTop5byRating = displayTop5byRating;
    }

}
