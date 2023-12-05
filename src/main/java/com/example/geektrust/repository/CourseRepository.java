package com.example.geektrust.repository;

import com.example.geektrust.entity.Course;

import java.util.*;

public class CourseRepository implements ICourseRepository {
    private final Map<String, Course> courseMap = new HashMap<>();

    @Override
    public Course save(Course object) {
        courseMap.put(object.getCourseID(), object);
        return object;
    }

    @Override
    public boolean existById(String id) {
        return courseMap.containsKey(id);
    }

    @Override
    public Optional<Course> findById(String courseID) {
        return Optional.of(courseMap.get(courseID));
    }

    @Override
    public void delete(Course object) {
        if (existById(object.getCourseID())) {
            courseMap.remove(object.getCourseID());
        }
    }

    @Override
    public void deleteById(String id) {
        if (existById(id)) {
            courseMap.remove(id);
        }
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courseMap.values());
    }
}
