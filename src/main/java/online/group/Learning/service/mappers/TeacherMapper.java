package online.group.Learning.service.mappers;

import online.group.Learning.model.dto.TeacherDTO;
import online.group.Learning.model.entity.Teacher;
import org.jetbrains.annotations.NotNull;

public class TeacherMapper {

    public static Teacher toTeacher(@NotNull TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.name());
        teacher.setEmail(teacherDTO.email());

        return teacher;
    }

    public static TeacherDTO toTeacherDTO(@NotNull Teacher teacher) {
        return new TeacherDTO(teacher.getName(), teacher.getEmail(), teacher.getCourseOfferings().stream()
                .map(CourseOfferingMapper::toCourseOfferingDTO).toList());
    }
}
