package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;


@Getter
@Setter
@Builder
@AllArgsConstructor


public class Course {
    private final String courseName;
    private String courseID;
    private final String instructor;
    private Map<String, Employee> employeeMap;
    private final Integer minimumEmployee;
    private final Integer maximumEmployee;
    private final LocalDate date;
    private boolean isCancelled;
    private boolean isAlloted;


}
