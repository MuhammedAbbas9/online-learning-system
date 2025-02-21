package online.group.Learning.model.dto;

import java.util.Set;

public record TeacherDTO(Long id, String name, String email, Set<CourseOfferingDTO> courseOfferingDTOS) {
}
