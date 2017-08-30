package springbootio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.entity.view.ResultData;
import springbootio.service.StudentService;
import springbootio.service.TeacherService;

/**
 * Created by miaomiao on 17-8-29.
 */
@RestController
@RequestMapping("/springbootio")
public class AuthController {

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    //学生添加注册信息 注册 返回学号
    @PostMapping("/registerStudent")
    public ResultData registerStudent(@RequestBody StudentInfo studentInfo){
        try{
            studentService.registerStudent(studentInfo);
            return new ResultData(true, studentInfo.getId(),"注册成功！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }


    //学生登录
    @RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
    public ResultData loginStudent(@RequestBody JwtAuthenticationRequest authenticationRequest){
        try {
            JwtAuthenticationResponse response = studentService.studentLogin(authenticationRequest);
            return new ResultData(true,response,"登录成功！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //老师注册 返回老师帐号
    @PostMapping("/registerTeacher")
    public ResultData registerTeacherInfo(@RequestBody TeacherInfo teacherInfo){
        try {
            teacherService.registerTeacher(teacherInfo);
            return new ResultData(true, teacherInfo.getId(),"注册成功！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //老师登录
    @PostMapping("/teacherLogin")
    public ResultData teacherLogin(@RequestBody JwtAuthenticationRequest authenticationRequest){
        try {
            JwtAuthenticationResponse response =teacherService.teacherLogin(authenticationRequest);
            return new ResultData(true,response,"登录成功！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }



}
