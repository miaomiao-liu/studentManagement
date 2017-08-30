package springbootio.service;

import springbootio.entity.persistence.StudentDetail;
import springbootio.entity.persistence.StudentGrade;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.exception.StudentException;

/**
 * Created by miaomiao on 17-7-8.
 */
public interface StudentService {

    //根据学生id查找学生信息
   StudentDetail queryStudentDetailById(int studentNumber) throws StudentException;

    //根据学生名字查找学生信息
   StudentDetail queryStudentDetailByName(String username) throws StudentException;

    //添加学生基本信息
   String studentDetail(StudentDetail studentDetail,String username) throws StudentException;

    //添加学生注册信息
    void registerStudent(StudentInfo studentInfo) throws StudentException;

    //学生登录生成token
    JwtAuthenticationResponse studentLogin( JwtAuthenticationRequest authenticationRequest) throws StudentException;

    //学生查询成绩
    StudentGrade queryGrade(int studentNumber) throws StudentException;
}
