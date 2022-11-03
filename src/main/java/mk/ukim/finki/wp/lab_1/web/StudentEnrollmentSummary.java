package mk.ukim.finki.wp.lab_1.web;

import mk.ukim.finki.wp.lab_1.repository.CourseRepository;

import mk.ukim.finki.wp.lab_1.service.impl.CourseServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "student-enrollment-summary", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseRepository courseRepository;
    private final CourseServiceImpl courseService;


    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseRepository courseRepository, CourseServiceImpl courseService) {
        this.springTemplateEngine = springTemplateEngine;

        this.courseRepository = courseRepository;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseID = req.getSession().getAttribute("courseId").toString();
        String username = req.getParameter("username");
        req.getSession().setAttribute("username", username);
        req.getSession().setAttribute("courseId", courseID);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("course", courseRepository.findById(Long.valueOf(courseID)));
        context.setVariable("studentsInCourse", courseService.addStudentInCourse(username, Long.valueOf(courseID)));
        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.getSession().setAttribute("username", username);
        String courseID = req.getSession().getAttribute("courseId").toString();
        req.getSession().setAttribute("courseId", courseID);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("course", courseRepository.findById(Long.valueOf(courseID)));
        context.setVariable("studentsInCourse", courseService.addStudentInCourse(username, Long.valueOf(courseID)).getStudents());
        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());

    }
}
