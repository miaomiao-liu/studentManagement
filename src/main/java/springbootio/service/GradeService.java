package springbootio.service;

import springbootio.entity.persistence.GradeDetail;

/**
 * Created by miaomiao on 17-7-9.
 */
public interface GradeService {
    public GradeDetail queryStudentGrade(int studentNumber);

    public int addStudentGrade(GradeDetail gradeDetail);

    public int updateStudentGrade(GradeDetail gradeDetail);
}
