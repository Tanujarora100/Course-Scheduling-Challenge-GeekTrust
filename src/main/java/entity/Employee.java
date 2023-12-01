package entity;

import exception.DataValidationException;
import util.Messages;


public class Employee {
    private final String name;
    private final String emailId;

    public Employee(String emailId) throws DataValidationException {
        if (!emailId.isEmpty() && Messages.VALID_EMAIL_REGEX_VALIDATOR.matcher(emailId).matches()) {
            this.name = emailId.substring(0, emailId.indexOf('@'));
            this.emailId = emailId;
        } else {
            throw new DataValidationException(Messages.INPUT_DATA_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }


    public String getEmailId() {
        return emailId;
    }

}
