package mk.ukim.finki.wp.lab_1.data;

import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
//    public static List<Student> students = new ArrayList<>();
//    public static List<Course> courses = new ArrayList<>();
//    public static List<Teacher> teachers = new ArrayList<>();
    public static boolean sortCourses = true;

//    @PostConstruct
//    public void init() {
//        students.add(new Student("goran", "gv", "Goran", "Veljanov"));
//        students.add(new Student("petar", "pt", "Petar", "Trajkovski"));
//        students.add(new Student("tamara", "ts", "Tamara", "Stojanova"));
//        students.add(new Student("keti", "km", "Keti", "Markova"));
//        students.add(new Student("maja", "mj", "Maja", "Jovanova"));
//
//        Teacher teacher1=new Teacher(10L,"Lazar","Mirkovski");
//        Teacher teacher2= new Teacher(11L,"Trajko","Trajkov");
//        Teacher teacher3 = new Teacher(12L,"Kire","Kirilovski");
//        Teacher teacher4 = new Teacher(13L,"Igor","Trpkovski");
//        Teacher teacher5 = new Teacher(14L,"Riste","Ristov");
//
//        teachers.add(teacher1);
//        teachers.add(teacher2);
//        teachers.add(teacher3);
//        teachers.add(teacher4);
//        teachers.add(teacher5);
//
//
//        courses.add(new Course("Web programming", "(Veb Programiranje)",
//                List.of(
//                        new Student[]{
//                                new Student("goran", "gv", "Goran", "Veljanov"),
//                                new Student("petar", "pt", "Petar", "Trajkovski"),
//                        }
//                ),
//                teacher1));
//        courses.add(new Course("Operating System", "(Operativni sistemi)",
//                List.of(
//                        new Student[]{
//                                new Student("tamara", "ts", "Tamara", "Stojanova"),
//                                new Student("maja", "mj", "Maja", "Jovanova")
//                        }),
//                teacher3));
//        courses.add(new Course("Electronic and Mobile", "(Elektronska i Mobilna)",
//                List.of(
//                        new Student("maja", "mj", "Maja", "Jovanova")
//                ),
//                teacher5));
//        courses.add(new Course("Software Engineering", "(Softversko Inzenjerstvo)",
//                List.of(
//                        new Student("keti", "km", "Keti", "Markova"),
//                        new Student("goran", "gv", "Goran", "Veljanov")
//                ),teacher2));
//
//    }
}
