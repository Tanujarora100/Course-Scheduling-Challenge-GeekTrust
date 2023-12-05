package com.example.geektrust.service;

import com.example.geektrust.dto.AllotCourseDto;

import java.util.List;

public interface IAllotCourseService {

    List<AllotCourseDto> allotCourse(String courseID);
}
