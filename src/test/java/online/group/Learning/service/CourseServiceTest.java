package online.group.Learning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import online.group.Learning.controller.dto.CourseDTO;
import online.group.Learning.model.entity.Course;
import online.group.Learning.repository.CourseRepository;
import online.group.Learning.service.mappers.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
/**
 * @author Muhammad
 * @date 3/4/2025
 */

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;
    private CourseDTO courseDTO;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setId(1L);
        course.setCourseCode("Course 101");

        courseDTO = CourseMapper.toCourseDTO(course);
    }

    @Test
    void shouldGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Collections.singletonList(course));

        List<Course> courses = courseService.getAllCourses();

        assertNotNull(courses);
        assertEquals(1, courses.size());
        assertEquals("Course 101", courses.getFirst().getCourseCode());
    }

    @Test
    void shouldCreateCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course createdCourse = courseService.createCourse(courseDTO);

        assertNotNull(createdCourse);
        assertEquals("Course 101", createdCourse.getCourseCode());
    }

    @Test
    void shouldUpdateCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseDTO updatedCourse = courseService.updateCourse(courseDTO);

        assertNotNull(updatedCourse);
        assertEquals("Course 101", updatedCourse.courseCode());
    }

    @Test
    void shouldDeleteCourse() {
        courseService.deleteCourse(1L);

        verify(courseRepository).deleteById(1L);
    }
}
