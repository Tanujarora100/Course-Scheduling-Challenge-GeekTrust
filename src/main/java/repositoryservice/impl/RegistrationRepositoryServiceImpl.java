package repositoryservice.impl;

import dto.CourseDto;
import dto.EmployeeDto;
import dto.RegistrationDto;
import entity.Registration;
import exception.DataValidationException;
import repository.IRegistrationRepository;
import repositoryservice.ICourseRepositoryService;
import repositoryservice.IEmployeeRepositoryService;
import repositoryservice.IRegistrationRepositoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RegistrationRepositoryServiceImpl implements IRegistrationRepositoryService {
    private final IRegistrationRepository registrationRepository;
    private final ICourseRepositoryService courseRepositoryService;
    private final IEmployeeRepositoryService employeeRepositoryService;

    public RegistrationRepositoryServiceImpl(IRegistrationRepository registrationRepository, ICourseRepositoryService courseRepositoryService, IEmployeeRepositoryService employeeRepositoryService) {
        this.registrationRepository = registrationRepository;
        this.courseRepositoryService = courseRepositoryService;
        this.employeeRepositoryService = employeeRepositoryService;
    }

    @Override
    public RegistrationDto save(RegistrationDto object) {
        Registration registration = mapToRegistration(object);
        String registrationID = generateRegistrationNumber(object);
        registration.setRegID(registrationID);
        registrationRepository.save(registration);
        Optional<Registration> byId = registrationRepository.findById(registrationID);
        return mapToDTO(byId.get());
    }

    @Override
    public List<RegistrationDto> findAll() {
        return registrationRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<RegistrationDto> findById(String s) {
        return registrationRepository.existById(s) ? registrationRepository.findById(s).map(this::mapToDTO) : Optional.empty();
    }

    @Override
    public boolean existByID(String s) {
        return registrationRepository.existById(s);
    }

    @Override
    public void delete(RegistrationDto object) {
        if (registrationRepository.existById(object.getRegID())) {
            registrationRepository.delete(mapToRegistration(object));
        }
    }

    @Override
    public void deleteByID(String regId) {
        if (registrationRepository.existById(regId)) {
            registrationRepository.deleteByID(regId);
        }
    }

    @Override
    public boolean validateID(String s) {
        return false;
    }

    public String generateRegistrationNumber(RegistrationDto dto) throws DataValidationException {
        String courseID = dto.getCourseID();
        String emailAddress = dto.getEmailAddress();
        EmployeeDto empDTO = employeeRepositoryService.findByEmail(emailAddress);

        Optional<CourseDto> courseNameOptional = courseRepositoryService.findById(courseID);
        if (courseNameOptional.isPresent() && empDTO != null) {
            String empName = empDTO.getName();
            String courseName = courseNameOptional.get().getCourseName();
            return "REG-COURSE-" + empName + "-" + courseName;
        }
        throw new DataValidationException("Failed to generate registration number");
    }

    private RegistrationDto mapToDTO(Registration registration) {
        return RegistrationDto.builder()
                .emailAddress(registration.getEmailAddress())
                .courseID(registration.getCourseID())
                .regID(registration.getRegID())
                .isAccepted(registration.isAccepted())
                .build();
    }

    @Override
    public Registration mapToRegistration(RegistrationDto registrationDto) {
        return Registration.builder()
                .emailAddress(registrationDto.getEmailAddress())
                .courseID(registrationDto.getCourseID())
                .regID(registrationDto.getRegID())
                .isAccepted(registrationDto.isAccepted())
                .build();
    }
}

