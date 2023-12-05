package com.example.geektrust.dto;

public class AllotCourseDto {
    public String getFinalResult() {
        return finalResult;
    }

    private final String finalResult;

    public AllotCourseDto(String finalResult) {
        this.finalResult = finalResult;
    }
}
