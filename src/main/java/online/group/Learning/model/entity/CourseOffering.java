package online.group.Learning.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "course_offerings")
public class CourseOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")

    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")

    private Teacher teacher;

    @ManyToMany(mappedBy = "courseOfferings")
    private List<Student> students;

    private String semester;

    private LocalDate endDate;

    private LocalDate startDate;
}
