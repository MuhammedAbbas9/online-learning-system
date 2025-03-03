package online.group.Learning.service.mappers;

import online.group.Learning.model.dto.StudentDTO;
import online.group.Learning.model.entity.Student;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class StudentMapper {

    public static <T extends StudentDTO> Student toStudent(@NotNull T studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.id());
        student.setFullName(studentDTO.fullName());
        student.setUsername(studentDTO.username());
        student.setPassword(studentDTO.password());
        student.setEmail(studentDTO.email());
        if (studentDTO.courseOfferingDTOS() != null)
            student.setCourseOfferings(studentDTO.courseOfferingDTOS().stream()
                    .map(CourseOfferingMapper::toCourseOffering).collect(Collectors.toSet()));
        else student.setCourseOfferings(null);
        return student;
    }

    public static StudentDTO toStudentDTO(@NotNull Student student) {
        return new StudentDTO(student.getId(), student.getFullName(), student.getUsername(), student.getPassword(),
                student.getEmail(), null);
    }
}
