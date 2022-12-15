package mk.ukim.finki.wp.lab_1.service;

public interface GradeService {
    Character findGradeByUsernameAndCourseId(String name, Long id);
}
