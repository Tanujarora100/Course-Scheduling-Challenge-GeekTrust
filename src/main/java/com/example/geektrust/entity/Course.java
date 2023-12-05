package com.example.geektrust.entity;

import com.example.geektrust.constants.CourseStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Course {

    public Course(String courseName, String instructorName, String date, Integer minimumEmployees, Integer maximumEmployees) {
        this.date = LocalDate.parse(date, Course.formatter);
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.minimumEmployees = minimumEmployees;
        this.maximumEmployees = maximumEmployees;
        this.employeeList = new ArrayList<>();
        this.courseID = generateCourseID();
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public String getCourseName() {
        return courseName;
    }

    public LocalDate getDate() {
        return date;
    }


    private final LocalDate date;

    public String getInstructorName() {
        return instructorName;
    }

    public Integer getMinimumEmployees() {
        return minimumEmployees;
    }

    public Integer getMaximumEmployees() {
        return maximumEmployees;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    private final String courseName;
    private final String instructorName;
    private final Integer minimumEmployees;
    private final Integer maximumEmployees;
    private List<Employee> employeeList;
    private CourseStatus courseStatus;

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseID() {
        return courseID;
    }


    private final String courseID;


    private String generateCourseID() {
        return "OFFERING" + "-" + getCourseName() + "-" + getInstructorName();
    }

    public void addEmployeeToCourse(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployeeFromCourse(Employee employee) {
        employeeList.remove(employee);
    }

    @Override
    public String toString() {
        return "Course{" +
                "date=" + date +
                ", courseName='" + courseName + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", minimumEmployees=" + minimumEmployees +
                ", maximumEmployees=" + maximumEmployees +
                ", employeeList=" + employeeList +
                ", courseID='" + courseID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseName.equals(course.courseName) && instructorName.equals(course.instructorName) && Objects.equals(courseID, course.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, instructorName, courseID);
    }
}
