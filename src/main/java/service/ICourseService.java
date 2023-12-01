package service;

import dto.CourseDto;
import exception.DataValidationException;

import java.util.Optional;

public interface ICourseService {


    public String allotCourse(String courseId) throws DataValidationException;
    public CourseDto save(CourseDto course) throws DataValidationException;
    public String cancelCourse(String courseRegistrationID) throws DataValidationException;

    CourseDto findByID(String courseRegistrationID) throws DataValidationException;
    boolean validateCourse(CourseDto courseDto);

}
