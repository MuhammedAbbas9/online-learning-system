package online.group.Learning.service;

import online.group.Learning.model.dto.UserDTO;
import online.group.Learning.model.entity.Teacher;
import online.group.Learning.model.entity.User;
import online.group.Learning.repository.TeacherRepository;
import online.group.Learning.model.dto.TeacherDTO;
import online.group.Learning.service.mappers.TeacherMapper;
import online.group.Learning.service.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public UserDTO createTeacher(UserDTO userDTO) {
        Teacher teacher = TeacherMapper.toTeacher(new TeacherDTO(userDTO.id(), userDTO.name(), userDTO.email(),
                null));
        User user = teacherRepository.save(teacher);
        return UserMapper.toDTO(user);
    }
}
