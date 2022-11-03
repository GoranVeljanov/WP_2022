package mk.ukim.finki.wp.lab_1.service.impl;

import mk.ukim.finki.wp.lab_1.data.DataHolder;
import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.repository.CourseRepository;
import mk.ukim.finki.wp.lab_1.repository.StudentRepository;
import mk.ukim.finki.wp.lab_1.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> listStudentsByCourse(Long courseId) {
        return DataHolder.courses.stream().filter(r->r.getCourseId().equals(courseId)).collect(Collectors.toList());
    }
    @Override
    public List<Course> listAll() {
        return this.courseRepository.findAllCourses();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if (username == null || username.isEmpty() || courseId == null || courseId.describeConstable().isEmpty()) {
            throw new IllegalArgumentException();
        }
        Student student = DataHolder.students.stream().filter(e->e.getUsername().equals(username)).findFirst().get();
        Course course = DataHolder.courses.stream().filter(e->e.getCourseId().equals(courseId)).findFirst().get();
        DataHolder.courses.stream().filter(e->e.getCourseId().equals(courseId));
        return this.courseRepository.addStudentToCourse(student,course);
    }

}
