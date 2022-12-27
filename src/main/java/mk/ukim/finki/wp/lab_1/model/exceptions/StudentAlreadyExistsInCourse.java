package mk.ukim.finki.wp.lab_1.model.exceptions;

public class StudentAlreadyExistsInCourse extends RuntimeException {
    public StudentAlreadyExistsInCourse(String username) {
        super(String.format("Student with %d already enrolled in course", username));
    }
}
