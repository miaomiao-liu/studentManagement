package cn.edu.swpu.cins.studentManagement.dao;

import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentDetail;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentInfo;

/**
 * Created by miaomiao on 17-7-8.
 */
@Mapper
@Repository
public interface StudentDao {

    //根据学生名字查找学生信息
    StudentDetail queryStudentByName(String username);
    //根据学生学号查找学生信息
    StudentDetail queryStudentByNumber(int studentNumber);


    /**
     * 添加学生基本信息
     * @param
     * @return int >0表示插入学生信息的行数 0：表示插入失败
     */
    int addStudent(StudentDetail studentDetail);

    //更新学生基本信息
    int updateStudent(StudentDetail studentDetail);



    //（注册）添加学生注册信息
    int addStudentInfo(StudentInfo studentInfo);

    //查询学生注册信息
    StudentInfo selectStudentInfoById(int id);

    StudentInfo selectStudentInfoByName(String username);

    StudentInfo selectStudentInfoByEmail(String email);

    int updatePassword(@Param("password") String password,@Param("username") String userame);

}
