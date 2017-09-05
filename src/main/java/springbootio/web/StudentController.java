package springbootio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import springbootio.dao.GradeDao;
import springbootio.entity.persistence.StudentDetail;
import springbootio.entity.persistence.StudentGrade;
import springbootio.entity.view.ResultData;
import springbootio.service.StudentService;
import springbootio.util.GetUsrName;

import javax.servlet.http.HttpServletRequest;
import java.util.Scanner;

/**
 * Created by miaomiao on 17-7-8.
 */
@RestController
@RequestMapping("/Springbootio")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private GetUsrName getUsrName;

    //根据studentNumber查询学生信息
    @GetMapping("/queryStudentNumber/{studentNumber}")
    public ResultData queryStudentByStudentNumber(@PathVariable int studentNumber) {
        try {
            StudentDetail studentDetail = studentService.queryStudentDetailById(studentNumber);
            return new ResultData(true,studentDetail);
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
}

//根据姓名查询学生信息
    @GetMapping("/queryStudentName/{name}")
    public ResultData queryStudentByName(@PathVariable String name) {
        try{
            StudentDetail studentDetail = studentService.queryStudentDetailByName(name);
            return new ResultData(true,studentDetail);
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //注册之后
    //添加学生信息 更新学生信息
    @PostMapping(value = "/studentDetail")
    public ResultData registerStudent(@RequestBody StudentDetail studentDetail,HttpServletRequest request) {
        try {
            String username = getUsrName.AllProjects(request);
            String msg=studentService.studentDetail(studentDetail,username);
            return new ResultData(true,studentDetail,msg);
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
}

//学生查询成绩
@GetMapping(value = "/studentQueryGrade")
public ResultData queryStudentGradeByStudentNumber(@RequestParam int  studentNumber) {
    try {
        StudentGrade studentGrade = studentService.queryGrade(studentNumber);
        return new ResultData(true,studentGrade);
    }catch (Exception e){
            return new ResultData(false,e.getMessage());
        }
}

//学生修改密码  验证旧密码？？？
//@PostMapping("/updatePassword")
// public ResultData updatePassword(){
//
//}


}
