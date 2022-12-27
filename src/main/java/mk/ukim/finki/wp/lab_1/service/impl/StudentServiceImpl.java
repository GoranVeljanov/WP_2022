package mk.ukim.finki.wp.lab_1.service.impl;

import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab_1.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return this.studentRepository.findAll();
    }
    @Override
    public List<Student> findStudentByUsername(String username) {
        return this.studentRepository.findStudentByUsername(username);
    }

    @Override
    public List<Student> searchStudentByNameOrSurname(String text) {
        return this.studentRepository.findStudentByNameOrSurname(text,text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Student student = new Student(username, password, name, surname);
        studentRepository.save(student);
        return student;
    }
}
