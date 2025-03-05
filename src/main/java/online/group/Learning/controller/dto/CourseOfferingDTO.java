package online.group.Learning.controller.dto;

import java.time.LocalDate;
import java.util.List;

public record CourseOfferingDTO(Long id, CourseDTO courseDTO, TeacherDTO teacherDTO, String term, LocalDate startDate,
                                LocalDate endDate) {


}
