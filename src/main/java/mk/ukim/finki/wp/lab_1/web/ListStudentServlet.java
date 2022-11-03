package mk.ukim.finki.wp.lab_1.web;

import mk.ukim.finki.wp.lab_1.data.DataHolder;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "list-student-servlet", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {
    private final StudentRepository studentRepository;
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseRepository courseRepository;

    public ListStudentServlet(StudentRepository studentRepository, SpringTemplateEngine springTemplateEngine, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.springTemplateEngine = springTemplateEngine;
        this.courseRepository = courseRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseID = req.getSession().getAttribute("courseId").toString();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        List<Student> studentList = courseRepository.findAllStudentsByCourse(Long.valueOf(courseID));
        List<Student> studentsNotInCourse = studentRepository.findAllStudents();

        for (Student s : studentList) {
            studentsNotInCourse.removeIf(e -> e.getUsername().equals(s.getUsername()));
        }

        req.getSession().setAttribute("studentListInCourse", studentList);

        webContext.setVariable("students", courseRepository.findAllStudentsByCourse(Long.valueOf(courseID)));
        webContext.setVariable("allStudents", studentRepository.findAllStudents());
        webContext.setVariable("courseId", courseID);

        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
        DataHolder.students.addAll(studentList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseID = (String) req.getParameter("courseId");
        req.getSession().setAttribute("courseId", courseID);

        List<Student> studentList = courseRepository.findAllStudentsByCourse(Long.valueOf(courseID));
        List<Student> studentsNotInCourse = studentRepository.findAllStudents();

        for (Student s : studentList) {
            studentsNotInCourse.removeIf(e -> e.getUsername().equals(s.getUsername()));
        }

//        req.getSession().setAttribute("studentListInCourse", studentList);

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("students", courseRepository.findAllStudentsByCourse(Long.valueOf(courseID)));
        webContext.setVariable("allStudents", studentRepository.findAllStudents());
        webContext.setVariable("courseId", courseID);

        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
        DataHolder.students.addAll(studentList);
    }
}
