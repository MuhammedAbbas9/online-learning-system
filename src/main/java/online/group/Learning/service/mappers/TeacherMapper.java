package online.group.Learning.service.mappers;

import online.group.Learning.model.dto.TeacherDTO;
import online.group.Learning.model.entity.Teacher;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class TeacherMapper {

    public static Teacher toTeacher(@NotNull TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.id());
        teacher.setName(teacherDTO.name());
        teacher.setEmail(teacherDTO.email());
        teacher.setCourseOfferings(teacherDTO.courseOfferingDTOS().stream()
                .map(CourseOfferingMapper::toCourseOffering).collect(Collectors.toSet()));
        return teacher;
    }

    public static TeacherDTO toTeacherDTO(@NotNull Teacher teacher) {
        return new TeacherDTO(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.getCourseOfferings().stream()
                .map(CourseOfferingMapper::toCourseOfferingDTO).collect(Collectors.toSet()));
    }
}
