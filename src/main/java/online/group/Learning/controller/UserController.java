package online.group.Learning.controller;

import online.group.Learning.model.dto.UserDTO;
import online.group.Learning.service.StudentService;
import online.group.Learning.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Muhammad
 * @date 2/21/2025
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    public UserController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> registerUser(@RequestParam boolean isTeacher, @RequestBody UserDTO userDTO) {
        if(isTeacher)
            return ResponseEntity.ok(teacherService.createTeacher(userDTO));
        else
            return ResponseEntity.ok(studentService.createStudent(userDTO));
    }
}
