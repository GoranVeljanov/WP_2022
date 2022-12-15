//package mk.ukim.finki.wp.lab_1.repository.impl;
//
//
//import mk.ukim.finki.wp.lab_1.model.Course;
//import mk.ukim.finki.wp.lab_1.model.Student;
//import org.springframework.stereotype.Repository;
//
//
//import java.util.*;
//
//
//@Repository
//public class CourseRepositoryImpl {
//    List<Course> courseList = DataHolder.courses;
//
//    public List<Course> findAllCourses() {
//        return DataHolder.courses;
//    }
//
//    public Optional<Course> findById(Long courseId) {
//        return DataHolder.courses.stream().filter(e -> e.getCourseId().equals(courseId)).findFirst();
//    }
//
//    public List<Student> findAllStudentsByCourse(Long courseId) {
//        return DataHolder.courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().get().getStudents();
//    }
//    public Optional<Course> findByName(String name) {
//        return DataHolder.courses.stream().filter(c -> c.getName().equals(name)).findFirst();
//    }
//
//    public Course addStudentToCourse(Student student, Course course) {
//        List<Student> studentList = new ArrayList<>(course.getStudents());
//        studentList.add(student);
//        DataHolder.courses.stream().filter(c -> c.equals(course)).findFirst().get().setStudents(studentList);
//        return course;
//    }
//
////    public Optional<Course> save(String name, String description, Long id) {
////        DataHolder.courses.removeIf(c -> c.getName().equals(name) || c.getDescription().equals(description));
////        Course course = new Course(name, description, new ArrayList<>(), DataHolder.teachers.stream().filter(t->t.getId().equals(id)).findFirst().get());
////        DataHolder.courses.add(course);
////        return Optional.of(course);
////    }
//    public void deleteById(Long id) {
//        DataHolder.courses.removeIf(c->c.getCourseId().equals(id));
//    }
//}
