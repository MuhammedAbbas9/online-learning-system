package online.group.Learning.service;

import jakarta.persistence.EntityNotFoundException;
import online.group.Learning.model.dto.StudentDTO;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.model.entity.Student;
import online.group.Learning.repository.StudentRepository;
import online.group.Learning.service.mappers.CourseOfferingMapper;
import online.group.Learning.service.mappers.StudentMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseOfferingService courseOfferingService;

    public StudentService(StudentRepository studentRepository, CourseOfferingService courseOfferingService) {
        this.studentRepository = studentRepository;
        this.courseOfferingService = courseOfferingService;
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

    public StudentDTO unrollStudentFromCourseOffering(Long studentId, Long courseOfferingId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        CourseOffering courseOffering = CourseOfferingMapper.
                toCourseOffering(courseOfferingService.getCourseOfferingById(courseOfferingId));
        student.getCourseOfferings().remove(courseOffering);
        student = studentRepository.save(student);
        return StudentMapper.toStudentDTO(student);
    }
}
