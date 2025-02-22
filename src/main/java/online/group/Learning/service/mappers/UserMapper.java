package online.group.Learning.service.mappers;

import online.group.Learning.model.dto.UserDTO;
import online.group.Learning.model.entity.User;

/**
 * @author Muhammad
 * @date 2/21/2025
 */
public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAddress(), user.getPhoneNumber());
    }

}


