package mk.ukim.finki.wp.lab_1.web.servlet;

import mk.ukim.finki.wp.lab_1.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab_1.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab_1.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Courses-list-servlet", urlPatterns = "/listCourses")
public class CoursesListServlet extends HttpServlet {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListServlet(CourseRepository courseRepository, StudentRepository studentRepository, SpringTemplateEngine springTemplateEngine) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courses", this.courseRepository.findAll());
        this.springTemplateEngine.process("listCourses.html", context, resp.getWriter());
    }

}
