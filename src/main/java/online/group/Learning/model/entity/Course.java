package online.group.Learning.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @Setter @Getter
    private String name;

    @Column(nullable = false)
    @Setter @Getter
    private String courseCode;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @Setter @Getter
    private List<CourseOffering> courseOfferings;

    @Setter @Getter
    private String description;

    @Setter @Getter
    private String imageAddress;

    @Setter @Getter
    private String videoAddress;
}
