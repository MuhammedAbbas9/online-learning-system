package online.group.Learning.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;
import online.group.Learning.model.entity.Course;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.model.entity.Teacher;
import online.group.Learning.model.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Muhammad
 * @date 3/4/2025
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class CourseOfferingRepositoryTest {

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    private CourseOffering courseOffering;
    private Course course;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setName("Math1");
        course.setCourseCode("101 Math");
        course.setDescription("Math 1");

        teacher = new Teacher();
        teacher.setFullName("Muhammed");
        teacher.setUsername("attiaabbas");
        teacher.setUserType(UserType.TEACHER);
        teacher.setEmail("abbas@gmail.com");
        teacher.setPassword("newpassword");

        courseOffering = new CourseOffering();
        courseOffering.setTerm("Fall 2021");
        courseOffering.setCourse(course);
        courseOffering.setTeacher(teacher);
        courseOffering.setStartDate(LocalDate.parse("2021-09-01"));
        courseOffering.setEndDate(LocalDate.parse("2021-12-31"));
    }

    @Test
    void shouldSaveCourseOffering() {
        // Act
        CourseOffering savedCourseOffering = courseOfferingRepository.save(courseOffering);

        // Assert
        assertNotNull(savedCourseOffering);
        assertNotNull(savedCourseOffering.getId());
        assertEquals("Fall 2021", savedCourseOffering.getTerm());
    }

    @Test
    void shouldFindAllCourseOfferings() {
        // Arrange
        courseRepository.save(course);
        teacherRepository.save(teacher);
        courseOfferingRepository.save(courseOffering);

        // Act
        List<CourseOffering> courseOfferings = courseOfferingRepository.findAll();

        // Assert
        assertNotNull(courseOfferings);
        assertEquals(1, courseOfferings.size());
        assertEquals("Fall 2021", courseOfferings.getFirst().getTerm());
    }

    @Test
    void shouldFindCourseOfferingById() {
        // Arrange
        CourseOffering savedCourseOffering = courseOfferingRepository.save(courseOffering);

        // Act
        CourseOffering foundCourseOffering = courseOfferingRepository.findById(savedCourseOffering.getId()).orElse(null);

        // Assert
        assertNotNull(foundCourseOffering);
        assertEquals("Fall 2021", foundCourseOffering.getTerm());
    }

    @Test
    void shouldDeleteCourseOffering() {
        // Arrange
        CourseOffering savedCourseOffering = courseOfferingRepository.save(courseOffering);

        // Act
        courseOfferingRepository.deleteById(savedCourseOffering.getId());

        // Assert
        List<CourseOffering> courseOfferings = courseOfferingRepository.findAll();
        assertEquals(0, courseOfferings.size());
    }
}