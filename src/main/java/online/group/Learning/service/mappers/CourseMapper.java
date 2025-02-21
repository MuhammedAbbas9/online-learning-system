package online.group.Learning.service.mappers;
import online.group.Learning.model.dto.CourseDTO;
import online.group.Learning.model.entity.Course;
import org.jetbrains.annotations.NotNull;

public class CourseMapper {

    public static Course toCourse(@NotNull CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.name());
        course.setCourseCode(courseDTO.courseCode());
        course.setCourseOfferings(courseDTO.courseOfferings());
        course.setDescription(courseDTO.description());
        course.setImageAddress(courseDTO.imageAddress());
        course.setVideoAddress(courseDTO.videoAddress());
        return course;
    }

    public static CourseDTO toCourseDTO(@NotNull Course course) {
        return new CourseDTO(course.getName(), course.getCourseCode(), course.getCourseOfferings(),
                course.getDescription(), course.getImageAddress(), course.getVideoAddress());
    }
}
