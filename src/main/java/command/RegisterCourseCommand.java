package command;

import dto.RegistrationDto;
import exception.DataValidationException;
import service.IRegistrationService;
import util.Messages;

import java.util.List;

public class RegisterCourseCommand implements ICommand {
    private final IRegistrationService registrationService;

    public RegisterCourseCommand(IRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // IP:- <email-id> <course-offering-id>
    // OP:-	<course-registration-id> <status>
    // Sample Input Token List:- ["REGISTER","ANDY@GMAIL.COM","OFFERING-JAVA-JAMES"]
    @Override
    public void execute(List<String> params) throws DataValidationException {
        try {
            if (params.size() != Messages.EXPECTED_ARGS_REGISTER_COURSE) {
                throw new DataValidationException("INPUT_DATA_ERROR (not enough input values)");
            }

            String emailAddress = params.get(1);
            String courseOfferingId = params.get(2);

            if (emailAddress == null || emailAddress.isEmpty() || courseOfferingId == null || courseOfferingId.isEmpty()
                    || !Messages.VALID_EMAIL_REGEX_VALIDATOR.matcher(emailAddress).matches()) {
                throw new DataValidationException(Messages.INPUT_DATA_ERROR_MESSAGE);
            }

            RegistrationDto registrationDto = createRegistrationDto(emailAddress, courseOfferingId);
            String result = registrationService.create(registrationDto);
            System.out.println(result);

        } catch (DataValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    private RegistrationDto createRegistrationDto(String emailAddress, String courseID) {
        return RegistrationDto.builder()
                .emailAddress(emailAddress)
                .courseID(courseID)
                .build();
    }


}
