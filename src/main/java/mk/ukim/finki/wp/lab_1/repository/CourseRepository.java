package mk.ukim.finki.wp.lab_1.repository;
import mk.ukim.finki.wp.lab_1.data.DataHolder;
import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class CourseRepository {
    List<Course> courseList = DataHolder.courses;
    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }
    public Course findById(Long courseId){
        return DataHolder.courses.stream().filter(e->e.getCourseId().equals(courseId)).findFirst().get();
    }
    public List<Student> findAllStudentsByCourse(Long courseId) {
        return DataHolder.courses.stream().filter(c->c.getCourseId().equals(courseId)).findFirst().get().getStudents();
    }

    public Course addStudentToCourse(Student student, Course course) {
        List<Student> studentList= new ArrayList<>(course.getStudents());
        studentList.add(student);
        DataHolder.courses.stream().filter(c->c.equals(course)).findFirst().get().setStudents(studentList);

        return course;
    }
}
