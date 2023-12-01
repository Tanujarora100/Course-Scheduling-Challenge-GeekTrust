package command;

import service.ICourseService;
import util.Messages;

import java.util.List;

public class CancelCourseCommand implements ICommand {
    private final ICourseService courseService;

    public CancelCourseCommand(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void execute(List<String> params) {
        try {
            if (params.size() == Messages.EXPECTED_ARGS_CANCEL) {
                String courseOfferingId = params.get(1);
                String result = "";
                result = courseService.cancelCourse(courseOfferingId);
                System.out.println(result);
            } else {
                System.out.println(Messages.INPUT_DATA_ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
