package online.group.Learning.controller;

import online.group.Learning.model.dto.CourseOfferingDTO;
import online.group.Learning.service.CourseOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
  @author Muhammed Abbas
 */

/**
 * Controller for managing course offerings in the system.
 * This class provides RESTful endpoints for CRUD operations on course offerings.
 * <p>
 * The CourseOfferingController serves as an entry point for handling HTTP requests
 * related to course offerings and delegates the necessary processing to CourseOfferingService.
 * <p>
 * Endpoints:
 * - GET /api/course-offerings: Retrieves a list of all available course offerings.
 * - GET /api/course-offerings/{id}: Retrieves details of a specific course offering by ID.
 * - POST /api/course-offerings: Creates a new course offering using the provided data.
 * - PUT /api/course-offerings: Updates details of an existing course offering.
 * - DELETE /api/course-offerings/{id}: Deletes an existing course offering by its ID.
 */
@RestController
@RequestMapping("/api/course-offerings")
public class CourseOfferingController {

    private final CourseOfferingService courseOfferingService;

    public CourseOfferingController(CourseOfferingService courseOfferingService) {
        this.courseOfferingService = courseOfferingService;
    }

    @GetMapping
    public ResponseEntity<List<CourseOfferingDTO>> listAllCourseOfferings() {
        return ResponseEntity.ok(courseOfferingService.getAllCourseOfferings());
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseOfferingDTO> getCourseOfferingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseOfferingService.getCourseOfferingById(id));
    }

    @PostMapping
    public ResponseEntity<CourseOfferingDTO> createCourseOffering(@RequestBody CourseOfferingDTO courseOfferingDTO) {
        return ResponseEntity.ok(courseOfferingService.createCourseOffering(courseOfferingDTO));
    }

    @PutMapping
    public ResponseEntity<CourseOfferingDTO> updateCourseOffering(@RequestBody CourseOfferingDTO courseOfferingDTO) {
        return ResponseEntity.ok(courseOfferingService.updateCourseOffering(courseOfferingDTO));
    }

    @DeleteMapping("{id}")
    public void deleteCourseOffering(@PathVariable("id") Long id) {
        courseOfferingService.deleteCourseOffering(id);
    }

}
