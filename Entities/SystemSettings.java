package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * This is an entity class that is deisgned to create a SystemSettings object
 */
public class SystemSettings {

    private String filePath = "Database/SystemSettingsDatabase.txt";
    private ArrayList<Date> publicHolidays;

    private float weekdayPrices;
    private float weekendPrices;
    private float pHPrices;
    private float studentDiscount;
    private float seniorDiscount;
    private float threeDAddOn;
    private float blockbusterAddOn;
    private float IMAXAddOn;
    private float platinumAddOn;

    /**
     * Constructor with attributes
     * 
     * @param publicHolidays   array list of public holidays
     * @param weekdayPrices    regular ticket price for weekday
     * @param weekendPrices    regular ticket price for weekend
     * @param pHPrices         regular ticket price for public holiday
     * @param studentDiscount  discount for student
     * @param seniorDiscount   discount for seniors
     * @param threeDAddOn      additional price for 3D movies
     * @param blockbusterAddOn additional price for blockbuster movies
     * @param IMAXAddOn        additional price for IMAX movies
     * @param platinumAddOn    additional price for platinum movie suite
     */
    public SystemSettings(ArrayList<Date> publicHolidays, float weekdayPrices,
            float weekendPrices, float pHPrices,
            float studentDiscount, float seniorDiscount, float threeDAddOn,
            float blockbusterAddOn, float IMAXAddOn, float platinumAddOn) {
        this.publicHolidays = publicHolidays;
        this.weekdayPrices = weekdayPrices;
        this.weekendPrices = weekendPrices;
        this.pHPrices = pHPrices;
        this.studentDiscount = studentDiscount;
        this.seniorDiscount = seniorDiscount;
        this.threeDAddOn = threeDAddOn;
        this.blockbusterAddOn = blockbusterAddOn;
        this.IMAXAddOn = IMAXAddOn;
        this.platinumAddOn = platinumAddOn;
    }

    /**
     * Constructor
     */
    public SystemSettings() {

    }

    /** This method prints out the existing ticket prices */
    public void printTicketSettings() {
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("************** Ticket Prices **************");
        System.out.println("Weekday Ticket:                     $" + String.format("%.2f", weekdayPrices));
        System.out.println("Weekend Ticket:                     $" + String.format("%.2f", weekendPrices));
        System.out.println("Public Holiday Ticket:              $" + String.format("%.2f", pHPrices));
        System.out.println("");
        System.out.println("**************** Discounts ****************");
        System.out.println("Student Discount:                  -$" + String.format("%.2f", studentDiscount));
        System.out.println("Senior Citizen Discount:           -$" + String.format("%.2f", seniorDiscount));
        System.out.println("");
        System.out.println("************ Additional Charges ***********");
        System.out.println("3D Movie:                          +$" + String.format("%.2f", threeDAddOn));
        System.out.println("Blockbuster Movie:                 +$" + String.format("%.2f", blockbusterAddOn));
        System.out.println("IMAX Movie:                        +$" + String.format("%.2f", IMAXAddOn));
        System.out.println("Platinum Movie Suite:              +$" + String.format("%.2f", platinumAddOn));
        System.out.println("");
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("Returning to previous menu...");
        System.out.println("");
    }

    /**
     * This method prints out the list of public holidays
     */
    public void printPHSettings() {
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("************** Exisitng Public Holidays **************");
        System.out.println("");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        for (int i = 1; i <= getPublicHolidays().size(); i++) {
            System.out.println(i + ". " + dateFormatter.format(getPublicHolidays().get(i - 1)));
        }

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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        String dateString = dateFormatter.format(date);
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

}
