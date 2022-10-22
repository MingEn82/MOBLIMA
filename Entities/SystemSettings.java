package Entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SystemSettings {

    private String filePath = "Database/SystemSettingsDatabase.txt";
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
}
