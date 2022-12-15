//package mk.ukim.finki.wp.lab_1.repository.impl;
//
//import mk.ukim.finki.wp.lab_1.data.DataHolder;
//import mk.ukim.finki.wp.lab_1.model.Student;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class StudentRepositoryImpl {
//
//
//    public List<Student> findAllStudents() {
//        return DataHolder.students;
//    }
//
//    public List<Student> findAllByNameOrSurname(String text) {
//        return DataHolder.students.stream().filter(e -> e.getName().contains(text) ||
//                e.getSurname().contains(text)).collect(Collectors.toList());
//    }
//
//    public Student save(Student student) {
//        if (student == null || student.getName() == null || student.getUsername() == null || student.getPassword() == null || student.getSurname() == null) {
//            return null;
//        }
//        DataHolder.students.removeIf(r -> r.getUsername().equals(student.getUsername())
//                || r.getName().equals(student.getName())
//                || r.getPassword().equals(student.getPassword())
//                || r.getSurname().equals(student.getSurname()));
//        DataHolder.students.add(student);
//        return student;
//    }
//
//}
