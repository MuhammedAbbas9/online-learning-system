package online.group.Learning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import jakarta.persistence.EntityNotFoundException;
import online.group.Learning.controller.dto.CourseOfferingDTO;
import online.group.Learning.model.entity.Course;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.repository.CourseOfferingRepository;
import online.group.Learning.service.mappers.CourseOfferingMapper;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class CourseOfferingServiceTest {

    @Mock
    private CourseOfferingRepository courseOfferingRepository;

    @InjectMocks
    private CourseOfferingService courseOfferingService;

    private CourseOffering courseOffering;
    private CourseOfferingDTO courseOfferingDTO;

    @BeforeEach
    void setUp() {
        courseOffering = new CourseOffering();
        courseOffering.setId(1L);
        Course course = new Course();
        course.setName("Course 101");
        courseOffering.setCourse(course);
        courseOfferingDTO = CourseOfferingMapper.toCourseOfferingDTO(courseOffering);
    }

    @Test
    void shouldGetAllCourseOfferings() {
        when(courseOfferingRepository.findAll()).thenReturn(Collections.singletonList(courseOffering));

        List<CourseOfferingDTO> courseOfferings = courseOfferingService.getAllCourseOfferings();

        assertNotNull(courseOfferings);
        assertEquals(1, courseOfferings.size());
        assertEquals("Course 101", courseOfferings.get(0).getName());
    }

    @Test
    void shouldGetCourseOfferingById() {
        when(courseOfferingRepository.findById(1L)).thenReturn(Optional.of(courseOffering));

        CourseOfferingDTO foundCourseOffering = courseOfferingService.getCourseOfferingById(1L);

        assertNotNull(foundCourseOffering);
        assertEquals("Course 101", foundCourseOffering.getName());
    }

    @Test
    void shouldThrowExceptionWhenCourseOfferingNotFound() {
        when(courseOfferingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            courseOfferingService.getCourseOfferingById(1L);
        });
    }

    @Test
    void shouldCreateCourseOffering() {
        when(courseOfferingRepository.save(any(CourseOffering.class))).thenReturn(courseOffering);

        CourseOfferingDTO createdCourseOffering = courseOfferingService.createCourseOffering(courseOfferingDTO);

        assertNotNull(createdCourseOffering);
        assertEquals("Course 101", createdCourseOffering.getName());
    }

    @Test
    void shouldUpdateCourseOffering() {
        when(courseOfferingRepository.save(any(CourseOffering.class))).thenReturn(courseOffering);

        CourseOfferingDTO updatedCourseOffering = courseOfferingService.updateCourseOffering(courseOfferingDTO);

        assertNotNull(updatedCourseOffering);
        assertEquals("Course 101", updatedCourseOffering.getName());
    }

    @Test
    void shouldDeleteCourseOffering() {
        courseOfferingService.deleteCourseOffering(1L);

        verify(courseOfferingRepository).deleteById(1L);
    }
}