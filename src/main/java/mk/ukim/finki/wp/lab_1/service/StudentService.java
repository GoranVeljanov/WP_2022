package mk.ukim.finki.wp.lab_1.service;

import mk.ukim.finki.wp.lab_1.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchStudentByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);
    List<Student> findStudentByUsername(String username);
}
