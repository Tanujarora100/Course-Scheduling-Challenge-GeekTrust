package dto;

import entity.Employee;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class CourseDto {
    private final String courseName;
    private final String courseID;
    private final String instructor;
    private Map<String, Employee> employeeMap;
    private final Integer minimumEmployee;
    private final Integer maximumEmployee;
    private final LocalDate date;
    private boolean isCancelled;
    private boolean isAlloted;


}
