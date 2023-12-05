package com.example.geektrust.service;

import com.example.geektrust.constants.CourseStatus;
import com.example.geektrust.constants.RegistrationStatus;
import com.example.geektrust.dto.AllotCourseDto;
import com.example.geektrust.entity.Course;
import com.example.geektrust.entity.Registration;
import com.example.geektrust.exception.CourseNotFoundException;
import com.example.geektrust.repository.ICourseRepository;
import com.example.geektrust.repository.IRegistrationRepository;
import com.example.geektrust.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllotCourseImpl implements IAllotCourseService {
    private final ICourseRepository courseRepository;
    private final IRegistrationRepository registrationRepository;

    public AllotCourseImpl(ICourseRepository courseRepository, IRegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<AllotCourseDto> allotCourse(String courseID) {
        Optional<Course> optionalCourse = courseRepository.findById(courseID);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            String registrationStatus = null;
            List<Registration> registrationsForCourse = registrationRepository.findAll().stream()
                    .filter(registration -> registration.getCourseID().equals(course.getCourseID()))
                    .collect(Collectors.toList());
            if (course.getCourseStatus() != CourseStatus.CANCELED &&
                    course.getEmployeeList().size() < course.getMinimumEmployees()) {
                course.setCourseStatus(CourseStatus.CANCELED);
                registrationStatus = RegistrationStatus.CANCELED.toString();
            } else if (course.getCourseStatus() != CourseStatus.CANCELED &&
                    course.getEmployeeList().size() >= course.getMinimumEmployees()) {
                course.setCourseStatus(CourseStatus.ALLOTED);
                registrationStatus = RegistrationStatus.CONFIRMED.toString();
            }
            return getDTOList(registrationsForCourse, registrationStatus, course);
        } else {
            throw new CourseNotFoundException(Utils.COURSE_NOT_FOUND_ERROR);
        }
    }

    private List<AllotCourseDto> getDTOList(List<Registration> registrationsForCourse, String registrationStatus, Course course) {
        List<AllotCourseDto> allotCourseDtos = new ArrayList<>();
        for (Registration registration : registrationsForCourse) {
            assert registrationStatus != null;
            if (registrationStatus.equals(RegistrationStatus.ACCEPTED.toString())) {
                registration.setRegistrationStatus(RegistrationStatus.ACCEPTED);
            } else {
                registration.setRegistrationStatus(RegistrationStatus.CANCELED);
            }
            AllotCourseDto allotCourseDto = generateDto(registration, course);
            allotCourseDtos.add(allotCourseDto);
        }
        return allotCourseDtos;
    }

    private AllotCourseDto generateDto(Registration registration, Course course) {

        String dtoString = registration.getRegistrationID() + " " +
                Utils.generateName(registration.getEmailID()) + " " +
                course.getCourseID() + " " +
                course.getInstructorName() + " " +
                course.getDate() + " " +
                registration.getRegistrationStatus().toString();

        return new AllotCourseDto(dtoString);
    }
}
