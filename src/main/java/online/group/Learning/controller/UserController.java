package online.group.Learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import online.group.Learning.controller.dto.UserDTO;
import online.group.Learning.service.StudentService;
import online.group.Learning.service.TeacherService;
import online.group.Learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Muhammad
 * @date 2/21/2025
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Sign in", description = "Sign in as a user")
    @ApiResponse(responseCode = "201", description = "User is logged in successfully!")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login Successful!");
    }

    @Operation(summary = "Register a user", description = "Create a new user in the system")
    @ApiResponse(responseCode = "201", description = "User is registered successfully!")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestParam boolean isTeacher, @RequestBody UserDTO userDTO) {
        if (isTeacher)
            return ResponseEntity.ok(teacherService.createTeacher(userDTO));
        else
            return ResponseEntity.ok(studentService.createStudent(userDTO));
    }
}
