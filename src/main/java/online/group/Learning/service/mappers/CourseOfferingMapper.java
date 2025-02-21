package online.group.Learning.service.mappers;

import online.group.Learning.model.dto.CourseOfferingDTO;
import online.group.Learning.model.entity.Course;
import online.group.Learning.model.entity.CourseOffering;
import online.group.Learning.model.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseOfferingMapper {

    public static CourseOffering toCourseOffering(CourseOfferingDTO courseOfferingDTO) {
        Course course = CourseMapper.toCourse(courseOfferingDTO.courseDTO());
        Teacher teacher = TeacherMapper.toTeacher(courseOfferingDTO.teacherDTO());
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setCourse(course);
        courseOffering.setTeacher(teacher);

        if(courseOfferingDTO.studentDTOList() != null)
        courseOffering.setStudents(courseOfferingDTO.studentDTOList().stream().map(StudentMapper::toStudent).toList());
        else courseOffering.setStudents(null);

        courseOffering.setSemester(courseOfferingDTO.semester());
        courseOffering.setStartDate(courseOfferingDTO.startDate());
        courseOffering.setEndDate(courseOfferingDTO.endDate());

        return courseOffering;
    }

    public static CourseOfferingDTO toCourseOfferingDTO(CourseOffering courseOffering) {
        return new CourseOfferingDTO(CourseMapper.toCourseDTO(courseOffering.getCourse()),
                TeacherMapper.toTeacherDTO(courseOffering.getTeacher()),
                courseOffering.getStudents().stream().map(StudentMapper::toStudentDTO).toList(),
                courseOffering.getSemester(), courseOffering.getStartDate(), courseOffering.getEndDate());
    }
}
