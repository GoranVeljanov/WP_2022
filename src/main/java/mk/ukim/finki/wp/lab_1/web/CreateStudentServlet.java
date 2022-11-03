package mk.ukim.finki.wp.lab_1.web;

import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.repository.CourseRepository;
import mk.ukim.finki.wp.lab_1.repository.StudentRepository;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "create-student-servlet", urlPatterns = "/createStudent")
public class CreateStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        springTemplateEngine.process("/createStudent", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        String username = req.getParameter("username");

        Student student = new Student(username, password, name, surname);


        studentRepository.save(student);
        List<Student> studentList = studentRepository.findAllStudents();

        req.getSession().setAttribute("studentListInCourse", studentList);
        resp.sendRedirect("/addStudent");
    }
}
