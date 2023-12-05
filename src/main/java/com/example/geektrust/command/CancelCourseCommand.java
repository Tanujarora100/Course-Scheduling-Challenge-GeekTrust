package com.example.geektrust.command;

import com.example.geektrust.dto.CourseCancelDTO;
import com.example.geektrust.exception.RegistrationNotFound;
import com.example.geektrust.service.ICancelCourseService;
import com.example.geektrust.utils.Utils;

public class CancelCourseCommand implements ICommand {

    private final ICancelCourseService cancelCourseService;

    public CancelCourseCommand(ICancelCourseService cancelCourseService) {
        this.cancelCourseService = cancelCourseService;
    }

    @Override
    public void execute(String[] params) {
        if (params.length < Utils.CANCEL_COMMAND_EXPECTED_PARAMS) {
            Utils.printCommand(Utils.INPUT_DATA_ERROR);
            return;
        }
        try {
            CourseCancelDTO courseCancelDTO = cancelCourseService.cancelCourse(params[1]);
            Utils.printCommand(courseCancelDTO.getRegistrationID() + " " + courseCancelDTO.getCourseStatus());
        } catch (RegistrationNotFound | NullPointerException e) {
            Utils.printCommand(e.getMessage());
        }
    }
}
