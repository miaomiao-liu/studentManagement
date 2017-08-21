package springbootio.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.persistence.TeacherInfo;

/**
 * Created by miaomiao on 17-7-8.
 */
@Mapper
@Repository
public interface TeacherDao {

    //根据id查询详细个人信息
    public TeacherDetail queryTeacherById(int id);

    //根据username查询详细个人信息
    public TeacherDetail queryTeacherByName(String username);

    //添加个人信息
    public int addTeacher(TeacherDetail teacherDetail);

    //更新老师个人信息
    public int updateTeacher(TeacherDetail teacherDetail);

    //添加注册信息
    public int addTeacherInfo(TeacherInfo teacherInfo);

    //
    public TeacherInfo selectTeacherInfoByName(String username);

    //
    public TeacherInfo queryTeacherInfoById(int id);

    public TeacherInfo selectTeacherInfoByEmail(String email);
}
