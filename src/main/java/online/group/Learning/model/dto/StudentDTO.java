package online.group.Learning.model.dto;

import online.group.Learning.model.entity.CourseOffering;

import java.util.List;

public record StudentDTO(String name, String email, List<CourseOfferingDTO> courseOfferingDTOS) {
}