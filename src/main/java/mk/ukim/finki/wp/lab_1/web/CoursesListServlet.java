package mk.ukim.finki.wp.lab_1.web;

import mk.ukim.finki.wp.lab_1.repository.StudentRepository;
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
    private final CourseService courseService;
    private final StudentRepository studentRepository;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListServlet(CourseService courseService, StudentRepository studentRepository, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courses", this.courseService.listAll());
        this.springTemplateEngine.process("listCourses.html", context, resp.getWriter());
    }

}
