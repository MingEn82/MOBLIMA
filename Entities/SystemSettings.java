package Entities;

import java.util.ArrayList;
import java.util.Date;

public class SystemSettings {

    private String filePath = "Database/SystemSettingsDatabase.txt";
    private ArrayList<Date> publicHolidays;
    // Regular Price
    private float mondayToWednesdayRegularTicketPrices;
    private float thursdayRegularTicketPrices;
    private float fridayRegularPeakTicketPrices;
    private float fridayRegularNonPeakTicketPrices;
    private float weekendRegularTicketPrices;
    private float studentRegularTicketPrices;
    private float seniorRegularTicketPrices;
    private float mondayToWednesday3DTicketPrices;
    // 3D Price
    private float thursday3DTicketPrices;
    private float friday3DPeakTicketPrices;
    private float friday3DNonPeakTicketPrices;
    private float weekend3DTicketPrices;
    private float student3DTicketPrices;
    // Other additional charges
    private float blockbusterAdditionalPrice;
    private float platinumMovieSuiteAdditionalPrice;
    private float IMAXAdditionalPrice;

    public SystemSettings() {

    }

    public SystemSettings(ArrayList<Date> publicHolidays, float mondayToWednesdayRegularTicketPrices,
            float thursdayRegularTicketPrices, float fridayRegularPeakTicketPrices,
            float fridayRegularNonPeakTicketPrices, float weekendRegularTicketPrices, float studentRegularTicketPrices,
            float seniorRegularTicketPrices, float mondayToWednesday3DTicketPrices, float thursday3DTicketPrices,
            float friday3DPeakTicketPrices, float friday3DNonPeakTicketPrices, float weekend3DTicketPrices,
            float student3DTicketPrices, float blockbusterAdditionalPrice, float platinumMovieSuiteAdditionalPrice,
            float IMAXAdditionalPrice) {
        this.publicHolidays = publicHolidays;
        this.mondayToWednesdayRegularTicketPrices = mondayToWednesdayRegularTicketPrices;
        this.thursdayRegularTicketPrices = thursdayRegularTicketPrices;
        this.fridayRegularPeakTicketPrices = fridayRegularPeakTicketPrices;
        this.fridayRegularNonPeakTicketPrices = fridayRegularNonPeakTicketPrices;
        this.weekendRegularTicketPrices = weekendRegularTicketPrices;
        this.studentRegularTicketPrices = studentRegularTicketPrices;
        this.seniorRegularTicketPrices = seniorRegularTicketPrices;
        this.mondayToWednesday3DTicketPrices = mondayToWednesday3DTicketPrices;
        this.thursday3DTicketPrices = thursday3DTicketPrices;
        this.friday3DPeakTicketPrices = friday3DPeakTicketPrices;
        this.friday3DNonPeakTicketPrices = friday3DNonPeakTicketPrices;
        this.weekend3DTicketPrices = weekend3DTicketPrices;
        this.student3DTicketPrices = student3DTicketPrices;
        this.blockbusterAdditionalPrice = blockbusterAdditionalPrice;
        this.platinumMovieSuiteAdditionalPrice = platinumMovieSuiteAdditionalPrice;
        this.IMAXAdditionalPrice = IMAXAdditionalPrice;
    }

    public void printSettings() {
        System.out.println("---------------------------------------------------------");
        System.out.println("");
        System.out.println("*** Regular Movie Ticket Pricing ***");
        System.out.println("Monday to Wednesday: $" + mondayToWednesdayRegularTicketPrices);
        System.out.println("Thursday: $" + thursdayRegularTicketPrices);
        System.out.println("Friday (Peak): $" + fridayRegularPeakTicketPrices);
        System.out.println("Friday (Non Peak): $" + fridayRegularNonPeakTicketPrices);
        System.out.println("Weekends: $" + weekendRegularTicketPrices);
        System.out.println("Student: $" + studentRegularTicketPrices);
        System.out.println("Senior Citizen: $" + seniorRegularTicketPrices);
        System.out.println("");
        System.out.println("*** 3D Movie Ticket Pricing ***");
        System.out.println("Monday to Wednesday: $" + mondayToWednesday3DTicketPrices);
        System.out.println("Thursday: $" + thursday3DTicketPrices);
        System.out.println("Friday (Peak): $" + friday3DPeakTicketPrices);
        System.out.println("Friday (Non Peak): $" + friday3DNonPeakTicketPrices);
        System.out.println("Weekends: $" + weekend3DTicketPrices);
        System.out.println("Student: $" + student3DTicketPrices);
        System.out.println("");
        System.out.println("*** Other Additional Cost ***");
        System.out.println("Blockbuster: $" + blockbusterAdditionalPrice);
        System.out.println("Platinum Movie Suite: $" + platinumMovieSuiteAdditionalPrice);
        System.out.println("IMAX:$" + IMAXAdditionalPrice);
        System.out.println("");
        System.out.println("---------------------------------------------------------");
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Date> getPublicHolidays() {
        return this.publicHolidays;
    }

    public void setPublicHolidays(ArrayList<Date> publicHolidays) {
        this.publicHolidays = publicHolidays;
    }

    public float getMondayToWednesdayRegularTicketPrices() {
        return this.mondayToWednesdayRegularTicketPrices;
    }

    public void setMondayToWednesdayRegularTicketPrices(float mondayToWednesdayRegularTicketPrices) {
        this.mondayToWednesdayRegularTicketPrices = mondayToWednesdayRegularTicketPrices;
    }

    public float getThursdayRegularTicketPrices() {
        return this.thursdayRegularTicketPrices;
    }

    public void setThursdayRegularTicketPrices(float thursdayRegularTicketPrices) {
        this.thursdayRegularTicketPrices = thursdayRegularTicketPrices;
    }

    public float getFridayRegularPeakTicketPrices() {
        return this.fridayRegularPeakTicketPrices;
    }

    public void setFridayRegularPeakTicketPrices(float fridayRegularPeakTicketPrices) {
        this.fridayRegularPeakTicketPrices = fridayRegularPeakTicketPrices;
    }

    public float getFridayRegularNonPeakTicketPrices() {
        return this.fridayRegularNonPeakTicketPrices;
    }

    public void setFridayRegularNonPeakTicketPrices(float fridayRegularNonPeakTicketPrices) {
        this.fridayRegularNonPeakTicketPrices = fridayRegularNonPeakTicketPrices;
    }

    public float getWeekendRegularTicketPrices() {
        return this.weekendRegularTicketPrices;
    }

    public void setWeekendRegularTicketPrices(float weekendRegularTicketPrices) {
        this.weekendRegularTicketPrices = weekendRegularTicketPrices;
    }

    public float getStudentRegularTicketPrices() {
        return this.studentRegularTicketPrices;
    }

    public void setStudentRegularTicketPrices(float studentRegularTicketPrices) {
        this.studentRegularTicketPrices = studentRegularTicketPrices;
    }

    public float getSeniorRegularTicketPrices() {
        return this.seniorRegularTicketPrices;
    }

    public void setSeniorRegularTicketPrices(float seniorRegularTicketPrices) {
        this.seniorRegularTicketPrices = seniorRegularTicketPrices;
    }

    public float getMondayToWednesday3DTicketPrices() {
        return this.mondayToWednesday3DTicketPrices;
    }

    public void setMondayToWednesday3DTicketPrices(float mondayToWednesday3DTicketPrices) {
        this.mondayToWednesday3DTicketPrices = mondayToWednesday3DTicketPrices;
    }

    public float getThursday3DTicketPrices() {
        return this.thursday3DTicketPrices;
    }

    public void setThursday3DTicketPrices(float thursday3DTicketPrices) {
        this.thursday3DTicketPrices = thursday3DTicketPrices;
    }

    public float getFriday3DPeakTicketPrices() {
        return this.friday3DPeakTicketPrices;
    }

    public void setFriday3DPeakTicketPrices(float friday3DPeakTicketPrices) {
        this.friday3DPeakTicketPrices = friday3DPeakTicketPrices;
    }

    public float getFriday3DNonPeakTicketPrices() {
        return this.friday3DNonPeakTicketPrices;
    }

    public void setFriday3DNonPeakTicketPrices(float friday3DNonPeakTicketPrices) {
        this.friday3DNonPeakTicketPrices = friday3DNonPeakTicketPrices;
    }

    public float getWeekend3DTicketPrices() {
        return this.weekend3DTicketPrices;
    }

    public void setWeekend3DTicketPrices(float weekend3DTicketPrices) {
        this.weekend3DTicketPrices = weekend3DTicketPrices;
    }

    public float getStudent3DTicketPrices() {
        return this.student3DTicketPrices;
    }

    public void setStudent3DTicketPrices(float student3DTicketPrices) {
        this.student3DTicketPrices = student3DTicketPrices;
    }

    public float getBlockbusterAdditionalPrice() {
        return this.blockbusterAdditionalPrice;
    }

    public void setBlockbusterAdditionalPrice(float blockbusterAdditionalPrice) {
        this.blockbusterAdditionalPrice = blockbusterAdditionalPrice;
    }

    public float getPlatinumMovieSuiteAdditionalPrice() {
        return this.platinumMovieSuiteAdditionalPrice;
    }

    public void setPlatinumMovieSuiteAdditionalPrice(float platinumMovieSuiteAdditionalPrice) {
        this.platinumMovieSuiteAdditionalPrice = platinumMovieSuiteAdditionalPrice;
    }

    public float getIMAXAdditionalPrice() {
        return this.IMAXAdditionalPrice;
    }

    public void setIMAXAdditionalPrice(float IMAXAdditionalPrice) {
        this.IMAXAdditionalPrice = IMAXAdditionalPrice;
    }

}
