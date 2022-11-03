package mk.ukim.finki.wp.lab_1.data;

import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("goran", "gv", "Goran", "Veljanov"));
        students.add(new Student("petar", "pt", "Petar", "Trajkovski"));
        students.add(new Student("tamara", "ts", "Tamara", "Stojanova"));
        students.add(new Student("keti", "km", "Keti", "Markova"));
        students.add(new Student("maja", "mj", "Maja", "Jovanova"));

        courses.add(new Course(1L, "Web programming", "(Veb Programiranje)",
                List.of(
                        new Student[]{
                                new Student("goran", "gv", "Goran", "Veljanov"),
                                new Student("petar", "pt", "Petar", "Trajkovski"),
                        }
                )));
        courses.add(new Course(2L, "Operating System", "(Bazi na Podatoci)",
                List.of(
                        new Student[]{
                                new Student("tamara", "ts", "Tamara", "Stojanova"),
                                new Student("maja", "mj", "Maja", "Jovanova")
                        })
        ));
        courses.add(new Course(3L, "Electronic and Mobile", "(Kompjuterski Mrezi)",
                List.of(
                        new Student("maja", "mj", "Maja", "Jovanova")
                )));
        courses.add(new Course(4L, "Computer Network", "(Softversko Inzenjerstvo)", List.of(
                new Student("keti", "km", "Keti", "Markova"),
                new Student("goran", "gv", "Goran", "Veljanov")
        )));
    }
}
