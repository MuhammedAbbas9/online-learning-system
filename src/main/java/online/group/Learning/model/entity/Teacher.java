package online.group.Learning.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "teachers")
public class Teacher extends User {

    @OneToMany(mappedBy = "teacher")
    private List<CourseOffering> courseOfferings;

}
