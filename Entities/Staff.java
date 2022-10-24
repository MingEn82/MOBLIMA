package Entities;

/**
 * Staff class that extend user.
 */
public class Staff extends User {
    private String password;

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
     * @return
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
