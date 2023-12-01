package util;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Messages {
    public static final String COURSE_CANCELLED ="COURSE_CANCELLED";



    private Messages() {
        //private constructor
    }

    public static final Pattern VALID_EMAIL_REGEX_VALIDATOR = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final String ACCEPTED_MESSAGE = "ACCEPTED";
    public static final String REJECTED_MESSAGE = "REJECTED";
    public static final String CANCEL_ACCEPTED_MESSAGE = "CANCEL_ACCEPTED";
    public static final String CANCEL_REJECTED_MESSAGE = "CANCEL_REJECTED";
    public static final String ALLOT_COURSE_MESSAGE = "CONFIRMED";
    public static final String ADD_COURSE_OFFERING_MESSAGE = "ADD_COURSE_OFFERING";
    public static final String INPUT_DATA_ERROR_MESSAGE = "INPUT_DATA_ERROR";

    public static final int EXPECTED_ARGS_ADD_COURSE = 5;
    public static final int EXPECTED_ARGS_REGISTER_COURSE = 3;
    public static final int EXPECTED_ARGS_CANCEL = 2;
    public static final int EXPECTED_ARGS_ALLOT = 2;

    public static final String COURSE_FULL_ERROR_MESSAGE="COURSE_FULL_ERROR";

    public static final DateTimeFormatter DATE_FORMATTER_PATTERN = DateTimeFormatter.ofPattern("ddMMyyyy");
}
