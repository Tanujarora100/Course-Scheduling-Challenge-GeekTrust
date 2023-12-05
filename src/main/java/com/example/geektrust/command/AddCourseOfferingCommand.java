package com.example.geektrust.command;

import com.example.geektrust.entity.Course;
import com.example.geektrust.exception.CourseAlreadyExistsException;
import com.example.geektrust.exception.InvalidCommandException;
import com.example.geektrust.service.AddCourseOfferingService;
import com.example.geektrust.utils.Utils;

public class AddCourseOfferingCommand implements ICommand {
    private final AddCourseOfferingService addCourseOfferingService;
//    ADD-COURSE-OFFERING JAVA JAMES 15062022 1 2


    public AddCourseOfferingCommand(AddCourseOfferingService addCourseOfferingService) {
        this.addCourseOfferingService = addCourseOfferingService;
    }

    @Override
    public void execute(String[] params) {
        if (params.length < Utils.ADD_COMMAND_EXPECTED_PARAMS) {
            Utils.printCommand(Utils.INPUT_DATA_ERROR);
            return;
        }
        Course course = generateCourseObject(params);
        try {
            Course generatedCourse = addCourseOfferingService.addCourse(course);
            Utils.printCommand(generatedCourse.getCourseID());
        } catch (InvalidCommandException | CourseAlreadyExistsException e) {
            Utils.printCommand(e.getMessage());
        }
    }

    private Course generateCourseObject(String[] params) {
        return new Course(params[1], params[2], params[3], Integer.valueOf(params[4]), Integer.valueOf(params[5]));
    }
}
