package springbootio.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.persistence.TeacherInfo;

/**
 * Created by miaomiao on 17-7-8.
 */
@Mapper
@Repository
public interface TeacherDao {

    //根据username查询详细个人信息
    TeacherDetail queryTeacherByName(String username);
    //根据id查询详细个人信息
    TeacherDetail queryTeacherByNumber(int teacherNumber);

    //添加个人信息
    int addTeacher(TeacherDetail teacherDetail);

    //更新老师个人信息
    int updateTeacher(TeacherDetail teacherDetail);



    //添加注册信息
    int addTeacherInfo(TeacherInfo teacherInfo);

    //
    TeacherInfo selectTeacherInfoByName(String username);

    //
    TeacherInfo selectTeacherInfoById(int id);

    TeacherInfo selectTeacherInfoByEmail(String email);
}
