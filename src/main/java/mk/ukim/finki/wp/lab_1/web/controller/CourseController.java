package mk.ukim.finki.wp.lab_1.web.controller;

import mk.ukim.finki.wp.lab_1.data.DataHolder;
import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Teacher;

import mk.ukim.finki.wp.lab_1.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab_1.service.CourseService;
import mk.ukim.finki.wp.lab_1.service.StudentService;
import mk.ukim.finki.wp.lab_1.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
   private final CourseService courseService;
   private final StudentService studentService;
   private final TeacherService teacherService;
   private final CourseRepository courseRepository;

    public CourseController(CourseService courseService, StudentService studentService, TeacherService teacherService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Course> courses = this.courseService.listAll();
        model.addAttribute("courses", courses);
        return "listCourses";
    }

    @GetMapping("/add-form")
    public String addCoursePage(Model model) {
        List<Course> courses = this.courseService.listAll();
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        return "add-course";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        if (this.courseService.findById(id).isPresent()) {
            Course course = this.courseService.findById(id).get();
            List<Teacher> teachers = this.teacherService.findAll();
            model.addAttribute("course", course);
            model.addAttribute("teachers", teachers);
            return "add-course";
        }
        return "redirect:/listCourses?error=CourseNotFound";
    }


    @PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Teacher id) {
            this.courseService.save(name,description,id);
            return "redirect:/listCourses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/listCourses";
    }

    @GetMapping("/sort-courses")
    public String SortCourses(Model model) {

        DataHolder.sortCourses = !DataHolder.sortCourses;
        return "redirect:/listCourses";
    }
}
