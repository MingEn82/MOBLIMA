package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class StaffDatabaseController implements DatabaseController {
    private String filePath = "Database/StaffDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private HashMap<String, String> allStaff;

    public StaffDatabaseController() {
        file = new File(filePath);
        this.allStaff = new HashMap<String, String>();
        this.readFile();
    }

    public StaffDatabaseController(String filePath) {
        file = new File(filePath);
        this.allStaff = new HashMap<String, String>();
        this.readFile();
    }

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

    public HashMap<String, String> getAllStaff() {
        return allStaff;
    }

    // Login Controller will take over this part
    public boolean login(String username, String password) {

        return allStaff.containsKey(username) && allStaff.get(username).equals(password);
    }

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
