package online.group.Learning.model.dto;

import java.util.List;

public record TeacherDTO(String name, String email, List<CourseOfferingDTO> courseOfferingDTOS) {
}
