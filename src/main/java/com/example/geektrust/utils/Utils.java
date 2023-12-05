package com.example.geektrust.utils;

import java.time.format.DateTimeFormatter;

public class Utils {
    public static final String INVALID_EMAIL_PATTERN = "INVALID_EMAIL_PATTERN";
    public static final String INVALID_COMMAND_RESPONSE = "INVALID_COMMAND_RESPONSE";
    public static final String INPUT_DATA_ERROR = "INPUT_DATA_ERROR";
    public static final String COURSE_CANCELED_ERROR = "COURSE_CANCELED";
    public static final String COURSE_FULL_ERROR = "COURSE_FULL_ERROR";
    public static final String COURSE_NOT_FOUND_ERROR = "COURSE_NOT_FOUND";
    public static final String ACCEPTED_MESSAGE = "ACCEPTED";

    public static <T> void printCommand(T t) {
        System.out.println(t);
    }


    public static final Integer ADD_COMMAND_EXPECTED_PARAMS = 6;
    public static final Integer ALLOT_COMMAND_EXPECTED_PARAMS = 2;
    public static final Integer CANCEL_COMMAND_EXPECTED_PARAMS = 2;
    public static final Integer REGISTER_COMMAND_EXPECTED_PARAMS = 3;

    public static String generateName(String emailId) {
        return emailId.substring(0, emailId.indexOf("@"));
    }

}
