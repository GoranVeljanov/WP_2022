package mk.ukim.finki.wp.lab_1.web.servlet;


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
        String courseID = req.getSession().getAttribute("courseId").toString();
        String name = (String) req.getParameter("username");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        Character grade = req.getParameter("grade").charAt(0);
        req.getSession().setAttribute("grade", grade);
        webContext.setVariable("grade", gradeService.findGradeByUsernameAndCourseId(name, Long.valueOf(courseID)));
        webContext.setVariable("allStudents", studentService.listAll());
        webContext.setVariable("courseId", courseID);
        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String student = (String) req.getParameter("username");
        if (student == null) {
//            String search = (String) req.getParameter("search");
            WebContext webContext = new WebContext(req, resp, this.getServletContext());
            String courseID = (String) req.getParameter("courseId");
            req.getSession().setAttribute("courseId", courseID);
//            List<Student> students = studentService.searchStudentByNameOrSurname(search);
            webContext.setVariable("courseId", courseID);
            webContext.setVariable("allStudents", studentService.listAll());
            this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
        }
    }
}
