package mk.ukim.finki.wp.lab_1.web.servlet;


import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Student;
import mk.ukim.finki.wp.lab_1.service.CourseService;
import mk.ukim.finki.wp.lab_1.service.GradeService;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "list-student-servlet", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;
    private final GradeService gradeService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService, GradeService gradeService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
        String username =  req.getParameter("username");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        Boolean error=(Boolean) req.getSession().getAttribute("hasError");
        if(error){
            webContext.setVariable("error","Student already exists in the course");
        }
        if (username == null) {
            String courseID = (String) req.getSession().getAttribute("courseId");
            webContext.setVariable("allStudents", studentService.listAll());
            webContext.setVariable("courseId", courseID);
            webContext.setVariable("bodyContent","listStudents.html");
            this.springTemplateEngine.process("master-template.html", webContext, resp.getWriter());
        } else {
            String courseID = (String) req.getSession().getAttribute("courseId");
            Character grade = req.getParameter("grade").charAt(0);
            req.getSession().setAttribute("grades", grade);
            webContext.setVariable("grades", gradeService.findGradeByUsernameAndCourseId(username, Long.valueOf(courseID)));
            webContext.setVariable("allStudents", studentService.listAll());
            webContext.setVariable("courseId", courseID);
            webContext.setVariable("bodyContent","listStudents.html");
            this.springTemplateEngine.process("master-template.html", webContext, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String student = (String) req.getParameter("username");

        if (student == null) {
            Long courseId = Long.valueOf(req.getParameter("courseId"));
            WebContext webContext = new WebContext(req, resp, this.getServletContext());
            req.getSession().setAttribute("courseId",courseId);

            webContext.setVariable("courseId", courseId);
            webContext.setVariable("allStudents",studentService.listAll());
            webContext.setVariable("bodyContent","listStudents");
            this.springTemplateEngine.process("master-template.html", webContext, resp.getWriter());
        }
    }
}
