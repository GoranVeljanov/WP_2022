package mk.ukim.finki.wp.lab_1.repository.jpa;

import mk.ukim.finki.wp.lab_1.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAll();
    Optional<Course> findByName(String name);
    List<Course> findAllByCourseId(Long id);
    void deleteByName(String name);

}
