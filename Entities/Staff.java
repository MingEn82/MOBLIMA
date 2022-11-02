package Entities;

/**
 * Staff class that extend user.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class Staff extends User {
    /**
     * Password of staff
     */
    private String password;

    /**
     * Constructor for Staff Class
     */
    public Staff () {

    }
    /**
     * Constructor for Staff Class.
     * @param username
     * @param password
     */
    public Staff (String username, String password){
        this.setUsername(username);
        this.password = password;
    }

    /**
     * Getter method for password.
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * Setter method for password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
