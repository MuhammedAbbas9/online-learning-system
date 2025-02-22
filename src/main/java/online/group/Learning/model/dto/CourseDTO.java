package online.group.Learning.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import online.group.Learning.model.entity.CourseOffering;

import java.util.List;

public record CourseDTO(Long id, String name, String courseCode, @JsonIgnore List<CourseOffering> courseOfferings,
                        String description, String imageAddress, String videoAddress) {
}


