package online.group.Learning.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.group.Learning.model.enums.UserType;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String address;
    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType; // ENUM to differentiate Teacher vs Student
}

