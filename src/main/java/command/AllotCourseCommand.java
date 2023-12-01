package command;

import service.ICourseService;
import util.Messages;

import java.util.List;

public class AllotCourseCommand implements ICommand {
    private final ICourseService courseService;

    public AllotCourseCommand(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void execute(List<String> commandArguments) {
        try {
            if (commandArguments.size() == Messages.EXPECTED_ARGS_ALLOT) {
                String courseOfferingId = commandArguments.get(1);
                String allotmentResult = courseService.allotCourse(courseOfferingId);
                System.out.println(allotmentResult);
            } else {
                printErrorMessage(Messages.INPUT_DATA_ERROR_MESSAGE);
            }
        } catch (Exception e) {
            printErrorMessage(e.getMessage());
        }
    }

    private void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}

