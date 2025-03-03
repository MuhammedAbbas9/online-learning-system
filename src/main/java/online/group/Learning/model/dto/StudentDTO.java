package online.group.Learning.model.dto;

import java.util.Set;

public record StudentDTO(Long id, String fullName, String username, String password, String email,
                         Set<CourseOfferingDTO> courseOfferingDTOS) {
}