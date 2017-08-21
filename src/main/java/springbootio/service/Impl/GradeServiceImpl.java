package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootio.dao.GradeDao;
import springbootio.entity.persistence.GradeDetail;
import springbootio.service.GradeService;

/**
 * Created by miaomiao on 17-7-9.
 */
@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeDao gradeDao;

    @Override
    public GradeDetail queryStudentGrade(int studentNumber){
        return gradeDao.queryGrade(studentNumber);
    }

    @Override
    public int addStudentGrade(GradeDetail gradeDetail){
        return gradeDao.addGrade(gradeDetail);
    }

    @Override
    public int updateStudentGrade(GradeDetail gradeDetail){
        return gradeDao.updateGeade(gradeDetail);
    }
}
