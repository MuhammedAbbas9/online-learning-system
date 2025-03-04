package online.group.Learning.service;

import jakarta.persistence.EntityNotFoundException;
import online.group.Learning.controller.dto.CourseOfferingDTO;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.repository.CourseOfferingRepository;
import online.group.Learning.service.mappers.CourseOfferingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseOfferingService {

    private final CourseOfferingRepository courseOfferingRepository;

    public CourseOfferingService(CourseOfferingRepository courseOfferingRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
    }

    public List<CourseOfferingDTO> getAllCourseOfferings() {
        return courseOfferingRepository.findAll().stream().map(CourseOfferingMapper::toCourseOfferingDTO).toList();
    }

    public CourseOfferingDTO getCourseOfferingById(Long id) {
        CourseOffering courseOffering = courseOfferingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseOffering not found with id: " + id));

        return CourseOfferingMapper.toCourseOfferingDTO(courseOffering);
    }

    public CourseOfferingDTO createCourseOffering(CourseOfferingDTO courseOfferingDTO) {
        CourseOffering courseOffering = CourseOfferingMapper.toCourseOffering(courseOfferingDTO);
        return CourseOfferingMapper.toCourseOfferingDTO(courseOfferingRepository.save(courseOffering));
    }

    public CourseOfferingDTO updateCourseOffering(CourseOfferingDTO courseOfferingDTO) {
        CourseOffering courseOffering = CourseOfferingMapper.toCourseOffering(courseOfferingDTO);
        return CourseOfferingMapper.toCourseOfferingDTO(courseOfferingRepository.save(courseOffering));
    }

    public void deleteCourseOffering(Long id) {
        courseOfferingRepository.deleteById(id);
    }
}
