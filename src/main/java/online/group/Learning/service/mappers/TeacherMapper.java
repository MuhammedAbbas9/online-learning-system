package online.group.Learning.service.mappers;

import online.group.Learning.controller.dto.TeacherDTO;
import online.group.Learning.model.entity.Teacher;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class TeacherMapper {

    public static Teacher toTeacher(@NotNull TeacherDTO teacherDTO) {

        if (teacherDTO == null)
            return null;
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.id());
        teacher.setFullName(teacherDTO.fullName());
        teacher.setUsername(teacherDTO.username());
        teacher.setPassword(teacherDTO.password());
        teacher.setEmail(teacherDTO.email());
        if (teacherDTO.courseOfferingDTOS() != null)
            teacher.setCourseOfferings(teacherDTO.courseOfferingDTOS().stream()
                    .map(CourseOfferingMapper::toCourseOffering).collect(Collectors.toSet()));
        else
            teacher.setCourseOfferings(null);
        return teacher;
    }

    public static TeacherDTO toTeacherDTO(@NotNull Teacher teacher) {
        return teacher != null
                ? new TeacherDTO(teacher.getId(), teacher.getFullName(), teacher.getUsername(), teacher.getPassword(),
                        teacher.getEmail(), null)
                : null;
    }
}
