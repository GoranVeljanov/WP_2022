package mk.ukim.finki.wp.lab_1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StudentNotFoundException  extends RuntimeException{
    public StudentNotFoundException(String username) {
        super(String.format("Student with id %d was not found",username));
    }
}
