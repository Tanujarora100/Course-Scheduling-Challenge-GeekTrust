package service.impl;

import dto.CourseDto;
import dto.RegistrationDto;
import exception.DataValidationException;
import repositoryservice.ICourseRepositoryService;
import repositoryservice.IRegistrationRepositoryService;
import service.ICourseService;
import util.Messages;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseServiceImpl implements ICourseService {
    private final ICourseRepositoryService courseRepositoryService;
    private final IRegistrationRepositoryService registrationRepositoryService;

    public CourseServiceImpl(ICourseRepositoryService courseRepositoryService, IRegistrationRepositoryService registrationRepositoryService) {
        this.courseRepositoryService = courseRepositoryService;
        this.registrationRepositoryService = registrationRepositoryService;
    }

    @Override
    public String allotCourse(String courseId) throws DataValidationException {
        Optional<CourseDto> courseDtoOptional = courseRepositoryService.findById(courseId);

        if (!courseDtoOptional.isPresent()) {
            throw new DataValidationException(Messages.INPUT_DATA_ERROR_MESSAGE);
        }
        CourseDto courseDto = courseDtoOptional.get();
        List<RegistrationDto> regEmployees = getAllRegistrationsCourseWise(courseId);
        String status = "";
        if (validateAllocation(courseDto)) {
            courseDto.setAlloted(true);
            status = Messages.ALLOT_COURSE_MESSAGE;
        } else {
            courseDto.setCancelled(true);
            status = Messages.CANCEL_ACCEPTED_MESSAGE;
        }

        StringBuilder output = new StringBuilder();

        for (RegistrationDto e : regEmployees) {
            output.append(String.format("%s %s %s %s %s %s %s%n",
                    e.getRegID(),
                    e.getEmailAddress(),
                    courseDto.getCourseID(),
                    courseDto.getCourseName(),
                    courseDto.getInstructor(),
                    courseDto.getDate(),
                    status));
        }

        courseRepositoryService.save(courseDto);

        return output.toString();
    }

    public List<RegistrationDto> getAllRegistrationsCourseWise(String courseId) {
        List<RegistrationDto> all = registrationRepositoryService.findAll();
        return all.stream().filter(registrationDto -> registrationDto.getCourseID().equals(courseId)).collect(Collectors.toList());

    }

    private boolean validateAllocation(CourseDto course) {
        List<RegistrationDto> allRegistrationsCourseWise = getAllRegistrationsCourseWise(course.getCourseID());
        long count = allRegistrationsCourseWise.stream().filter(registration -> registration.getCourseID().equals(course.getCourseID())).count();
        return course.getMaximumEmployee() >= count && !course.isCancelled();
    }

    @Override
    public CourseDto save(CourseDto course) throws DataValidationException {
        if (validateCourse(course))
            courseRepositoryService.save(course);
        Optional<CourseDto> byId = courseRepositoryService.findById(course.getCourseID());
        return byId.orElse(null);
    }

    @Override
    public String cancelCourse(String courseRegistrationID) throws DataValidationException {
        Optional<RegistrationDto> registrationDto = registrationRepositoryService.findById(courseRegistrationID);
        if (!registrationDto.isPresent())
            throw new DataValidationException(Messages.INPUT_DATA_ERROR_MESSAGE);
        RegistrationDto registration = registrationDto.get();
        Optional<CourseDto> courseOptional = courseRepositoryService.findById(registration.getCourseID());
        if (!courseOptional.isPresent())
            throw new DataValidationException(Messages.INPUT_DATA_ERROR_MESSAGE);
        CourseDto course = courseOptional.get();
        StringBuilder sb = new StringBuilder();
        String status = "";
        if (course.isAlloted()) {
            status = Messages.CANCEL_REJECTED_MESSAGE;

        } else {
            status = Messages.CANCEL_ACCEPTED_MESSAGE;
            registrationRepositoryService.deleteByID(registration.getRegID());
        }
        sb.append(String.format("%s %s%n(because ALLOT course-offering is already run)",
                courseRegistrationID,
                status));
        return sb.toString();
    }

    @Override
    public CourseDto findByID(String courseRegistrationID) throws DataValidationException {
        Optional<CourseDto> byId = courseRepositoryService.findById(courseRegistrationID);
        return byId.orElse(null);
    }

    @Override
    public boolean validateCourse(CourseDto courseDto) {
        if (courseDto.getCourseName() == null || courseDto.getCourseName().isEmpty())
            return false;
        if (courseDto.getCourseID() != null)
            return false;
        return courseDto.getInstructor() != null && !courseDto.getInstructor().isEmpty();
    }
}
