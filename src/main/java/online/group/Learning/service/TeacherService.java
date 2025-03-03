package online.group.Learning.service;

import online.group.Learning.model.dto.UserDTO;
import online.group.Learning.model.entity.Teacher;
import online.group.Learning.model.entity.User;
import online.group.Learning.repository.TeacherRepository;
import online.group.Learning.model.dto.TeacherDTO;
import online.group.Learning.service.mappers.TeacherMapper;
import online.group.Learning.service.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createTeacher(UserDTO userDTO) {
        String passwordEncoded = passwordEncoder.encode(userDTO.password());
        Teacher teacher = TeacherMapper.toTeacher(new TeacherDTO(userDTO.id(), userDTO.fullName(), userDTO.username(),
                passwordEncoded, userDTO.email(), null));
        User user = teacherRepository.save(teacher);
        return UserMapper.toDTO(user);
    }


}
