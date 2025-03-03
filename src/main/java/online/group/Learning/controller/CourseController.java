package online.group.Learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import online.group.Learning.model.dto.CourseDTO;
import online.group.Learning.model.entity.Course;
import online.group.Learning.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author Muhammed Abbas
 */

/**
 * Controller for managing courses in the system.
 * This class provides endpoints for CRUD operations on courses.
 * <p>
 * The CourseController is responsible for handling HTTP requests
 * related to courses and delegating the corresponding processing to CourseService.
 * <p>
 * Endpoints:
 * - GET /api/courses: Retrieves a list of all available courses.
 * - POST /api/courses: Creates a new course based on the provided course data.
 * - PUT /api/courses: Updates details of an existing course.
 * - DELETE /api/courses/{courseId}: Deletes an existing course by its ID.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Get all courses", description = "Fetch a list of all available courses")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved courses")
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDTO courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }

    @PutMapping
    public ResponseEntity<CourseDTO> updateCourseDetails(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(courseDTO));
    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }
}
