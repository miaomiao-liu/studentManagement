package springbootio.service;

import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.StudentDetail;
import springbootio.entity.persistence.StudentInfo;
import springbootio.exception.StudentException;

/**
 * Created by miaomiao on 17-7-8.
 */
public interface StudentService {

    //根据学生id查找学生信息
    public StudentDetail queryStudentDetailById(int id) throws StudentException;

    //根据学生名字查找学生信息
    public StudentDetail queryStudentDetailByName(String username) throws StudentException;

    //添加学生基本信息
    public int addStudentDetail(StudentDetail studentDetail) throws StudentException;

    //更新学生基本信息
    public int updateStudentDetail(StudentDetail studentDetail) throws StudentException;

    //添加学生注册信息
    public int registerStudent(StudentInfo studentInfo) throws StudentException;

    //查询学生注册信息
    public StudentInfo queryStudentInfo(String username) throws StudentException;

    //学生登录生成token
    public String studentLogin(String username,String password) throws StudentException;


}
