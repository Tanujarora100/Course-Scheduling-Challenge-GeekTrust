package repository;

import entity.Course;

import java.util.List;

public interface ICourseRepository extends CRUDRepository<Course,String> {
    List<Course> findAllotedCourse();
}
