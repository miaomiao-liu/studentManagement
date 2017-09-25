package cn.edu.swpu.cins.studentManagement.service;

import cn.edu.swpu.cins.studentManagement.entity.view.FindPassword;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationRequest;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentInfo;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherInfo;
import cn.edu.swpu.cins.studentManagement.entity.view.FindPassword;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationRequest;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationResponse;
import cn.edu.swpu.cins.studentManagement.entity.view.RegisterStudent;
import cn.edu.swpu.cins.studentManagement.exception.StudentException;
import cn.edu.swpu.cins.studentManagement.exception.TeacherException;

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
