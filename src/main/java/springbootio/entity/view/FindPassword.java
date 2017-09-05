package springbootio.entity.view;

/**
 * Created by miaomiao on 17-8-31.
 */
public class FindPassword {

    private String username;
    private String newPassword;

    public FindPassword() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
