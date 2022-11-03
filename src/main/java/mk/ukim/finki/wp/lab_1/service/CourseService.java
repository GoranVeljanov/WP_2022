package mk.ukim.finki.wp.lab_1.service;

import mk.ukim.finki.wp.lab_1.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> listStudentsByCourse(Long courseId);
    List<Course> listAll();
    Course addStudentInCourse(String username, Long courseId);
}
