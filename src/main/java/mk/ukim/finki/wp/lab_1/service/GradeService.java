package mk.ukim.finki.wp.lab_1.service;


import mk.ukim.finki.wp.lab_1.model.Grade;
import mk.ukim.finki.wp.lab_1.model.Student;


import java.util.List;

public interface GradeService {
    Character findGradeByUsernameAndCourseId(String name, Long id);
    List<Grade> findGradesByCourseGreaterThan();
    List<Student> findStudentsByCourseIdAndUsername(Long id, String username);

}
