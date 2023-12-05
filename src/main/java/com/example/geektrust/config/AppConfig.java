package com.example.geektrust.config;

import com.example.geektrust.command.*;
import com.example.geektrust.repository.CourseRepository;
import com.example.geektrust.repository.RegistrationRepository;
import com.example.geektrust.service.*;

public class AppConfig {


    private final RegistrationRepository registrationRepository = new RegistrationRepository();
    private static final CourseRepository courseRepository = new CourseRepository();
    private final AddCourseOfferingService addCourseOfferingService = new AddCourseOfferingService(courseRepository);
    private final AddCourseOfferingCommand addCourseOfferingCommand = new AddCourseOfferingCommand(addCourseOfferingService);
    private final RegisterCourseService registerCourseService = new RegisterCourseService(registrationRepository, courseRepository);

    private final RegisterCourseCommand registerCourseCommand = new RegisterCourseCommand(registerCourseService);
    CommandFactory commandFactory = CommandFactory.getInstance();
    private static final String ADD_COURSE_COMMAND = "ADD-COURSE-OFFERING";
    private static final String REGISTER_COURSE_COMMAND = "REGISTER";
    private static final String ALLOT_COURSE = "ALLOT";
    private static final String CANCEL_COURSE = "CANCEL";
    private final IAllotCourseService allotCourseService = new AllotCourseImpl(courseRepository, registrationRepository);
    private final AllotCourseCommand allotCourseCommand = new AllotCourseCommand(allotCourseService);
    private final ICancelCourseService cancelCourseService = new CourseCancelService(courseRepository, registrationRepository);
    private final CancelCourseCommand cancelCourseCommand = new CancelCourseCommand(cancelCourseService);

    public CommandFactory getCommandFactory() {
        commandFactory.registerNewCommand(ADD_COURSE_COMMAND, addCourseOfferingCommand);
        commandFactory.registerNewCommand(REGISTER_COURSE_COMMAND, registerCourseCommand);
        commandFactory.registerNewCommand(ALLOT_COURSE, allotCourseCommand);
        commandFactory.registerNewCommand(CANCEL_COURSE, cancelCourseCommand);
        return commandFactory;
    }
}
