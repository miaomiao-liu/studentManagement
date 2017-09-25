package cn.edu.swpu.cins.studentManagement.entity.persistence;

/**
 * Created by miaomiao on 17-7-10.
 */
public class TeacherInfo {

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

    private int id;
    private String username;
    private String password;
    private String email;

    public TeacherInfo(){};

    public TeacherInfo(int id,
                       String username,
                       String password,
                       String email){
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;

    }
}
