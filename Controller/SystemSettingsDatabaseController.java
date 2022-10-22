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

import Utils.DateParser;

public class SystemSettingsDatabaseController extends DatabaseController {
    /*File Format
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
    private double mondayToWednesdayRegularTicketPrices;
    private double thursdayRegularTicketPrices;
    private double fridayRegularPeakTicketPrices;
    private double fridayRegularNonPeakTicketPrices;
    private double weekendRegularTicketPrices;
    private double studentRegularTicketPrices;
    private double seniorRegularTicketPrices;
    private double mondayToWednesday3DTicketPrices;
    private double thursday3DTicketPrices;
    private double friday3DPeakTicketPrices;
    private double friday3DNonPeakTicketPrices;
    private double weekend3DTicketPrices;
    private double student3DTicketPrices;
    private double blockbusterAdditionalPrice;
    private double platinumMovieSuiteAdditionalPrice;
    private double IMAXAdditionalPrice;

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
                } catch (ParseException e) {}
            }

            mondayToWednesdayRegularTicketPrices = Double.parseDouble(brStream.readLine());
            thursdayRegularTicketPrices = Double.parseDouble(brStream.readLine());
            fridayRegularPeakTicketPrices = Double.parseDouble(brStream.readLine());
            fridayRegularNonPeakTicketPrices = Double.parseDouble(brStream.readLine());
            weekendRegularTicketPrices = Double.parseDouble(brStream.readLine());
            studentRegularTicketPrices = Double.parseDouble(brStream.readLine());
            seniorRegularTicketPrices = Double.parseDouble(brStream.readLine());
            mondayToWednesday3DTicketPrices = Double.parseDouble(brStream.readLine());
            thursday3DTicketPrices = Double.parseDouble(brStream.readLine());
            friday3DPeakTicketPrices = Double.parseDouble(brStream.readLine());
            friday3DNonPeakTicketPrices = Double.parseDouble(brStream.readLine());
            weekend3DTicketPrices = Double.parseDouble(brStream.readLine());
            student3DTicketPrices = Double.parseDouble(brStream.readLine());
            blockbusterAdditionalPrice = Double.parseDouble(brStream.readLine());
            platinumMovieSuiteAdditionalPrice = Double.parseDouble(brStream.readLine());
            IMAXAdditionalPrice = Double.parseDouble(brStream.readLine());
            
            brStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(ArrayList<Date> publicHolidays, double mondayToWednesdayRegularTicketPrices, double thursdayRegularTicketPrices, double fridayRegularPeakTicketPrices, double fridayRegularNonPeakTicketPrices, double weekendRegularTicketPrices, double studentRegularTicketPrices, double seniorRegularTicketPrices, double mondayToWednesday3DTicketPrices, double thursday3DTicketPrices, double friday3DPeakTicketPrices, double friday3DNonPeakTicketPrices, double weekend3DTicketPrices, double student3DTicketPrices, double blockbusterAdditionalPrice, double platinumMovieSuiteAdditionalPrice, double IMAXAdditionalPrice) {
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

        this.writeToDatabase();
        this.printSettings();
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
            } catch (Exception e) {} 
        }
    }
}
