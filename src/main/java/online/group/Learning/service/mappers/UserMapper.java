package online.group.Learning.service.mappers;

import online.group.Learning.controller.dto.UserDTO;
import online.group.Learning.model.entity.User;

/**
 * @author Muhammad
 * @date 2/21/2025
 */
public class UserMapper {

    public static UserDTO toDTO(User user) {

        return user != null ? new UserDTO(user.getId(), user.getFullName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getAddress(),
                user.getPhoneNumber()) : null;
    }

}


