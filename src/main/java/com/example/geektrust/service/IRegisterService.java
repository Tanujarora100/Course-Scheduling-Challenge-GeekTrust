package com.example.geektrust.service;

import com.example.geektrust.entity.Registration;

public interface IRegisterService {
    Registration registerCourse(String emailId, String courseOfferingId);

}
