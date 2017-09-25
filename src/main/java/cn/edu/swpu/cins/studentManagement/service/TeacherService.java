package cn.edu.swpu.cins.studentManagement.service;

import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherDetail;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherDetail;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherInfo;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationRequest;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationResponse;
import cn.edu.swpu.cins.studentManagement.exception.TeacherException;

/**
 * Created by miaomiao on 17-7-8.
 */
public interface TeacherService {

    TeacherDetail queryTeacherDetailById(int teacherNumber) throws TeacherException;

    TeacherDetail queryTeacherDetailByName(String username) throws TeacherException;

    String teacherDetail(TeacherDetail teacherDetail,String username) throws TeacherException;

    //添加学生成绩
    void addStudentGrade(StudentGrade studentGrade) throws TeacherException;

    //更新学生成绩
    void updateStudentGrade(StudentGrade studentGrade)throws TeacherException;
    //老师查询某个学生成绩
    StudentGrade queryStudentGrade(int studentNumber) throws TeacherException;
}
