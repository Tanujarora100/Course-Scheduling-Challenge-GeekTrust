package command;

import dto.CourseDto;
import exception.DataValidationException;
import service.ICourseService;
import util.Messages;

import java.time.LocalDate;
import java.util.List;

public class AddCourseCommand implements ICommand {
    private final ICourseService courseService;

    public AddCourseCommand(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void execute(List<String> params) {
        try {
            if (params.size() < 2) {
                String message = Messages.INPUT_DATA_ERROR_MESSAGE + "(not enough input values)";
                throw new DataValidationException(message);
            }
            // Sample Input Token List:- ["ADD-COURSE-OFFERING","JAVA","JAMES","15062022","1","2"]
            String courseName = params.get(1);
            String instructor = "";
            String date = "";
            int addCourseCommandSize = 4;
            if (params.size() >= addCourseCommandSize) {
                instructor = params.get(2);
                date = params.get(3);
            }

            if (instructor == null || instructor.isEmpty() || date == null || date.isEmpty() || courseName.isEmpty()) {
                throw new DataValidationException("INPUT_DATA_ERROR\n(because of missing instructor and course-offering-date)\n");
            }
            CourseDto courseDto = createCourseDto(params);
            CourseDto course = courseService.save(courseDto);
            String courseID = "OFFERING-" + course.getCourseName() + "-" + course.getInstructor();
            System.out.println(courseID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private CourseDto createCourseDto(List<String> params) {
        LocalDate localDate = getLocalDate(params.get(3));
        return CourseDto.builder()
                .courseName(params.get(1))
                .instructor(params.get(2))
                .date(localDate)
                .maximumEmployee(Integer.parseInt(params.get(5)))
                .minimumEmployee(Integer.parseInt(params.get(4)))
                .isAlloted(false)
                .isCancelled(false).build();
    }

    private LocalDate getLocalDate(String date) {
        return LocalDate.parse(date, Messages.DATE_FORMATTER_PATTERN);
    }


}
