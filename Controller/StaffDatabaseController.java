package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * This is an controller class that handles the reading and writing of database
 * for Staff
 */
public class StaffDatabaseController implements DatabaseController {
    private String filePath = "Database/StaffDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private HashMap<String, String> allStaff;

    /**
     * Contructor
     */
    public StaffDatabaseController() {
        file = new File(filePath);
        this.allStaff = new HashMap<String, String>();
        this.readFile();
    }

    /**
     * Constructor with filePath string
     * 
     * @param filePath filepath string of the text file
     */
    public StaffDatabaseController(String filePath) {
        file = new File(filePath);
        this.allStaff = new HashMap<String, String>();
        this.readFile();
    }

    /**
     * This method will read the text file and store into allStaff Array of HashMap
     */
    public void readFile() {
        try {
            BufferedReader brStream = new BufferedReader(new FileReader(file));
            String line = brStream.readLine();
            String[] staff;
            while (line != null) {
                staff = line.split(": ");
                allStaff.put(staff[0], staff[1]);
                line = brStream.readLine();
            }
            brStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the allStaff Array of HashMap
     * 
     * @return allStaff Array of HashMap
     */
    public HashMap<String, String> getAllStaff() {
        return allStaff;
    }

    /**
     * This method checks if the username and password exist in the allStaff Array
     * of HashMap
     * 
     * @param username username of the staff
     * @param password password of the staff
     * @return True if username exist and the password is tagged to the username,
     *         else False
     */
    public boolean login(String username, String password) {

        return allStaff.containsKey(username) && allStaff.get(username).equals(password);
    }

    /**
     * This method check if the newUsername exist in the allStaff Array of HashMap
     * If exist, will print a exist and return
     * Else add the newUsername and newPassword to the database and print
     * 
     * @param newUsername username of the account to be added to database
     * @param newPassword password of the account to be added to database
     */
    public void addNewStaff(String newUsername, String newPassword) {
        // username can be found
        if (allStaff.containsKey(newUsername)) {
            System.out.println("Error! User already exists!");
            return;
        }

        // insert the mapping into allStaff map
        allStaff.put(newUsername, newPassword);

        // write into file
        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            pw = new PrintWriter(bf);
            String content = "\n" + newUsername + ": " + newPassword;

            pw.append(content);

            System.out.println("Account successfully created for: [" + newUsername + "]");

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
