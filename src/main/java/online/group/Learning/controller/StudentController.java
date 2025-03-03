package online.group.Learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import online.group.Learning.model.dto.StudentDTO;
import online.group.Learning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * @author Muhammed Abbas
 */

/**
 * REST controller for managing student enrollments and unenrollments in course offerings.
 *
 * The `StudentController` class provides HTTP endpoints for students to enroll in
 * and unroll from course offerings. The controller delegates business logic
 * to the `StudentService` to handle the necessary processing.
 *
 * This controller exposes the following endpoints:
 *
 * - POST /api/students/enroll: Enrolls a student in a course offering.
 * - DELETE /api/students/unroll: Unenrolls a student from a course offering.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Enroll student in a course", description = "Enrolls a student in a course offering")
    @ApiResponse(responseCode = "201", description = "Student enrolled successfully")
    @PostMapping("enroll")
    public ResponseEntity<StudentDTO> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseOfferingId) {
        StudentDTO studentDTO = studentService.enrollStudentInCourseOffering(studentId, courseOfferingId);
        return ResponseEntity.ok(studentDTO);
    }

    @Operation(summary = "Deregister student from a course", description = "Deregister a student from a course offering")
    @ApiResponse(responseCode = "201", description = "Student deregistered successfully")
    @DeleteMapping("deregister")
    public ResponseEntity<StudentDTO> deregisterStudent(@RequestParam Long studentId, @RequestParam Long courseOfferingId) {
        StudentDTO studentDTO = studentService.deregisterStudentFromCourseOffering(studentId, courseOfferingId);
        return ResponseEntity.ok(studentDTO);
    }


}
