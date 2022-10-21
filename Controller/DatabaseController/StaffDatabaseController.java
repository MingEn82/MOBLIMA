package Controller.DatabaseController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.HashMap;

public class StaffDatabaseController extends DatabaseController {
    private String fileString = "./Database/StaffDatabase.txt";
    private File file;
    private BufferedWriter bf;
    private PrintWriter pw;
    private HashMap<String, String> allStaff;

    public StaffDatabaseController() {
        file = new File(fileString);    
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
    
    public boolean login() {
        Scanner sc = new Scanner(System.in);
        String username, password;

        System.out.println("Enter username:");
        username = sc.next();
        System.out.println("Enter password");
        password = sc.next();

        return allStaff.containsKey(username) && allStaff.get(username).equals(password);
    }
    
    public void writeFile(String newUsername, String newPassword) {
        allStaff.put(newUsername, newPassword);

        try {
            bf = new BufferedWriter(new FileWriter(file, true));
            pw = new PrintWriter(bf);
            String content = "\n" + newUsername + ": " + newPassword;

            if (allStaff.size() > 0) {
                pw.println("");
            }
            pw.print(content);

            // iterate map entries 
            // for (Map.Entry<String, String> staff : allStaff.entrySet()) { 
            //     output = output + staff.getKey() + ": " + staff.getValue() + "\n";
            // }
            // output = output.trim();
            // bf.write(content);
            // bf.flush();

        } catch (IOException e) {
            e.printStackTrace(); 
        } finally {
            try { 
                pw.close();
            } catch (Exception e) {} 
        }
        
        System.out.println(allStaff);
    }
}
