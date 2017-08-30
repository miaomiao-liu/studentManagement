package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootio.dao.GradeDao;
import springbootio.entity.persistence.StudentGrade;
import springbootio.service.GradeService;

/**
 * Created by miaomiao on 17-7-9.
 */
@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeDao gradeDao;

    @Override
    public StudentGrade queryStudentGrade(int studentNumber){
        return gradeDao.queryGrade(studentNumber);
    }

    @Override
    public int addStudentGrade(StudentGrade gradeDetail){
        return gradeDao.addGrade(gradeDetail);
    }

    @Override
    public int updateStudentGrade(StudentGrade gradeDetail){
        return gradeDao.updateGeade(gradeDetail);
    }
}
