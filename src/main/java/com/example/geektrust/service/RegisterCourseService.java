package com.example.geektrust.service;

import com.example.geektrust.entity.Course;
import com.example.geektrust.entity.Employee;
import com.example.geektrust.entity.Registration;
import com.example.geektrust.exception.CourseFullException;
import com.example.geektrust.exception.CourseNotFoundException;
import com.example.geektrust.repository.CourseRepository;
import com.example.geektrust.repository.ICourseRepository;
import com.example.geektrust.repository.RegistrationRepository;
import com.example.geektrust.utils.Utils;

import java.util.Optional;

public class RegisterCourseService implements IRegisterService {
    private final RegistrationRepository registrationRepository;
    private final ICourseRepository courseRepository;

    public RegisterCourseService(RegistrationRepository registrationRepository, CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Registration registerCourse(String emailId, String courseOfferingId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseOfferingId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            if (course.getEmployeeList().size() >= course.getMaximumEmployees()) {
                throw new CourseFullException(Utils.COURSE_FULL_ERROR);
            }
            Registration registration = new Registration(course.getCourseID(),emailId,Utils.generateName(emailId));
            Employee employee = new Employee(emailId);
            course.addEmployeeToCourse(employee);
            registrationRepository.save(registration);
            courseRepository.save(course);
            return registration;
        } else {
            throw new CourseNotFoundException(Utils.COURSE_NOT_FOUND_ERROR);
        }
    }


}
