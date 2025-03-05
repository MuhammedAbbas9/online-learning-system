package online.group.Learning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import online.group.Learning.model.entity.Course;
import online.group.Learning.model.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import online.group.Learning.controller.dto.CourseOfferingDTO;
import online.group.Learning.controller.dto.StudentDTO;
import online.group.Learning.controller.dto.UserDTO;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.model.entity.Student;
import online.group.Learning.model.enums.UserType;
import online.group.Learning.repository.StudentRepository;
import online.group.Learning.service.mappers.CourseOfferingMapper;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseOfferingService courseOfferingService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private StudentService studentService;

    private UserDTO userDTO;
    private Student student;
    private CourseOffering courseOffering;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(1L, "Jane Doe", "janedoe", "password123", "janedoe@example.com", "123 Street", "1234567890");
        student = new Student();
        student.setId(1L);
        student.setFullName("Jane Doe");
        student.setUsername("janedoe");
        student.setPassword("encodedPassword");
        student.setEmail("janedoe@example.com");
        student.setUserType(UserType.STUDENT);

        Course course = new Course();
        course.setId(1L);
        course.setCourseCode("101 Math");
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
        student.setCourseOfferings(Set.of(courseOffering));
    }

    @Test
    void shouldCreateStudentSuccessfully() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        UserDTO createdUser = studentService.createStudent(userDTO);

        // Assert
        assertNotNull(createdUser);
        assertEquals("Jane Doe", createdUser.fullName());
        assertEquals("janedoe", createdUser.username());
        assertEquals("janedoe@example.com", createdUser.email());

        Mockito.verify(studentRepository).save(any(Student.class));
    }

    @Test
    void shouldEnrollStudentInCourseOfferingSuccessfully() {
        // Arrange
        CourseOfferingDTO courseOfferingDTO = CourseOfferingMapper.toCourseOfferingDTO(courseOffering);
        when(studentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(student));
        when(courseOfferingService.getCourseOfferingById(anyLong())).thenReturn(courseOfferingDTO);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        StudentDTO enrolledStudent = studentService.enrollStudentInCourseOffering(1L, 1L);

        // Assert
        assertNotNull(enrolledStudent);
        assertEquals("Jane Doe", enrolledStudent.fullName());
        Mockito.verify(studentRepository).save(any(Student.class));
    }

    @Test
    void shouldDeregisterStudentFromCourseOfferingSuccessfully() {
        // Arrange
        CourseOfferingDTO courseOfferingDTO = CourseOfferingMapper.toCourseOfferingDTO(courseOffering);
        when(studentRepository.findById(anyLong())).thenReturn(java.util.Optional.of(student));
        when(courseOfferingService.getCourseOfferingById(anyLong())).thenReturn(courseOfferingDTO);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        StudentDTO deregisteredStudent = studentService.deregisterStudentFromCourseOffering(1L, 1L);

        // Assert
        assertNotNull(deregisteredStudent);
        assertEquals("Jane Doe", deregisteredStudent.fullName());
        Mockito.verify(studentRepository).save(any(Student.class));
    }
}