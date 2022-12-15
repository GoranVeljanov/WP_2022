package mk.ukim.finki.wp.lab_1.service.impl;

import mk.ukim.finki.wp.lab_1.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab_1.service.GradeService;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Character findGradeByUsernameAndCourseId(String name, Long id) {
        return this.gradeRepository.findByStudent_UsernameAndCourse_CourseId(name,id).getGrade();
    }
}
