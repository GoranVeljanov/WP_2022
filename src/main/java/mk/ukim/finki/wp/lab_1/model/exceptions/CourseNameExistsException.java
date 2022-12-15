package mk.ukim.finki.wp.lab_1.model.exceptions;

public class CourseNameExistsException extends RuntimeException{
    public CourseNameExistsException(String name) {
        super("Course with name " + name + " already exists");
    }
}