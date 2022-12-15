package mk.ukim.finki.wp.lab_1.repository.jpa;

import mk.ukim.finki.wp.lab_1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAll();
    List<Student> findStudentByNameOrSurname(String name, String surname);
}
