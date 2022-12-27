package mk.ukim.finki.wp.lab_1.web.controller;

import mk.ukim.finki.wp.lab_1.data.DataHolder;
import mk.ukim.finki.wp.lab_1.model.Course;
import mk.ukim.finki.wp.lab_1.model.Teacher;

import mk.ukim.finki.wp.lab_1.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab_1.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab_1.service.CourseService;
import mk.ukim.finki.wp.lab_1.service.GradeService;
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
   private final GradeService gradeService;

    public CourseController(CourseService courseService, StudentService studentService, TeacherService teacherService, CourseRepository courseRepository, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseRepository = courseRepository;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        if(DataHolder.sortCourses) {
            model.addAttribute("courses",this.courseRepository.findAllByCourseOrderByNameAsc());
        }if(DataHolder.sortCourses){
            model.addAttribute("courses",this.courseRepository.findAllByCourseOrderByNameDesc());
        }else{
            List<Course> courses = this.courseService.listAll();
            model.addAttribute("courses", courses);
        }
        model.addAttribute("bodyContent","listCourses");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String addCoursePage(Model model) {
        List<Course> courses = this.courseService.listAll();
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("bodyContent","add-course");
        model.addAttribute("teachers", teachers);
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        if (this.courseService.findById(id).isPresent()) {
            Course course = this.courseService.findById(id).get();
            List<Teacher> teachers = this.teacherService.findAll();
            model.addAttribute("course", course);
            model.addAttribute("bodyContent","add-course");
            model.addAttribute("teachers", teachers);
            return "master-template";
        }
        return "redirect:/courses?error=CourseNotFound";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Teacher id) {
            this.courseService.save(name,description,id);
            return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }
    
    @GetMapping("/sort-courses")
    public String SortCourses(Model model) {
        DataHolder.sortCourses = !DataHolder.sortCourses;
        return "redirect:/courses";
    }
    @GetMapping("/failed")
    public String failedStudents(String grade, Model model) {
        model.addAttribute("bodyContent","failedStudents");
        model.addAttribute("grades",this.gradeService.findGradesByCourseGreaterThan());
        return "master-template";
    }
}
