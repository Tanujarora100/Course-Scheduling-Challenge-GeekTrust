package com.example.geektrust.dto;

public class CourseCancelDTO {
    public CourseCancelDTO(String registrationID, String courseStatus) {
        this.courseStatus = courseStatus;
        this.registrationID = registrationID;
    }

    private final String courseStatus;

    public String getCourseStatus() {
        return courseStatus;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    private final String registrationID;
}
