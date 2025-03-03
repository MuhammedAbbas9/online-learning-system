package online.group.Learning.model.dto;

import java.util.Set;

public record TeacherDTO(Long id, String fullName, String username, String password, String email,
                         Set<CourseOfferingDTO> courseOfferingDTOS) {
}
