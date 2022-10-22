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
     * weekdayPrices
     * weekendPrices
     * pHPrices
     * studentDiscount
     * seniorDiscount
     * threeeDAddOn
     * blockbusterAddOn
     * IMAXAddOnÍ
     * platinumAddOn
     */

    private String filePath = "Database/SystemSettingsDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private ArrayList<Date> publicHolidays;
    private float weekdayPrices;
    private float weekendPrices;
    private float pHPrices;
    private float studentDiscount;
    private float seniorDiscount;
    private float threeeDAddOn;
    private float blockbusterAddOn;
    private float IMAXAddOn;
    private float platinumAddOn;

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
            weekdayPrices = Float.parseFloat(brStream.readLine());
            weekendPrices = Float.parseFloat(brStream.readLine());
            pHPrices = Float.parseFloat(brStream.readLine());
            studentDiscount = Float.parseFloat(brStream.readLine());
            seniorDiscount = Float.parseFloat(brStream.readLine());
            threeeDAddOn = Float.parseFloat(brStream.readLine());
            blockbusterAddOn = Float.parseFloat(brStream.readLine());
            IMAXAddOn = Float.parseFloat(brStream.readLine());
            platinumAddOn = Float.parseFloat(brStream.readLine());

            brStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(SystemSettings ss) {
        this.publicHolidays = ss.getPublicHolidays();
        this.weekdayPrices = ss.getweekdayPrices();
        this.weekendPrices = ss.getweekendPrices();
        this.pHPrices = ss.getpHPrices();
        this.studentDiscount = ss.getstudentDiscount();
        this.seniorDiscount = ss.getseniorDiscount();
        this.threeeDAddOn = ss.getthreeeDAddOn();
        this.blockbusterAddOn = ss.getblockbusterAddOn();
        this.IMAXAddOn = ss.getIMAXAddOn();
        this.platinumAddOn = ss.getplatinumAddOn();
        this.writeToDatabase();

    }

    // public void printSettings() {
    //     System.out.println("------ System Settings ------");
    //     System.out.println("publicHolidays: " + publicHolidays);
    //     System.out.println("weekdayPrices: " + weekdayPrices);
    //     System.out.println("weekendPrices: " + weekendPrices);
    //     System.out.println("pHPrices: " + pHPrices);
    //     System.out.println("studentDiscount: " + studentDiscount);
    //     System.out.println("seniorDiscount: " + seniorDiscount);
    //     System.out.println("threeeDAddOn: " + threeeDAddOn);
    //     System.out.println("blockbusterAddOn: " + blockbusterAddOn);
    //     System.out.println("IMAXAddOn: " + IMAXAddOn);
    //     System.out.println("platinumAddOn: " + platinumAddOn);
    // }

    public void writeToDatabase() {
        DateParser dp = new DateParser("yyyyMMdd");
        try {
            bf = new BufferedWriter(new FileWriter(file, false));
            pw = new PrintWriter(bf);
            pw.println(this.publicHolidays.stream().map(date -> dp.formatDate(date)).collect(Collectors.joining(", ")));
            pw.println(this.weekdayPrices);
            pw.println(this.weekendPrices);
            pw.println(this.pHPrices);
            pw.println(this.studentDiscount);
            pw.println(this.seniorDiscount);
            pw.println(this.threeeDAddOn);
            pw.println(this.blockbusterAddOn);
            pw.println(this.IMAXAddOn);
            pw.print(this.platinumAddOn);

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

    public float getthreeeDAddOn() {
        return this.threeeDAddOn;
    }

    public void setthreeeDAddOn(float threeeDAddOn) {
        this.threeeDAddOn = threeeDAddOn;
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
