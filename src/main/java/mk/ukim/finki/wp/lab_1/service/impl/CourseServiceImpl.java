package mk.ukim.finki.wp.lab_1.service.impl;


import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Grade;
import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.model.Teacher;
import mk.ukim.finki.wp.lab_1.model.enumeration.Type;
import mk.ukim.finki.wp.lab_1.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab_1.model.exceptions.StudentAlreadyExistsInCourse;
import mk.ukim.finki.wp.lab_1.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.lab_1.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab_1.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab_1.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab_1.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab_1.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab_1.service.CourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> findStudentsByCourseId(Long courseId) {
        return this.courseRepository.findStudentsByCourseId(courseId);
    }

    @Override
    public List<Course> listAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Boolean addStudentInCourse(String username, Long courseId, LocalDateTime dateTime, Character grade) {
        Student student = this.courseRepository.findStudentInCourse(username, courseId);
        if (student == null) {
            student = this.studentRepository.findById(username).orElseThrow();
            Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
            Grade grade1 = new Grade(grade, student, course, dateTime);
            gradeRepository.save(grade1);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Course> findByName(String name) {
        return this.courseRepository.findByName(name);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Course> save(String name, String description, Teacher teacherId) {
        Teacher teacher = this.teacherRepository.findById(teacherId.getId()).orElseThrow(() -> new TeacherNotFoundException(teacherId.getId()));
        this.courseRepository.deleteByName(name);
        return Optional.of(this.courseRepository.save(new Course(name, description, teacher, new ArrayList<>(), Type.MANDATORY)));
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }

    @Override
    public List<Grade> findGradesById(Long id) {
        return this.courseRepository.findById(id).get().getGrades();
    }


}
