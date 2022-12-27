package mk.ukim.finki.wp.lab_1.web.servlet;

import mk.ukim.finki.wp.lab_1.model.Course;


import mk.ukim.finki.wp.lab_1.model.Grade;
import mk.ukim.finki.wp.lab_1.service.CourseService;
import mk.ukim.finki.wp.lab_1.service.StudentService;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


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

        WebContext context = new WebContext(req, resp, req.getServletContext());
        String courseID = req.getSession().getAttribute("courseId").toString();
        req.getSession().setAttribute("courseId", courseID);

        List<Grade> grades = courseService.findGradesById(Long.valueOf(courseID));
        context.setVariable("course", courseService.findById(Long.valueOf(courseID)));
        context.setVariable("grades", grades);
        context.setVariable("bodyContent","studentsInCourse.html");
        this.springTemplateEngine.process("master-template.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String student = req.getParameter("username");
        WebContext context=new WebContext(req, resp, req.getServletContext());
        String courseID = req.getSession().getAttribute("courseId").toString();
        req.getSession().setAttribute("courseId", courseID);
        if (student != null) {
            LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
            Character grade = req.getParameter("grade").charAt(0);
            if (!courseService.addStudentInCourse(student, Long.valueOf(courseID), dateTime, grade)) {
                req.getSession().setAttribute("hasError", true);
                req.getSession().setAttribute("error", "Student already exists in the course");
                context.setVariable("hasError",true);
                context.setVariable("error","Student already exists in the course");
                resp.sendRedirect("/addStudent");
            } else {
                resp.sendRedirect("/StudentEnrollmentSummary");
            }
        }
    }
}
