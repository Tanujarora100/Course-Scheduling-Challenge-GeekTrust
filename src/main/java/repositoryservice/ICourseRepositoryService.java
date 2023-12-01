package repositoryservice;

import dto.CourseDto;
import entity.Course;

import java.util.List;


public interface ICourseRepositoryService extends CRUDRepositoryService<CourseDto, String> {
    List<CourseDto> findAllotedCourses();
    Course mapToCourse(CourseDto course);

}
