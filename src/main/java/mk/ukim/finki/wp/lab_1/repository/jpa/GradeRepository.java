package mk.ukim.finki.wp.lab_1.repository.jpa;

import mk.ukim.finki.wp.lab_1.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {

    Grade findByStudent_UsernameAndCourse_CourseId(String name, Long id);
    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
