package mk.ukim.finki.wp.lab_1.repository.jpa;

import mk.ukim.finki.wp.lab_1.model.Course;

import mk.ukim.finki.wp.lab_1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAll();
    Optional<Course> findByName(String name);
    List<Course> findAllByCourseId(Long id);
    @Query("select c.students from Course c where c.courseId = :id")
    List<Student> findStudentsByCourseId(Long id);
    void deleteByName(String name);
    @Query("select g.student from Grade g where g.course.courseId=:courseId and g.student.username=:username")
    Student findStudentInCourse(String username, Long courseId);
    @Query("select c from Course c order by c.name asc ")
    List<Course> findAllByCourseOrderByNameAsc();
    @Query("select c from Course c order by c.name desc")
    List<Course> findAllByCourseOrderByNameDesc();
}
