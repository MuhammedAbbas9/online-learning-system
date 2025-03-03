package online.group.Learning.service;

import jakarta.persistence.EntityNotFoundException;
import online.group.Learning.model.dto.StudentDTO;
import online.group.Learning.model.dto.UserDTO;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.model.entity.Student;
import online.group.Learning.model.entity.User;
import online.group.Learning.repository.StudentRepository;
import online.group.Learning.service.mappers.CourseOfferingMapper;
import online.group.Learning.service.mappers.StudentMapper;
import online.group.Learning.service.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseOfferingService courseOfferingService;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, CourseOfferingService courseOfferingService, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.courseOfferingService = courseOfferingService;
        this.passwordEncoder = passwordEncoder;
    }

    public StudentDTO enrollStudentInCourseOffering(Long studentId, Long courseOfferingId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        CourseOffering courseOffering = CourseOfferingMapper.
                toCourseOffering(courseOfferingService.getCourseOfferingById(courseOfferingId));

        student.getCourseOfferings().add(courseOffering);
        student = studentRepository.save(student);
        return StudentMapper.toStudentDTO(student);
    }

    public StudentDTO deregisterStudentFromCourseOffering(Long studentId, Long courseOfferingId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        CourseOffering courseOffering = CourseOfferingMapper.
                toCourseOffering(courseOfferingService.getCourseOfferingById(courseOfferingId));
        student.getCourseOfferings().remove(courseOffering);
        student = studentRepository.save(student);
        return StudentMapper.toStudentDTO(student);
    }

    public UserDTO createStudent(UserDTO userDTO) {
        String passwordEncoded = passwordEncoder.encode(userDTO.password());
        Student student = StudentMapper.toStudent(new StudentDTO(userDTO.id(), userDTO.fullName(), userDTO.username(),
                passwordEncoded, userDTO.email(), null));
        User user = studentRepository.save(student);
        return UserMapper.toDTO(user);
    }
}
