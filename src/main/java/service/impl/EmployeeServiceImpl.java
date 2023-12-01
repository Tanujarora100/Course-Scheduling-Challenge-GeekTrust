package service.impl;

import dto.EmployeeDto;
import exception.DataValidationException;
import repositoryservice.IEmployeeRepositoryService;
import service.IEmployeeService;
import util.Messages;

public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepositoryService employeeRepositoryService;

    public EmployeeServiceImpl(IEmployeeRepositoryService employeeRepositoryService) {
        this.employeeRepositoryService = employeeRepositoryService;
    }

    @Override
    public boolean existByID(String emailID) {
        return employeeRepositoryService.existByID(emailID);
    }

    @Override
    public void saveEmployee(EmployeeDto employee) throws DataValidationException {
        if (validateDto(employee) && validateEmailFormat(employee.getEmailAddress()))
            employeeRepositoryService.save(employee);
        else {
            throw new DataValidationException(Messages.INPUT_DATA_ERROR_MESSAGE);
        }
    }

    private boolean validateEmailFormat(String emailAddress) {
        return Messages.VALID_EMAIL_REGEX_VALIDATOR.matcher(emailAddress).matches();
    }

    private boolean validateDto(EmployeeDto employee) {
        return employee.getEmailAddress() != null && !employee.getEmailAddress().isEmpty();
    }
}
