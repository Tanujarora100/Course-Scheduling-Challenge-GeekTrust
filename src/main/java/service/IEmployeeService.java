package service;

import dto.EmployeeDto;
import exception.DataValidationException;

public interface IEmployeeService {

    boolean existByID(String emailID);
    void saveEmployee(EmployeeDto employee) throws DataValidationException;
}
