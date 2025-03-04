package online.group.Learning.service;

import online.group.Learning.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Muhammad
 * @date 3/2/2025
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(user ->
                        User.withUsername(user.getUsername())
                                .password(user.getPassword())
                                .roles(user.getUserType().toString())
                                .build())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
    }

}
