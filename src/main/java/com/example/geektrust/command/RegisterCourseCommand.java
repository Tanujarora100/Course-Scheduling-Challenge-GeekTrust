package com.example.geektrust.command;

import com.example.geektrust.entity.Registration;
import com.example.geektrust.exception.CourseCanceledException;
import com.example.geektrust.exception.CourseFullException;
import com.example.geektrust.exception.CourseNotFoundException;
import com.example.geektrust.service.IRegisterService;
import com.example.geektrust.utils.Utils;

public class RegisterCourseCommand implements ICommand {
    private final IRegisterService registerService;

    public RegisterCourseCommand(IRegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public void execute(String[] params) {
        if (params.length < Utils.REGISTER_COMMAND_EXPECTED_PARAMS) {
            Utils.printCommand(Utils.INPUT_DATA_ERROR);
            return;
        }
        try {
            Registration registration = registerService.registerCourse(params[1], params[2]);
            Utils.printCommand(registration.getRegistrationID() + " " + Utils.ACCEPTED_MESSAGE);
        } catch (CourseFullException e) {
            Utils.printCommand(Utils.COURSE_FULL_ERROR);
        } catch (CourseNotFoundException e) {
            Utils.printCommand(Utils.INPUT_DATA_ERROR);
        } catch (CourseCanceledException e) {
            Utils.printCommand(Utils.COURSE_CANCELED_ERROR);
        }
    }
}
