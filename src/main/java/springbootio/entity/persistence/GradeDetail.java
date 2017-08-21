package springbootio.entity.persistence;

/**
 * Created by miaomiao on 17-7-8.
 */
public class GradeDetail {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getAllgrade() {
        return allgrade;
    }

    public void setAllgrade(int allgrade) {
        this.allgrade = allgrade;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    private String username;
    private int studentNumber;
    private int allgrade;
    private int math;
    private int english;

    public GradeDetail(String username,
                       int studentNumber,
                       int allgrade,
                       int math,
                       int english){
        this.username=username;
        this.studentNumber=studentNumber;
        this.allgrade=allgrade;
        this.math=math;
        this.english=english;

    }

    public GradeDetail() {
    }
}
