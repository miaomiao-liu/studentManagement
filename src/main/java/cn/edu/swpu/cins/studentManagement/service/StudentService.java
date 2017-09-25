package cn.edu.swpu.cins.studentManagement.service;

import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentDetail;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;
import cn.edu.swpu.cins.studentManagement.exception.StudentException;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentDetail;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentInfo;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationRequest;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationResponse;
import cn.edu.swpu.cins.studentManagement.exception.StudentException;

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

    //学生查询成绩
    StudentGrade queryGrade(int studentNumber) throws StudentException;

    //学生更新密码
//    void updatePassword() throws StudentException;


}
