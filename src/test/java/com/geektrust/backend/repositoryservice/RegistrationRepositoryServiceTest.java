package com.geektrust.backend.repositoryservice;

import dto.CourseDto;
import dto.EmployeeDto;
import dto.RegistrationDto;
import entity.Registration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.IRegistrationRepository;
import repositoryservice.ICourseRepositoryService;
import repositoryservice.IEmployeeRepositoryService;
import repositoryservice.impl.RegistrationRepositoryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RegistrationRepositoryServiceTest {

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ICourseRepositoryService courseRepositoryService;

    @Mock
    private IEmployeeRepositoryService employeeRepositoryService;

    @InjectMocks
    private RegistrationRepositoryServiceImpl registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        // Arrange
        RegistrationDto registrationDto = new RegistrationDto(null, "test@example.com", "course123", false);
        // Mock behavior
        when(courseRepositoryService.findById(anyString())).thenReturn(Optional.of(new CourseDto("Java", "course123", "Instructor", null, 1, 2, null, false, false)));
        when(employeeRepositoryService.findByEmail(anyString())).thenReturn(new EmployeeDto("test@example.com", "Test Employee"));

        // Act
        RegistrationDto savedRegistration = registrationService.save(registrationDto);

        // Assert
        assertNotNull(savedRegistration);
        assertEquals("REG-COURSE-Test Employee-Java", savedRegistration.getRegID());
        // Add more assertions based on your expectations
    }

    @Test
    void findAll() {
        // Arrange
        List<Registration> registrations = new ArrayList<>();
        when(registrationRepository.findAll()).thenReturn(registrations);

        // Act
        List<RegistrationDto> registrationDtos = registrationService.findAll();

        // Assert
        assertNotNull(registrationDtos);
        assertTrue(registrationDtos.isEmpty());
        // Add more assertions based on your expectations
    }

    // Write similar tests for other methods...

    // Example of testing the private method (if needed)
    @Test
    void generateRegistrationNumber() throws Exception {
        // Arrange
        RegistrationDto registrationDto = new RegistrationDto("reg123", "test@example.com", "course123", false);

        // Mock behavior
        when(courseRepositoryService.findById(anyString())).thenReturn(Optional.of(new CourseDto("Java", "course123", "Instructor", null, 1, 2, null, false, false)));
        when(employeeRepositoryService.findByEmail(anyString())).thenReturn(new EmployeeDto("test@example.com", "Test Employee"));

        // Act
        String registrationNumber = registrationService.generateRegistrationNumber(registrationDto);

        // Assert
        assertNotNull(registrationNumber);
        assertEquals("REG-COURSE-Test Employee-Java", registrationNumber);
        // Add more assertions based on your expectations
    }
}
