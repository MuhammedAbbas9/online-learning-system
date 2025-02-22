package online.group.Learning.service.mappers;

import online.group.Learning.model.dto.CourseOfferingDTO;
import online.group.Learning.model.entity.Course;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.model.entity.Teacher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CourseOfferingMapper {

    public static CourseOffering toCourseOffering(@NotNull CourseOfferingDTO courseOfferingDTO) {
        Course course = CourseMapper.toCourse(courseOfferingDTO.courseDTO());
        Teacher teacher = TeacherMapper.toTeacher(courseOfferingDTO.teacherDTO());
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setId(courseOfferingDTO.id());
        courseOffering.setCourse(course);
        courseOffering.setTeacher(teacher);

        if (courseOfferingDTO.studentDTOList() != null)
            courseOffering.setStudents(courseOfferingDTO.studentDTOList().stream().map(StudentMapper::toStudent).toList());
        else courseOffering.setStudents(null);

        courseOffering.setTerm(courseOfferingDTO.term());
        courseOffering.setStartDate(courseOfferingDTO.startDate());
        courseOffering.setEndDate(courseOfferingDTO.endDate());

        return courseOffering;
    }

    public static CourseOfferingDTO toCourseOfferingDTO(CourseOffering courseOffering) {

        if(courseOffering.getStudents() == null)
            courseOffering.setStudents(new ArrayList<>());
        return new CourseOfferingDTO(courseOffering.getId(), CourseMapper.toCourseDTO(courseOffering.getCourse()),
                TeacherMapper.toTeacherDTO(courseOffering.getTeacher()), null,
                courseOffering.getTerm(), courseOffering.getStartDate(), courseOffering.getEndDate());
    }
}
