package mk.ukim.finki.wp.lab_1.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.wp.lab_1.model.enumeration.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    @Column(length = 2500)
    private String description;
    @ManyToMany
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Grade> grades;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description, Teacher teacher, List<Grade> grades, Type type) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.grades = grades;
        this.type = type;
    }

    public Course() {

    }

}
