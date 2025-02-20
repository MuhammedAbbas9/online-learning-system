package online.group.Learning.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @Setter @Getter
    private String name;

    @Setter @Getter
    private String description;

    @Setter @Getter
    private String imageAddress;

    @Setter @Getter
    private String videoAddress;
}
