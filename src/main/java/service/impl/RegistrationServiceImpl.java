package service.impl;

import dto.CourseDto;
import dto.EmployeeDto;
import dto.RegistrationDto;
import exception.DataValidationException;
import repositoryservice.ICourseRepositoryService;
import repositoryservice.IEmployeeRepositoryService;
import repositoryservice.IRegistrationRepositoryService;
import service.IRegistrationService;
import util.Messages;

import java.util.List;
import java.util.Optional;

public class RegistrationServiceImpl implements IRegistrationService {
    private final IRegistrationRepositoryService registrationRepositoryService;
    private final IEmployeeRepositoryService employeeRepositoryService;
    private final ICourseRepositoryService courseRepositoryService;

    public RegistrationServiceImpl(IRegistrationRepositoryService registrationRepositoryService, IEmployeeRepositoryService employeeRepositoryService, ICourseRepositoryService courseRepositoryService) {
        this.registrationRepositoryService = registrationRepositoryService;
        this.employeeRepositoryService = employeeRepositoryService;
        this.courseRepositoryService = courseRepositoryService;
    }

    @Override
    public String create(RegistrationDto registrationDto) throws DataValidationException {
        if (validateDTO(registrationDto) && checkCourseAvailability(registrationDto.getCourseID())) {
            boolean b = employeeRepositoryService.existByID(registrationDto.getEmailAddress());
            if (!b) {
                employeeRepositoryService.save(new EmployeeDto(registrationDto.getEmailAddress()));
            }
            RegistrationDto save = registrationRepositoryService.save(registrationDto);
            return save.getRegID();
        } else {
            return Messages.COURSE_FULL_ERROR_MESSAGE;
        }

    }

    private boolean validateDTO(RegistrationDto registrationDto) {
        if (registrationDto.getCourseID() == null || registrationDto.getCourseID().isEmpty()) return false;
        return registrationDto.getEmailAddress() != null && !registrationDto.getEmailAddress().isEmpty();

    }

    private boolean checkCourseAvailability(String courseId) {
        Optional<CourseDto> byId = courseRepositoryService.findById(courseId);
        List<RegistrationDto> all = registrationRepositoryService.findAll();
        long count = all.stream().filter(registrationDto -> registrationDto.getCourseID().equals(courseId)).count();
        return byId.filter(courseDto -> courseDto.getMaximumEmployee() <= count).isPresent();
    }


}
