package com.example.geektrust.service;

import com.example.geektrust.constants.CourseStatus;
import com.example.geektrust.dto.CourseCancelDTO;
import com.example.geektrust.entity.Course;
import com.example.geektrust.entity.Employee;
import com.example.geektrust.entity.Registration;
import com.example.geektrust.exception.RegistrationNotFound;
import com.example.geektrust.repository.ICourseRepository;
import com.example.geektrust.repository.IRegistrationRepository;
import com.example.geektrust.utils.Utils;

import java.util.List;
import java.util.Optional;

public class CourseCancelService implements ICancelCourseService {
    private static final String COURSE_CANCEL_FAILURE = "CANCEL_REJECTED";
    private static final String COURSE_CANCEL_ACCEPTED = "CANCEL_ACCEPTED";

    private final ICourseRepository courseRepository;
    private final IRegistrationRepository registrationRepository;

    public CourseCancelService(ICourseRepository courseRepository, IRegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    public CourseCancelDTO cancelCourse(String registrationID) {
        Optional<Registration> byId = registrationRepository.findById(registrationID);
        if (byId.isPresent()) {
            Registration registration = byId.get();
            String courseID = registration.getCourseID();
            Optional<Course> courseOptional = courseRepository.findById(courseID);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                if (course.getCourseStatus().equals(CourseStatus.ALLOTED)) {
                    return new CourseCancelDTO(registrationID, COURSE_CANCEL_FAILURE);
                }
                List<Employee> employeeList = course.getEmployeeList();
                employeeList.removeIf(employee -> employee.getEmailID().equals(registration.getEmailID()));
                courseRepository.save(course);
                registrationRepository.delete(registration);
                return new CourseCancelDTO(registrationID, COURSE_CANCEL_ACCEPTED);
            }
        }
        throw new RegistrationNotFound(Utils.INPUT_DATA_ERROR);

    }
}
