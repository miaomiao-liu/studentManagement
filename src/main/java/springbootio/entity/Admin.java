package springbootio.entity;

/**
 * Created by muyi on 17-4-18.
 */
public class Admin {

    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private long lastPasswordResetDate;

    public Admin(int id, String username, String password, String email, String role, long lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(long lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

}
