package Entities;

/**
 * Abstract class for creating User object.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public abstract class User {

    /**
     * Username of user object
     */
    private String username;

    /**
     * Getter method for username.
     * @return username
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Setter method for username.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
