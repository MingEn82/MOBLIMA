package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import Entities.SystemSettings;
import Utils.DateParser;

public class SystemSettingsDatabaseController implements DatabaseController {
    /*
     * File Format
     * publicHolidays
     * mondayToWednesdayRegularTicketPrices
     * thursdayRegularTicketPrices
     * fridayRegularPeakTicketPrices
     * fridayRegularNonPeakTicketPrices
     * weekendRegularTicketPrices
     * studentRegularTicketPrices
     * seniorRegularTicketPrices
     * mondayToWednesday3DTicketPrices
     * thursday3DTicketPrices
     * friday3DPeakTicketPrices
     * friday3DNonPeakTicketPrices
     * weekend3DTicketPrices
     * student3DTicketPrices
     * blockbusterAdditionalPrice
     * platinumMovieSuiteAdditionalPrice
     * IMAXAdditionalPrice
     */

    private String filePath = "Database/SystemSettingsDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private ArrayList<Date> publicHolidays;
    private float mondayToWednesdayRegularTicketPrices;
    private float thursdayRegularTicketPrices;
    private float fridayRegularPeakTicketPrices;
    private float fridayRegularNonPeakTicketPrices;
    private float weekendRegularTicketPrices;
    private float studentRegularTicketPrices;
    private float seniorRegularTicketPrices;
    private float mondayToWednesday3DTicketPrices;
    private float thursday3DTicketPrices;
    private float friday3DPeakTicketPrices;
    private float friday3DNonPeakTicketPrices;
    private float weekend3DTicketPrices;
    private float student3DTicketPrices;
    private float blockbusterAdditionalPrice;
    private float platinumMovieSuiteAdditionalPrice;
    private float IMAXAdditionalPrice;

    public SystemSettingsDatabaseController() {
        this.file = new File(filePath);
        this.publicHolidays = new ArrayList<Date>();
        this.readFile();
    }

    public SystemSettingsDatabaseController(String filePath) {
        this.file = new File(filePath);
        this.publicHolidays = new ArrayList<Date>();
        this.readFile();
    }

    public void readFile() {
        try {
            BufferedReader brStream = new BufferedReader(new FileReader(file));

            // Add public holidays
            String[] dates = brStream.readLine().split(", ");
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
            for (String date : dates) {
                try {
                    publicHolidays.add(dateFormatter.parse(date));
                } catch (ParseException e) {
                }
            }

            mondayToWednesdayRegularTicketPrices = Float.parseFloat(brStream.readLine());
            thursdayRegularTicketPrices = Float.parseFloat(brStream.readLine());
            fridayRegularPeakTicketPrices = Float.parseFloat(brStream.readLine());
            fridayRegularNonPeakTicketPrices = Float.parseFloat(brStream.readLine());
            weekendRegularTicketPrices = Float.parseFloat(brStream.readLine());
            studentRegularTicketPrices = Float.parseFloat(brStream.readLine());
            seniorRegularTicketPrices = Float.parseFloat(brStream.readLine());
            mondayToWednesday3DTicketPrices = Float.parseFloat(brStream.readLine());
            thursday3DTicketPrices = Float.parseFloat(brStream.readLine());
            friday3DPeakTicketPrices = Float.parseFloat(brStream.readLine());
            friday3DNonPeakTicketPrices = Float.parseFloat(brStream.readLine());
            weekend3DTicketPrices = Float.parseFloat(brStream.readLine());
            student3DTicketPrices = Float.parseFloat(brStream.readLine());
            blockbusterAdditionalPrice = Float.parseFloat(brStream.readLine());
            platinumMovieSuiteAdditionalPrice = Float.parseFloat(brStream.readLine());
            IMAXAdditionalPrice = Float.parseFloat(brStream.readLine());

            brStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(SystemSettings ss) {
        this.publicHolidays = ss.getPublicHolidays();
        this.mondayToWednesdayRegularTicketPrices = ss.getMondayToWednesday3DTicketPrices();
        this.thursdayRegularTicketPrices = ss.getThursdayRegularTicketPrices();
        this.fridayRegularPeakTicketPrices = ss.getFridayRegularPeakTicketPrices();
        this.fridayRegularNonPeakTicketPrices = ss.getFridayRegularNonPeakTicketPrices();
        this.weekendRegularTicketPrices = ss.getWeekendRegularTicketPrices();
        this.studentRegularTicketPrices = ss.getStudentRegularTicketPrices();
        this.seniorRegularTicketPrices = ss.getSeniorRegularTicketPrices();
        this.mondayToWednesday3DTicketPrices = ss.getMondayToWednesday3DTicketPrices();
        this.thursday3DTicketPrices = ss.getThursday3DTicketPrices();
        this.friday3DPeakTicketPrices = ss.getFriday3DPeakTicketPrices();
        this.friday3DNonPeakTicketPrices = ss.getFriday3DNonPeakTicketPrices();
        this.weekend3DTicketPrices = ss.getWeekend3DTicketPrices();
        this.student3DTicketPrices = ss.getStudent3DTicketPrices();
        this.blockbusterAdditionalPrice = ss.getBlockbusterAdditionalPrice();
        this.platinumMovieSuiteAdditionalPrice = ss.getPlatinumMovieSuiteAdditionalPrice();
        this.IMAXAdditionalPrice = ss.getIMAXAdditionalPrice();

        this.writeToDatabase();

        // this.printSettings();
    }

    public void printSettings() {
        System.out.println("------ System Settings ------");
        System.out.println("mondayToWednesdayRegularTicketPrices: " + mondayToWednesdayRegularTicketPrices);
        System.out.println("thursdayRegularTicketPrices: " + thursdayRegularTicketPrices);
        System.out.println("fridayRegularPeakTicketPrices: " + fridayRegularPeakTicketPrices);
        System.out.println("fridayRegularNonPeakTicketPrices: " + fridayRegularNonPeakTicketPrices);
        System.out.println("weekendRegularTicketPrices: " + weekendRegularTicketPrices);
        System.out.println("studentRegularTicketPrices: " + studentRegularTicketPrices);
        System.out.println("seniorRegularTicketPrices: " + seniorRegularTicketPrices);
        System.out.println("mondayToWednesday3DTicketPrices: " + mondayToWednesday3DTicketPrices);
        System.out.println("thursday3DTicketPrices: " + thursday3DTicketPrices);
        System.out.println("friday3DPeakTicketPrices: " + friday3DPeakTicketPrices);
        System.out.println("friday3DNonPeakTicketPrices: " + friday3DNonPeakTicketPrices);
        System.out.println("weekend3DTicketPrices: " + weekend3DTicketPrices);
        System.out.println("student3DTicketPrices: " + student3DTicketPrices);
        System.out.println("blockbusterAdditionalPrice: " + blockbusterAdditionalPrice);
        System.out.println("platinumMovieSuiteAdditionalPrice: " + platinumMovieSuiteAdditionalPrice);
        System.out.println("IMAXAdditionalPrice: " + IMAXAdditionalPrice);
    }

    public void writeToDatabase() {
        DateParser dp = new DateParser("yyyyMMdd");
        try {
            bf = new BufferedWriter(new FileWriter(file, false));
            pw = new PrintWriter(bf);
            pw.println(this.publicHolidays.stream().map(date -> dp.formatDate(date)).collect(Collectors.joining(", ")));
            pw.println(this.mondayToWednesdayRegularTicketPrices);
            pw.println(this.thursdayRegularTicketPrices);
            pw.println(this.fridayRegularPeakTicketPrices);
            pw.println(this.fridayRegularNonPeakTicketPrices);
            pw.println(this.weekendRegularTicketPrices);
            pw.println(this.studentRegularTicketPrices);
            pw.println(this.seniorRegularTicketPrices);
            pw.println(this.mondayToWednesday3DTicketPrices);
            pw.println(this.thursday3DTicketPrices);
            pw.println(this.friday3DPeakTicketPrices);
            pw.println(this.friday3DNonPeakTicketPrices);
            pw.println(this.weekend3DTicketPrices);
            pw.println(this.student3DTicketPrices);
            pw.println(this.blockbusterAdditionalPrice);
            pw.println(this.platinumMovieSuiteAdditionalPrice);
            pw.print(this.IMAXAdditionalPrice);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
            } catch (Exception e) {
            }
        }
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
