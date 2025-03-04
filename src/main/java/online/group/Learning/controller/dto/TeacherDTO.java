package online.group.Learning.controller.dto;

import java.util.Set;

public record TeacherDTO(Long id, String fullName, String username, String password, String email,
                         Set<CourseOfferingDTO> courseOfferingDTOS) {
}
