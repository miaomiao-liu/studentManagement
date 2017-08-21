package springbootio.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.StudentDetail;
import springbootio.entity.persistence.StudentInfo;

/**
 * Created by miaomiao on 17-7-8.
 */
@Mapper
@Repository
public interface StudentDao {

    //根据学生id查找学生信息
    public StudentDetail queryStudentById(int id);

    //根据学生名字查找学生信息
    public StudentDetail queryStudentByName(String username);


    /**
     * 添加学生基本信息
     * @param studentDetail
     * @return int >0表示插入学生信息的行数 0：表示插入失败
     */
    public int addStudent(StudentDetail studentDetail);

    //更新学生基本信息
    public int updateStudent(StudentDetail studentDetail);

    //（注册）添加学生注册信息
    public int addStudentInfo(StudentInfo studentInfo);

    //查询学生注册信息
    public StudentInfo selectStudentInfoByName(String username);

    public StudentInfo selectStudentInfoByEmail(String email);


}
