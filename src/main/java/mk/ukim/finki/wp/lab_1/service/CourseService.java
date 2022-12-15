package mk.ukim.finki.wp.lab_1.service;

import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Grade;
import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.model.Teacher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> listStudentsByCourse(Long courseId);

    List<Course> listAll();

    Course addStudentInCourse(String username, Long courseId, LocalDateTime dateTime, Character grade);


    Optional<Course> findByName(String name);

    Optional<Course> findById(Long id);

    public Optional<Course> save(String name, String description, Teacher teacherId);

    void deleteById(Long id);
    List<Grade> findGradesById(Long id);
}
