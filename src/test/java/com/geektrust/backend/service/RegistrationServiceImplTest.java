package com.geektrust.backend.service;

import dto.EmployeeDto;
import dto.RegistrationDto;
import entity.Course;
import entity.Registration;
import exception.DataValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ICourseRepository;
import repository.IRegistrationRepository;
import service.IEmployeeService;
import service.impl.RegistrationServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrationServiceImplTest {


}
