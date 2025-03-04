package online.group.Learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import online.group.Learning.controller.dto.CourseOfferingDTO;
import online.group.Learning.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/course-offering")
public class CourseOfferingController {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @Operation(summary = "Get all Course Offerings", description = "Fetch a list of all the course offerings")
    @ApiResponse(responseCode = "200", description = "Course Offerings list is fetched successfully!")
    @GetMapping
//    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<CourseOfferingDTO>> listAllCourseOfferings() {
        return ResponseEntity.ok(courseOfferingService.getAllCourseOfferings());
    }

    @Operation(summary = "Get Course Offering By Id", description = "Fetch the details of a course offering by id")
    @ApiResponse(responseCode = "200", description = "Course Offering details are fetched successfully!")
    @GetMapping("{id}")
    public ResponseEntity<CourseOfferingDTO> getCourseOfferingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseOfferingService.getCourseOfferingById(id));
    }

    @Operation(summary = "Create a Course Offering", description = "Create a new course offering using JSON Object")
    @ApiResponse(responseCode = "201", description = "Course Offering is created successfully!")
    @PostMapping
    public ResponseEntity<CourseOfferingDTO> createCourseOffering(@RequestBody CourseOfferingDTO courseOfferingDTO) {
        return ResponseEntity.ok(courseOfferingService.createCourseOffering(courseOfferingDTO));
    }

    @Operation(summary = "Update a Course Offering", description = "Update the details of a course offering")
    @ApiResponse(responseCode = "201", description = "Course Offering details are updated successfully!")
    @PutMapping
    public ResponseEntity<CourseOfferingDTO> updateCourseOffering(@RequestBody CourseOfferingDTO courseOfferingDTO) {
        return ResponseEntity.ok(courseOfferingService.updateCourseOffering(courseOfferingDTO));
    }

    @Operation(summary = "Delete a Course Offering", description = "Delete a course offering from the database")
    @ApiResponse(responseCode = "201", description = "Course Offering is deleted successfully!")
    @DeleteMapping("{id}")
    public void deleteCourseOffering(@PathVariable("id") Long id) {
        courseOfferingService.deleteCourseOffering(id);
    }

}
