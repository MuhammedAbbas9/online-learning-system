package online.group.Learning.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "students")
public class Student extends User {

    @ManyToMany
    @JoinTable(name = "enrollment",
    joinColumns = @JoinColumn(name = "courseOfferingId"),
    inverseJoinColumns = @JoinColumn(name = "studentId"))
    private Set<CourseOffering> courseOfferings;
}
