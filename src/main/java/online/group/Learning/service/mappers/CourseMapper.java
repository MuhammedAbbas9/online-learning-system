package online.group.Learning.service.mappers;

import online.group.Learning.controller.dto.CourseDTO;
import online.group.Learning.model.entity.Course;
import org.jetbrains.annotations.NotNull;

public class CourseMapper {

    public static Course toCourse(@NotNull CourseDTO courseDTO) {
        Course course = null;
        if (courseDTO != null) {
            course = new Course();
            course.setId(courseDTO.id());
            course.setName(courseDTO.name());
            course.setCourseCode(courseDTO.courseCode());
            course.setCourseOfferings(courseDTO.courseOfferings());
            course.setDescription(courseDTO.description());
            course.setImageAddress(courseDTO.imageAddress());
            course.setVideoAddress(courseDTO.videoAddress());
        }
        return course;
    }

    public static CourseDTO toCourseDTO(@NotNull Course course) {
        return course != null
                ? new CourseDTO(course.getId(), course.getName(), course.getCourseCode(), course.getCourseOfferings(),
                        course.getDescription(), course.getImageAddress(), course.getVideoAddress())
                : null;
    }
}
