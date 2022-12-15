package mk.ukim.finki.wp.lab_1.web.servlet;

import mk.ukim.finki.wp.lab_1.model.Course;


import mk.ukim.finki.wp.lab_1.service.CourseService;
import mk.ukim.finki.wp.lab_1.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet(name = "student-enrollment-summary", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.getSession().setAttribute("username", username);
        String courseID = req.getSession().getAttribute("courseId").toString();
        req.getSession().setAttribute("courseId", courseID);
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        req.getSession().setAttribute("dateTime", dateTime);
        Character grade = req.getParameter("grade").charAt(0);
        req.getSession().setAttribute("grades",grade);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("course", courseService.findById(Long.valueOf(courseID)));
        context.setVariable("grades", courseService.findGradesById(Long.valueOf(courseID)));
        context.setVariable("studentsInCourse", courseService.addStudentInCourse(username, Long.valueOf(courseID),dateTime,grade));
        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        req.getSession().setAttribute("username", username);
        String courseID = req.getSession().getAttribute("courseId").toString();
        req.getSession().setAttribute("courseId", courseID);
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        req.getSession().setAttribute("dateTime", dateTime);
        Character grade = req.getParameter("grade").charAt(0);
        req.getSession().setAttribute("grades",grade);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        Course course= this.courseService.findById(Long.valueOf(courseID)).get();
        context.setVariable("course", course);
        context.setVariable("grades", courseService.findGradesById(Long.valueOf(courseID)));
        context.setVariable("studentsInCourse", courseService.addStudentInCourse(username, Long.valueOf(courseID),dateTime,grade));
        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());

    }
}
