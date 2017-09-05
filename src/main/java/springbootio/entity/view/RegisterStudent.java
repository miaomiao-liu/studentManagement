package springbootio.entity.view;

import springbootio.entity.persistence.StudentInfo;

/**
 * Created by miaomiao on 17-9-4.
 */
public class RegisterStudent {
    private StudentInfo studentInfo;
    private String verifyCode;

    public RegisterStudent(StudentInfo studentInfo, String verifyCode) {
        this.studentInfo = studentInfo;
        this.verifyCode = verifyCode;
    }

    public RegisterStudent() {
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
