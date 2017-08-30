package springbootio.service;

import springbootio.entity.persistence.StudentGrade;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.exception.TeacherException;

/**
 * Created by miaomiao on 17-7-8.
 */
public interface TeacherService {

    TeacherDetail queryTeacherDetailById(int teacherNumber) throws TeacherException;

    TeacherDetail queryTeacherDetailByName(String username) throws TeacherException;

    String teacherDetail(TeacherDetail teacherDetail,String username) throws TeacherException;

    void registerTeacher(TeacherInfo teacherInfo) throws TeacherException;

    //老师登录生成token
    JwtAuthenticationResponse teacherLogin(JwtAuthenticationRequest authenticationRequest) throws TeacherException;

    //添加学生成绩
    void addStudentGrade(StudentGrade studentGrade) throws TeacherException;

    //更新学生成绩
    void updateStudentGrade(StudentGrade studentGrade)throws TeacherException;
    //老师查询某个学生成绩
    StudentGrade queryStudentGrade(int studentNumber) throws TeacherException;
}
