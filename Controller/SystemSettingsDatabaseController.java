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
     * threeDAddOn
     * blockbusterAddOn
     * IMAXAddOnÍ
     * platinumAddOn
     */

    private String filePath = "Database/SystemSettingsDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private SystemSettings ss;
    // private ArrayList<Date> publicHolidays;
    // private float weekdayPrices;
    // private float weekendPrices;
    // private float pHPrices;
    // private float studentDiscount;
    // private float seniorDiscount;
    // private float threeDAddOn;
    // private float blockbusterAddOn;
    // private float IMAXAddOn;
    // private float platinumAddOn;

    public SystemSettingsDatabaseController() {
        this.file = new File(filePath);
        this.ss = new SystemSettings();
        this.readFile();
    }

    public SystemSettingsDatabaseController(String filePath) {
        this.file = new File(filePath);
        this.ss = new SystemSettings();
        this.readFile();
    }

    public SystemSettings fetchSystemSettings () {
        // this.readFile();
        return this.ss;
    }

    public void readFile() {
        try {
            BufferedReader brStream = new BufferedReader(new FileReader(file));

            // Temp get and store variables
            // PH
            ArrayList<Date> publicHolidays = new ArrayList<Date>();
            float weekdayPrices;
            float weekendPrices;
            float pHPrices;
            float studentDiscount;
            float seniorDiscount;
            float threeDAddOn;
            float blockbusterAddOn;
            float IMAXAddOn;
            float platinumAddOn;
            String[] dates = brStream.readLine().split(", ");
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
            for (String date : dates) {
                try {
                    publicHolidays.add(dateFormatter.parse(date));
                } catch (ParseException e) {
                }
            }
            // Prices
            weekdayPrices = Float.parseFloat(brStream.readLine());
            weekendPrices = Float.parseFloat(brStream.readLine());
            pHPrices = Float.parseFloat(brStream.readLine());
            studentDiscount = Float.parseFloat(brStream.readLine());
            seniorDiscount = Float.parseFloat(brStream.readLine());
            threeDAddOn = Float.parseFloat(brStream.readLine());
            blockbusterAddOn = Float.parseFloat(brStream.readLine());
            IMAXAddOn = Float.parseFloat(brStream.readLine());
            platinumAddOn = Float.parseFloat(brStream.readLine());

            // Construct ss using the variables above
            this.ss = new SystemSettings(publicHolidays, weekdayPrices, weekendPrices, pHPrices, studentDiscount, seniorDiscount, threeDAddOn, blockbusterAddOn, IMAXAddOn, platinumAddOn);

            brStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(SystemSettings ss) {
        this.ss.setPublicHolidays(ss.getPublicHolidays());
        this.ss.setweekdayPrices(ss.getweekdayPrices());
        this.ss.setweekendPrices(ss.getweekendPrices());
        this.ss.setpHPrices(ss.getpHPrices());
        this.ss.setstudentDiscount(ss.getstudentDiscount());
        this.ss.setseniorDiscount(ss.getseniorDiscount());
        this.ss.setthreeDAddOn(ss.getthreeDAddOn());
        this.ss.setblockbusterAddOn(ss.getblockbusterAddOn());
        this.ss.setIMAXAddOn(ss.getIMAXAddOn());
        this.ss.setplatinumAddOn(ss.getplatinumAddOn());
        this.writeToDatabase();

    }

    public void writeToDatabase() {
        DateParser dp = new DateParser("yyyyMMdd");
        try {
            bf = new BufferedWriter(new FileWriter(file, false));
            pw = new PrintWriter(bf);
            pw.println(this.ss.getPublicHolidays().stream().map(date -> dp.formatDate(date)).collect(Collectors.joining(", ")));
            pw.println(this.ss.getweekdayPrices());
            pw.println(this.ss.getweekendPrices());
            pw.println(this.ss.getpHPrices());
            pw.println(this.ss.getstudentDiscount());
            pw.println(this.ss.getseniorDiscount());
            pw.println(this.ss.getthreeDAddOn());
            pw.println(this.ss.getblockbusterAddOn());
            pw.println(this.ss.getIMAXAddOn());
            pw.print(this.ss.getplatinumAddOn());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
            } catch (Exception e) {
            }
        }
    }


}
