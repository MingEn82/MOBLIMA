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

public class SystemSettingsDatabaseController implements DatabaseController {
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
                } catch (ParseException e) {}
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

    public void writeFile(ArrayList<Date> publicHolidays, float mondayToWednesdayRegularTicketPrices, float thursdayRegularTicketPrices, float fridayRegularPeakTicketPrices, float fridayRegularNonPeakTicketPrices, float weekendRegularTicketPrices, float studentRegularTicketPrices, float seniorRegularTicketPrices, float mondayToWednesday3DTicketPrices, float thursday3DTicketPrices, float friday3DPeakTicketPrices, float friday3DNonPeakTicketPrices, float weekend3DTicketPrices, float student3DTicketPrices, float blockbusterAdditionalPrice, float platinumMovieSuiteAdditionalPrice, float IMAXAdditionalPrice) {
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
