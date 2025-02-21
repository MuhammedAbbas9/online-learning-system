package online.group.Learning.repository;

import online.group.Learning.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
