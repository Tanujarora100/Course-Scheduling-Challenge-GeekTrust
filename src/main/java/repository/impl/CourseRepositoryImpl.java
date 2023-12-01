package repository.impl;

import entity.Course;
import repository.ICourseRepository;

import java.util.*;
import java.util.stream.Collectors;

public class CourseRepositoryImpl implements ICourseRepository {

    private final Map<String, Course> courseMap;

    public CourseRepositoryImpl() {
        this.courseMap = new LinkedHashMap<>();
    }

    public CourseRepositoryImpl(Map<String, Course> courseMap) {
        this.courseMap = courseMap;

    }

    @Override
    public void save(Course course) {
        if (course != null)
            courseMap.put(course.getCourseID(), course);
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courseMap.values());
    }

    @Override
    public Optional<Course> findById(String courseId) {
        if (courseId != null)
            return Optional.of(courseMap.get(courseId));
        else {
            return Optional.empty();
        }

    }

    @Override
    public boolean existById(String courseId) {
        if (courseId != null) {
            Optional<Course> byId = findById(courseId);
            return byId.isPresent();
        }
        return false;
    }

    @Override
    public void delete(Course course) {
        if (course != null)
            courseMap.remove(course.getCourseID());
    }

    @Override
    public void deleteByID(String courseID) {
        courseMap.remove(courseID);
    }

    @Override
    public List<Course> findAllotedCourse() {
        return courseMap.values().stream()
                .filter(Course::isAlloted)
                .collect(Collectors.toList());
    }
}
