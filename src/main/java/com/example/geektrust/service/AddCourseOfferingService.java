package com.example.geektrust.service;

import com.example.geektrust.entity.Course;
import com.example.geektrust.exception.CourseAlreadyExistsException;
import com.example.geektrust.repository.CourseRepository;
import com.example.geektrust.utils.Utils;

public class AddCourseOfferingService {
    private final CourseRepository courseRepository;

    /*
     Every input command has an output. The format is as given
    <COMMAND> <parameter-1>...<parameter-n> :	<OUTPUT>
     Add course offering
    COMMAND	PARAMETERS	OUTPUT
    ADD-COURSE-OFFERING	<course-name> <instructor> <date-in-ddmmyyyy> <minEmployees> <maxEmployees>	<course-offering-id>
     The format of course-offering-id is OFFERING-<COURSE-NAME>-<INSTRUCTOR>
     */
    public AddCourseOfferingService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course) {
        if (courseRepository.existById(course.getCourseID()))
            throw new CourseAlreadyExistsException(Utils.INPUT_DATA_ERROR);
        else {
            courseRepository.save(course);
            return course;
        }
    }

}
