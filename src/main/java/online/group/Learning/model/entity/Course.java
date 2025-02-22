package online.group.Learning.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String courseCode;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseOffering> courseOfferings;

    private String description;
    private String imageAddress;
    private String videoAddress;
}
