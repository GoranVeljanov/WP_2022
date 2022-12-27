package mk.ukim.finki.wp.lab_1.data;

import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Grade;
import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();
    public static List<Grade> grades = new ArrayList<>();
    public static Boolean sortCourses = true;

}
