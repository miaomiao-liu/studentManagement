package springbootio.entity.persistence;

/**
 * Created by miaomiao on 17-7-8.
 */
public class TeacherDetail {
    private int id;

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


    private String username;
    private String sex;
    private String university;
    private long phoneNumber;


    public TeacherDetail(int id,
                         String username,
                         String sex,
                         String university,
                         long phoneNumber){
        this.id=id;
        this.username=username;
        this.sex=sex;
        this.university=university;
        this.phoneNumber=phoneNumber;

    }

    public TeacherDetail() {
    }
}
