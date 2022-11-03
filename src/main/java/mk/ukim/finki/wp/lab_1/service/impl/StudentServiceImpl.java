package mk.ukim.finki.wp.lab_1.service.impl;

import mk.ukim.finki.wp.lab_1.data.DataHolder;
import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.repository.StudentRepository;
import mk.ukim.finki.wp.lab_1.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return this.studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return DataHolder.students.stream().filter(e -> e.getName().equals(text) ||
                e.getSurname().equals(text)).collect(Collectors.toList());
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
