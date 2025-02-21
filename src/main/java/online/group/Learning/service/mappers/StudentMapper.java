package online.group.Learning.service.mappers;

import online.group.Learning.model.dto.StudentDTO;
import online.group.Learning.model.entity.Student;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toStudent(@NotNull StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.id());
        student.setName(studentDTO.name());
        student.setEmail(studentDTO.email());
        student.setCourseOfferings(studentDTO.courseOfferingDTOS().stream()
                .map(CourseOfferingMapper::toCourseOffering).collect(Collectors.toSet()));
        return student;
    }

    public static StudentDTO toStudentDTO(@NotNull Student student) {
        return new StudentDTO(student.getId(), student.getName(), student.getEmail(),
                student.getCourseOfferings().stream()
                        .map(CourseOfferingMapper::toCourseOfferingDTO).collect(Collectors.toSet()));
    }
}
