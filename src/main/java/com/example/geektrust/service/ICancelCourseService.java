package com.example.geektrust.service;

import com.example.geektrust.dto.CourseCancelDTO;

public interface ICancelCourseService {
    CourseCancelDTO cancelCourse(String courseID);
}
