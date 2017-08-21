package springbootio.entity.persistence;

/**
 * Created by miaomiao on 17-7-8.
 */
public class StudentDetail {
    private int id;
    private String username;
    private String sex;
    private String university;

    private long phoneNumber;

    public StudentDetail(int id,
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
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

    public StudentDetail() {
    }
}
