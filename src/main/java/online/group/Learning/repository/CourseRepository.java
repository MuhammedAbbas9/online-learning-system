package online.group.Learning.repository;

import jakarta.transaction.Transactional;
import online.group.Learning.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long> {

}
