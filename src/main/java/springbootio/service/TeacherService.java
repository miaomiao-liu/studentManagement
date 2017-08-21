package springbootio.service;

import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.exception.TeacherException;

/**
 * Created by miaomiao on 17-7-8.
 */
public interface TeacherService {

    public TeacherDetail queryTeacherDetailById(int id) throws TeacherException;

    public TeacherDetail queryTeacherDetailByName(String username) throws TeacherException;

    public int addTeacherDetail(TeacherDetail teacherDetail) throws TeacherException;

    public int updateTeacherDetail(TeacherDetail teacherDetail) throws TeacherException;

    public int registerTeacher(TeacherInfo teacherInfo) throws TeacherException;

    public TeacherInfo queryTeacherInfoByName(String username) throws TeacherException;

    public TeacherInfo queryTeacherInfo(int id) throws TeacherException;

    //老师登录生成token
    public String teacherLogin(String username,String password) throws TeacherException;
}
