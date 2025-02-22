package online.group.Learning.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.List;

public record CourseOfferingDTO(Long id, CourseDTO courseDTO, TeacherDTO teacherDTO,
                                List<StudentDTO> studentDTOList, String term, LocalDate startDate,
                                LocalDate endDate) {


}
