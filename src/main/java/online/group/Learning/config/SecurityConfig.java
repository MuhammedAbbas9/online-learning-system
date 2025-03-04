package online.group.Learning.config;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import online.group.Learning.model.enums.UserType;
import online.group.Learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * @author Muhammad
 * @date 3/2/2025
 */
@Configuration
//@EnableMethodSecurity
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/course-offering/**").hasRole(UserType.STUDENT.toString())
//                        .requestMatchers("/api/course/**").hasRole(UserType.TEACHER.toString())
//                        .requestMatchers("/api/teacher/**").hasRole(UserType.TEACHER.toString())
//                        .requestMatchers("/api/student/**").hasRole(UserType.STUDENT.toString())
//                        .requestMatchers("/api/user/**").permitAll()
//                        .anyRequest().authenticated()
//                )
////                .formLogin(Customizer.withDefaults());
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider());
////                .httpBasic(Customizer.withDefaults())
////                .build();
//        return httpSecurity.build();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(authenticationProvider()));
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
