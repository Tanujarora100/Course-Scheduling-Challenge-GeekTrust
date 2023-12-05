package com.example.geektrust.entity;

import com.example.geektrust.exception.EmailValidationException;
import com.example.geektrust.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    public Employee(String emailID) {
        validateEmail(emailID);
        this.emailID = emailID;
        this.name = Utils.generateName(emailID);
    }

    public String getEmailID() {
        return emailID;
    }

    public String getName() {
        return name;
    }

    private final String emailID;
    private final String name;


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private void validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new EmailValidationException(Utils.INVALID_EMAIL_PATTERN);
    }
}
