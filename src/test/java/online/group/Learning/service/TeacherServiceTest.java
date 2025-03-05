package online.group.Learning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import online.group.Learning.controller.dto.UserDTO;
import online.group.Learning.model.entity.Teacher;
import online.group.Learning.model.entity.User;
import online.group.Learning.model.enums.UserType;
import online.group.Learning.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TeacherService teacherService;

    private UserDTO userDTO;
    private Teacher teacher;
    private User user;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(1L, "John Doe", "johndoe", "password123",
                "johndoe@example.com", "123 Street", "1234567890");
        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFullName("John Doe");
        teacher.setUsername("johndoe");
        teacher.setPassword("encodedPassword");
        teacher.setEmail("johndoe@example.com");
        teacher.setUserType(UserType.TEACHER);

        user = new Teacher();
        user.setId(1L);
        user.setFullName("John Doe");
        user.setUsername("johndoe");
        user.setPassword("encodedPassword");
        user.setEmail("johndoe@example.com");
        user.setUserType(UserType.TEACHER);
    }

    @Test
    void shouldCreateTeacherSuccessfully() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        // Act
        UserDTO createdUser = teacherService.createTeacher(userDTO);

        // Assert
        assertNotNull(createdUser);
        assertEquals("John Doe", createdUser.fullName());
        assertEquals("johndoe", createdUser.username());
        assertEquals("johndoe@example.com", createdUser.email());

        Mockito.verify(teacherRepository).save(any(Teacher.class));
    }
}
