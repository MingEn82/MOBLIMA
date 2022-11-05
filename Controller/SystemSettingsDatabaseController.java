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

/**
 * This is an controller class that handles the reading and writing of database
 * for SystemSetings
 * 
 * @author Teoh Xi Sheng
 * @version 1.0
 * @since 2022-11-02
 */
public class SystemSettingsDatabaseController implements DatabaseController {
    /*
     * File Format
     * publicHolidays
     * weekdayPrices
     * weekendPrices
     * pHPrices
     * studentDiscount
     * seniorDiscount
     * prefCreditLoyaltyDiscount
     * sixPMAddOn
     * threeDAddOn
     * blockbusterAddOn
     * IMAXAddOn
     * platinumAddOn
     * displayTop5bySales
     * displayTop5byRating
     */

    /**
     * Directory of the SystemSettingsDatabase text file
     */
    private String filePath = "Database/SystemSettingsDatabase.txt";

    /**
     * File object
     */
    private File file;

    /**
     * Writes text to character output stream
     */
    private BufferedWriter bf;

    /**
     * Prints objects to text-output stream
     */
    private PrintWriter pw;

    /**
     * Initiate a SystemSettings object
     */
    private SystemSettings ss;

    /**
     * Delimiter
     */
    private static final String delimiter = "<b>";

    /**
     * Constructor
     */
    public SystemSettingsDatabaseController() {
        this.file = new File(filePath);
        this.ss = new SystemSettings();
        this.readFile();
    }

    /**
     * Constructor with filePath string
     * 
     * @param filePath filepath string of the text file
     */
    public SystemSettingsDatabaseController(String filePath) {
        this.file = new File(filePath);
        this.ss = new SystemSettings();
        this.readFile();
    }

    /**
     * This method returns the ss SystemSettings object
     * 
     * @return ss SystemSettings object
     */
    public SystemSettings fetchSystemSettings() {
        // this.readFile();
        return this.ss;
    }

    /**
     * This method will read the text file and store into ss SystemSettings object
     */
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
            float prefCreditLoyaltyDiscount;
            float sixPMAddOn;
            float threeDAddOn;
            float blockbusterAddOn;
            float IMAXAddOn;
            float platinumAddOn;
            float wideSeatAddOn;
            int displayTop5bySales;
            int displayTop5byRating;
            String[] dates = brStream.readLine().split(delimiter);
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
            prefCreditLoyaltyDiscount = Float.parseFloat(brStream.readLine());
            sixPMAddOn = Float.parseFloat(brStream.readLine());
            threeDAddOn = Float.parseFloat(brStream.readLine());
            blockbusterAddOn = Float.parseFloat(brStream.readLine());
            IMAXAddOn = Float.parseFloat(brStream.readLine());
            platinumAddOn = Float.parseFloat(brStream.readLine());
            wideSeatAddOn = Float.parseFloat(brStream.readLine());
            displayTop5bySales = Integer.parseInt(brStream.readLine());
            displayTop5byRating = Integer.parseInt(brStream.readLine());

            // Construct ss using the variables above
            this.ss = new SystemSettings(publicHolidays, weekdayPrices, weekendPrices, pHPrices, studentDiscount,
                    seniorDiscount, prefCreditLoyaltyDiscount, sixPMAddOn, threeDAddOn, blockbusterAddOn, IMAXAddOn,
                    platinumAddOn, wideSeatAddOn, displayTop5bySales,
                    displayTop5byRating);

            brStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will take in SystemSettings ss object and store into the existing
     * SystemSettings object
     * and call writeToDatabase() to write to database using the existing updated
     * SystemSettings ss object
     * 
     * @param ss SystemSettings object
     */
    public void writeFile(SystemSettings ss) {
        this.ss.setPublicHolidays(ss.getPublicHolidays());
        this.ss.setweekdayPrices(ss.getweekdayPrices());
        this.ss.setweekendPrices(ss.getweekendPrices());
        this.ss.setpHPrices(ss.getpHPrices());
        this.ss.setstudentDiscount(ss.getstudentDiscount());
        this.ss.setseniorDiscount(ss.getseniorDiscount());
        this.ss.setprefCreditLoyaltyDiscount(ss.getprefCreditLoyaltyDiscount());
        this.ss.setsixPMAddOn(ss.getsixPMAddOn());
        this.ss.setthreeDAddOn(ss.getthreeDAddOn());
        this.ss.setblockbusterAddOn(ss.getblockbusterAddOn());
        this.ss.setIMAXAddOn(ss.getIMAXAddOn());
        this.ss.setplatinumAddOn(ss.getplatinumAddOn());
        this.ss.setWideSeatAddOn(ss.getWideSeatAddOn());
        this.ss.setdisplayTop5bySales(ss.getdisplayTop5bySales());
        this.ss.setdisplayTop5byRating(ss.getdisplayTop5byRating());
        this.writeToDatabase();
    }

    /**
     * This method will write the informaion of the SystemSettings ss object to the
     * database file
     */
    public void writeToDatabase() {
        DateParser dp = new DateParser("yyyyMMdd");
        try {
            bf = new BufferedWriter(new FileWriter(file, false));
            pw = new PrintWriter(bf);
            pw.println(this.ss.getPublicHolidays().stream().map(date -> dp.formatDate(date))
                    .collect(Collectors.joining(delimiter)));
            pw.println(this.ss.getweekdayPrices());
            pw.println(this.ss.getweekendPrices());
            pw.println(this.ss.getpHPrices());
            pw.println(this.ss.getstudentDiscount());
            pw.println(this.ss.getseniorDiscount());
            pw.println(this.ss.getprefCreditLoyaltyDiscount());
            pw.println(this.ss.getsixPMAddOn());
            pw.println(this.ss.getthreeDAddOn());
            pw.println(this.ss.getblockbusterAddOn());
            pw.println(this.ss.getIMAXAddOn());
            pw.println(this.ss.getplatinumAddOn());
            pw.println(this.ss.getWideSeatAddOn());
            pw.println(this.ss.getdisplayTop5bySales());
            pw.print(this.ss.getdisplayTop5byRating());

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
