package com.geektrust.backend.config;

import command.*;
import repository.ICourseRepository;
import repository.IEmployeeRepository;
import repository.IRegistrationRepository;
import repository.impl.CourseRepositoryImpl;
import repository.impl.EmployeeRepositoryImpl;
import repository.impl.RegistrationRepositoryImpl;
import repositoryservice.ICourseRepositoryService;
import repositoryservice.IEmployeeRepositoryService;
import repositoryservice.IRegistrationRepositoryService;
import repositoryservice.impl.CourseRepositoryServiceImpl;
import repositoryservice.impl.EmployeeRepositoryServiceImpl;
import repositoryservice.impl.RegistrationRepositoryServiceImpl;
import service.*;
import service.impl.CourseServiceImpl;
import service.impl.EmployeeServiceImpl;
import service.impl.RegistrationServiceImpl;

public class ObjectPool {
    private final ICourseRepository courseRepository = new CourseRepositoryImpl();
    private final IEmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final IRegistrationRepository registrationRepository = new RegistrationRepositoryImpl();

    private final ICourseRepositoryService courseRepositoryService = new CourseRepositoryServiceImpl(courseRepository);
    private final IEmployeeRepositoryService employeeRepositoryService = new EmployeeRepositoryServiceImpl(employeeRepository);
    private final IRegistrationRepositoryService registrationRepositoryService = new RegistrationRepositoryServiceImpl(registrationRepository, courseRepositoryService, employeeRepositoryService);

    private final ICourseService courseService = new CourseServiceImpl(courseRepositoryService, registrationRepositoryService);
    private final IEmployeeService employeeService = new EmployeeServiceImpl(employeeRepositoryService);
    private final IRegistrationService registrationService = new RegistrationServiceImpl(registrationRepositoryService, employeeRepositoryService, courseRepositoryService);


    private final AddCourseCommand addCourseOfferingCommand = new AddCourseCommand(courseService);
    private final RegisterCourseCommand registerCommand = new RegisterCourseCommand(registrationService);
    private final AllotCourseCommand allotCommand = new AllotCourseCommand(courseService);
    private final CancelCourseCommand cancelCommand = new CancelCourseCommand(courseService);

    private final CommandInvoker commandInvoker = new CommandInvoker();



    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("ADD-COURSE-OFFERING", addCourseOfferingCommand);
        commandInvoker.register("REGISTER", registerCommand);
        commandInvoker.register("ALLOT", allotCommand);
        commandInvoker.register("CANCEL", cancelCommand);


        return commandInvoker;
    }
}
