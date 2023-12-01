package dto;

import exception.DataValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import util.Messages;

@Data
@AllArgsConstructor

public class EmployeeDto {
    private final String emailAddress;
    private final String name;

    public EmployeeDto(String emailAddress) {

        if (!emailAddress.isEmpty() && Messages.VALID_EMAIL_REGEX_VALIDATOR.matcher(emailAddress).matches()) {
            this.name = emailAddress.substring(0, emailAddress.indexOf('@'));
            this.emailAddress = emailAddress;

        } else {
            throw new DataValidationException("Invalid email address");
        }
    }

}
