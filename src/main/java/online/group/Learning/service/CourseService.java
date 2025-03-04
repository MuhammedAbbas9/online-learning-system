package online.group.Learning.service;

import online.group.Learning.controller.dto.CourseDTO;
import online.group.Learning.model.entity.Course;
import online.group.Learning.repository.CourseRepository;
import online.group.Learning.service.mappers.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.toCourse(courseDTO);
        return courseRepository.save(course);
    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.toCourse(courseDTO);
        return CourseMapper.toCourseDTO(courseRepository.save(course));
    }

    public void deleteCourse(Long courseId) {
         courseRepository.deleteById(courseId);
    }
}
