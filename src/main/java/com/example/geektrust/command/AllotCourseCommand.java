package com.example.geektrust.command;

import com.example.geektrust.dto.AllotCourseDto;
import com.example.geektrust.exception.CourseNotFoundException;
import com.example.geektrust.service.IAllotCourseService;
import com.example.geektrust.utils.Utils;

import java.util.List;

public class AllotCourseCommand implements ICommand {
    private final IAllotCourseService allotCourseService;

    public AllotCourseCommand(IAllotCourseService allotCourseService) {
        this.allotCourseService = allotCourseService;
    }

    @Override
    public void execute(String[] params) {
        if (params.length < Utils.ALLOT_COMMAND_EXPECTED_PARAMS) {
            Utils.printCommand(Utils.INPUT_DATA_ERROR);
            return;
        }
        try {
            List<AllotCourseDto> allotCourseDtos = allotCourseService.allotCourse(params[1]);
            for (AllotCourseDto allotCourseDto : allotCourseDtos) {
                Utils.printCommand(allotCourseDto.getFinalResult());
            }
        } catch (CourseNotFoundException e) {
            Utils.printCommand(Utils.INPUT_DATA_ERROR);
        }
    }
}
