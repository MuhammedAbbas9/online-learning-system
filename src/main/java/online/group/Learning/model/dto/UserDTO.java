package online.group.Learning.model.dto;

import lombok.Data;

/**
 * @author Muhammad
 * @date 2/21/2025
 */
public record UserDTO(Long id, String fullName, String username, String password, String email, String address,
                      String phoneNumber) {
}

