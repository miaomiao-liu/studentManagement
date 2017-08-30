package springbootio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootio.dao.GradeDao;
import springbootio.dao.TeacherDao;
import springbootio.entity.persistence.StudentGrade;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.view.ResultData;
import springbootio.service.TeacherService;
import springbootio.util.GetUsrName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by miaomiao on 17-7-8.
 */

@RestController
@RequestMapping("/Springbootio")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    GetUsrName getUsrName;

    //老师帐号 查询个人信息
    @GetMapping(value = "/queryTeacherNumber/{teacherNumber}")
    public ResultData queryTeacher(@PathVariable int teacherNumber) {
        try {
            TeacherDetail teacherDetail = teacherService.queryTeacherDetailById(teacherNumber);
            return new ResultData(true,teacherDetail);
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }

    @GetMapping(value = "/queryTeacher/{name}")
    public ResultData queryTeacher(@PathVariable String name) {
        try {
            TeacherDetail teacherDetail = teacherService.queryTeacherDetailByName(name);
            return new ResultData(true,teacherDetail);
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }


    //老师 更新填写个人信息
    @PostMapping(value = "/teacherDetail")
    public ResultData teacherDetail(@RequestBody TeacherDetail teacherDetail, HttpServletRequest request) {
        try {
            String username = getUsrName.AllProjects(request);
            String msg = teacherService.teacherDetail(teacherDetail,username);
            return new ResultData(true,teacherDetail,msg);
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }


    //老师上传学生成绩
    @PostMapping(value = "/addGrade")
    public ResultData teacherAddStudentGrade(@RequestBody StudentGrade studentGrade){
        try {
            teacherService.addStudentGrade(studentGrade);
            return new ResultData(true,"添加成绩成功！");
        }catch (Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //老师更新学生成绩
    @PostMapping(value = "/updateGrade")
    public ResultData teacherUpdateStudentGrade(@RequestBody StudentGrade studentGrade){
        try {
            teacherService.updateStudentGrade(studentGrade);
            return new ResultData(true,"更新成绩成功！");
        }catch (Exception e){
            return new ResultData(false,e.getMessage());
        }
    }


    //老师查询学生成绩
    @PostMapping(value = "/teacherQueryGrade")
    public ResultData teacherQueryStudentGrade(@RequestParam("studentNumber") int studentNumber){
        try{
            StudentGrade studentGrade = teacherService.queryStudentGrade(studentNumber);
            return new ResultData(true,studentGrade);
        }catch (Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

}
