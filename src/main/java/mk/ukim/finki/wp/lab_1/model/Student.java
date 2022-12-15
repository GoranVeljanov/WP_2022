package mk.ukim.finki.wp.lab_1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    String username;
    String password;
    String name;
    String surname;
    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Student() {

    }

}
