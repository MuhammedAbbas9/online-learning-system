package online.group.Learning.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Table(name = "course_offerings")
public class CourseOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @Setter
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @Setter
    private Teacher teacher;

    @ManyToMany(mappedBy = "courseOfferings")
    @Setter
    private List<Student> students;

    @Setter
    private String semester;

    @Setter
    private LocalDate endDate;

    @Setter
    private LocalDate startDate;

}
