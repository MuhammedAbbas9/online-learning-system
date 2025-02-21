package online.group.Learning.model.dto;

import java.time.LocalDate;
import java.util.List;

public record CourseOfferingDTO(Long id, CourseDTO courseDTO, TeacherDTO teacherDTO, List<StudentDTO> studentDTOList,
                                String semester, LocalDate startDate, LocalDate endDate) {


}
