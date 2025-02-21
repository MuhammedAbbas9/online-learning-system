package online.group.Learning.model.dto;

import java.util.Set;

public record StudentDTO(Long id, String name, String email, Set<CourseOfferingDTO> courseOfferingDTOS) {
}