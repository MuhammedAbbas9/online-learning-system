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
import online.group.Learning.model.entity.Student;
import online.group.Learning.model.entity.Teacher;
import online.group.Learning.model.enums.UserType;
import online.group.Learning.repository.CourseOfferingRepository;
import online.group.Learning.service.mappers.CourseOfferingMapper;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
        Student student = new Student();
        student.setId(1L);
        student.setFullName("Jane Doe");
        student.setUsername("janedoe");
        student.setPassword("encodedPassword");
        student.setEmail("janedoe@example.com");
        student.setUserType(UserType.STUDENT);

        Course course = new Course();
        course.setId(1L);
        course.setCourseCode("Course 101");
        course.setDescription("Math 1");

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFullName("muhammed");
        teacher.setUsername("abbas");
        teacher.setUserType(UserType.TEACHER);
        teacher.setEmail("abbas@gmail.com");

        courseOffering = new CourseOffering();
        courseOffering.setId(1L);
        courseOffering.setTerm("Fall 2021");
        courseOffering.setCourse(course);
        courseOffering.setTeacher(teacher);
        courseOffering.setStudents(Collections.singletonList(student));
        courseOffering.setStartDate(LocalDate.parse("2021-09-01"));
        courseOffering.setEndDate(LocalDate.parse("2021-12-31"));
        courseOfferingDTO = CourseOfferingMapper.toCourseOfferingDTO(courseOffering);
    }

    @Test
    void shouldGetAllCourseOfferings() {
        when(courseOfferingRepository.findAll()).thenReturn(Collections.singletonList(courseOffering));

        List<CourseOfferingDTO> courseOfferings = courseOfferingService.getAllCourseOfferings();

        assertNotNull(courseOfferings);
        assertEquals(1, courseOfferings.size());
        assertEquals("Course 101", courseOfferings.get(0).courseDTO().courseCode());
    }

    @Test
    void shouldGetCourseOfferingById() {
        when(courseOfferingRepository.findById(1L)).thenReturn(Optional.of(courseOffering));

        CourseOfferingDTO foundCourseOffering = courseOfferingService.getCourseOfferingById(1L);

        assertNotNull(foundCourseOffering);
        assertEquals("Course 101", foundCourseOffering.courseDTO().courseCode());
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
        assertEquals("Course 101", createdCourseOffering.courseDTO().courseCode());
    }

    @Test
    void shouldUpdateCourseOffering() {
        when(courseOfferingRepository.save(any(CourseOffering.class))).thenReturn(courseOffering);

        CourseOfferingDTO updatedCourseOffering = courseOfferingService.updateCourseOffering(courseOfferingDTO);

        assertNotNull(updatedCourseOffering);
        assertEquals("Course 101", updatedCourseOffering.courseDTO().courseCode());
    }

    @Test
    void shouldDeleteCourseOffering() {
        courseOfferingService.deleteCourseOffering(1L);

        verify(courseOfferingRepository).deleteById(1L);
    }
}