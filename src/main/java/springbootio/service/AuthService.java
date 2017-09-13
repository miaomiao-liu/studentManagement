package springbootio.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.FindPassword;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.entity.view.RegisterStudent;
import springbootio.exception.StudentException;
import springbootio.exception.TeacherException;

import java.util.Map;

/**
 * Created by miaomiao on 17-9-2.
 */
public interface AuthService {

    //注册获取验证码
    Map<String,Object> getVerifyCodeForRegister(String username,String mail) throws Exception;

    //找回密码获取验证码
    Map<String,Object> getVerifyCodeForUpdatePwd(String username,String mail) throws Exception;

    //找回密码
    int FindPassword(FindPassword findPassword) throws Exception;

    //添加学生注册信息
    Map<String,Object> registerStudent(StudentInfo studentInfo) throws StudentException;

    //学生登录生成token
    JwtAuthenticationResponse studentLogin(JwtAuthenticationRequest authenticationRequest) throws StudentException;

    //添加老师注册信息
    Map<String,Object> registerTeacher(TeacherInfo teacherInfo) throws TeacherException;

    //老师登录生成token
    JwtAuthenticationResponse teacherLogin(JwtAuthenticationRequest authenticationRequest) throws TeacherException;
}
