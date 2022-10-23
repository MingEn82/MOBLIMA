package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

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

    public SystemSettings() {

    }

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

    public void printPHSettings() {
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("************** Exisitng Public Holidays **************");
        System.out.println("");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        for (int i = 1; i <= getPublicHolidays().size(); i++ ){
            System.out.println(i + ". " + dateFormatter.format(getPublicHolidays().get(i-1)));
        }
        
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void addPublicHolidays(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        String dateString = dateFormatter.format(date);
        System.out.println("Adding ["+ dateString +"] to the system...");
        System.out.println("");
        this.publicHolidays.add(date);
        System.out.println("["+ dateString +"] has been successfully added to the system");
        System.out.println("");
    }

    public void removePublicHolidays(int index) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE dd MMM yyyy");
        String dateString = dateFormatter.format(getPublicHolidays().get(index-1));
        System.out.println("Removing ["+ dateString +"] from the system...");
        System.out.println("");
        this.publicHolidays.remove(index-1);    
        System.out.println("[" + dateString + "] has been sucessfully removed from the system.");
    }


    public ArrayList<Date> getPublicHolidays() {
        return this.publicHolidays;
    }

    public void setPublicHolidays(ArrayList<Date> publicHolidays) {
        this.publicHolidays = publicHolidays;
    }

    public float getweekdayPrices() {
        return this.weekdayPrices;
    }

    public void setweekdayPrices(float weekdayPrices) {
        this.weekdayPrices = weekdayPrices;
    }

    public float getweekendPrices() {
        return this.weekendPrices;
    }

    public void setweekendPrices(float weekendPrices) {
        this.weekendPrices = weekendPrices;
    }

    public float getpHPrices() {
        return this.pHPrices;
    }

    public void setpHPrices(float pHPrices) {
        this.pHPrices = pHPrices;
    }

    public float getstudentDiscount() {
        return this.studentDiscount;
    }

    public void setstudentDiscount(float studentDiscount) {
        this.studentDiscount = studentDiscount;
    }

    public float getseniorDiscount() {
        return this.seniorDiscount;
    }

    public void setseniorDiscount(float seniorDiscount) {
        this.seniorDiscount = seniorDiscount;
    }

    public float getthreeDAddOn() {
        return this.threeDAddOn;
    }

    public void setthreeDAddOn(float threeDAddOn) {
        this.threeDAddOn = threeDAddOn;
    }

    public float getblockbusterAddOn() {
        return this.blockbusterAddOn;
    }

    public void setblockbusterAddOn(float blockbusterAddOn) {
        this.blockbusterAddOn = blockbusterAddOn;
    }

    public float getIMAXAddOn() {
        return this.IMAXAddOn;
    }

    public void setIMAXAddOn(float IMAXAddOn) {
        this.IMAXAddOn = IMAXAddOn;
    }

    public float getplatinumAddOn() {
        return this.platinumAddOn;
    }

    public void setplatinumAddOn(float platinumAddOn) {
        this.platinumAddOn = platinumAddOn;
    }



}
