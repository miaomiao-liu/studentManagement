package springbootio.entity;

import java.util.List;

/**
 * Created by muyi on 17-4-4.
 */
public class User {

    private int id;

    private String username;

    private String password;

    private String role;

    private long lastPasswordResetDate;

    public User(int id, String name, String password, String role, long lastPasswordResetDate) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.role = role;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {}

    public long getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(long lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
