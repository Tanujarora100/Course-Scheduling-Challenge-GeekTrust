package com.example.geektrust.entity;

import com.example.geektrust.constants.RegistrationStatus;

public class Registration {
    private final String courseID;

    public String getEmployeeName() {
        return employeeName;
    }

    private final String employeeName;
    private final String emailID;

    public String getRegistrationID() {
        return registrationID;
    }

    private final String registrationID;
    private static final String REG_COURSE = "REG-COURSE";
    private static final String DELIMITER = "-";

    public String getCourseID() {
        return courseID;
    }

    public String getEmailID() {
        return emailID;
    }

    public boolean isAlloted() {
        return isAlloted;
    }

    public void setAlloted(boolean alloted) {
        isAlloted = alloted;
    }

    private boolean isAlloted;

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    private RegistrationStatus registrationStatus;

    public Registration(String courseID, String empEmail, String empName) {
        this.courseID = courseID;
        this.employeeName = empName;
        this.emailID = empEmail;
        this.isAlloted = false;
        this.registrationID = generateRegistrationId();

    }

    //REG-COURSE-WOO-DATASCIENCE ACCEPTED
    private String generateRegistrationId() {
        String empName = getEmployeeName();
        String[] parts = courseID.split("-");
        String courseName = parts[1];
        return REG_COURSE + DELIMITER + empName + DELIMITER + courseName;
    }

}
