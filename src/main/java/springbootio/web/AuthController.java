package springbootio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.*;
import springbootio.enums.RegisterEnum;
import springbootio.enums.VerifyEnum;
import springbootio.service.AuthService;
import springbootio.service.MailService;

import java.util.Map;

/**
 * Created by miaomiao on 17-8-29.
 */
@RestController
@RequestMapping("/springbootio")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    MailService mailService;

    //获取验证码
    @PostMapping("/verifyCodeForRegister")
    public ResultData getVerifyCodeForRegister(@RequestParam("username") String username,
                                               @RequestParam("mail") String mail){
        try {
            Map<String,Object> map = authService.getVerifyCodeForRegister(username,mail);
            if(map.isEmpty()) {
                return new ResultData(true, VerifyEnum.CHECK_REGISTER_MAIL);
            }
            return new ResultData(false,map);
        } catch (Exception e) {
            return new ResultData(false,e.getMessage());
        }
    }


    //找回密码获取验证码
    @PostMapping("/verifyCodeForFindPwd")
    public ResultData getVerifyCodeForFindPwd(@RequestParam("username") String username,
                                              @RequestParam("mail") String mail){
        try {
            Map<String,Object> map = authService.getVerifyCodeForUpdatePwd(username,mail);
            if(map.isEmpty()) {
                return new ResultData(true, VerifyEnum.CHECK_PWD_MAIL);
            }
            return new ResultData(false,map);
        } catch (Exception e) {
            return new ResultData(false,e.getMessage());
        }
    }

    //找回密码
    @PostMapping("/findPassword")
    public ResultData findPassword(@RequestBody FindPassword findPassword) {
        try {
            if(authService.FindPassword(findPassword) == 1){
                return new ResultData(true, "更新密码成功！");
            }
            return new ResultData(false,"更新密码失败！");
        } catch (Exception e) {
            return new ResultData(false,e.getMessage());
        }
    }

    //前端验证之后，学生添加注册信息 注册 返回学号
    @PostMapping("/registerStudent")
    public ResultData registerStudent(@RequestBody StudentInfo studentInfo){
        try{
            Map<String,Object> map = authService.registerStudent(studentInfo);
            if(map.isEmpty()) {
                return new ResultData(true, studentInfo.getId(), RegisterEnum.REGISTER_SUCCESS.getMsg());
            }else {
                return new ResultData(false,map);
            }
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }


    //学生登录
    @RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
    public ResultData loginStudent(@RequestBody JwtAuthenticationRequest authenticationRequest){
        try {
            JwtAuthenticationResponse response = authService.studentLogin(authenticationRequest);
            return new ResultData(true,response,"登录成功！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //老师注册 返回老师帐号
    @PostMapping("/registerTeacher")
    public ResultData registerTeacherInfo(@RequestBody TeacherInfo teacherInfo){
        try {
            Map<String,Object> map = authService.registerTeacher(teacherInfo);
            if(map.isEmpty()) {
                return new ResultData(true, teacherInfo.getId(), RegisterEnum.REGISTER_SUCCESS.getMsg());
            }else {
                return new ResultData(false,map);
            }
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //老师登录
    @PostMapping("/teacherLogin")
    public ResultData teacherLogin(@RequestBody JwtAuthenticationRequest authenticationRequest){
        try {
            JwtAuthenticationResponse response =authService.teacherLogin(authenticationRequest);
            return new ResultData(true,response,"登录成功！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }


}
