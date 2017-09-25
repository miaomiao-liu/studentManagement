package cn.edu.swpu.cins.studentManagement.entity.persistence;

/**
 * Created by miaomiao on 17-7-8.
 */
public class TeacherDetail {
    private int id;
    private int teacherNumber;
    private String username;
    private String sex;
    private String university;
    private long phoneNumber;

    public TeacherDetail() {
    }

    public TeacherDetail(int id, int teacherNumber, String username, String sex, String university, long phoneNumber) {
        this.id = id;
        this.teacherNumber = teacherNumber;
        this.username = username;
        this.sex = sex;
        this.university = university;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
