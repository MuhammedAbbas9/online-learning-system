package online.group.Learning.controller.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Muhammad
 * @date 3/5/2025
 */
@Component
public class JwtUtils {
    private final String jwtSecret = "7bA1VZh1K8sT3gP9K6l9VzN2yqM2OqzQeFj6zD9ZhW9PqT5rD3mA7kPqM6sT2qL47bA1VZh1K8sT3gP9K6l9VzN2yqM2OqzQeFj6zD9ZhW9PqT5rD3mA7kPqM6sT2qL4"; // Store securely!
    private final long jwtExpirationMs = 86400000; // 24 hours

    @SuppressWarnings("deprecation")
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())) // Embed roles inside token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
