package Entities;

/**
 * Abstract class for creating User object.
 */
public abstract class User {
    private String username;

    /**
     * Getter method for username.
     * @return
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
