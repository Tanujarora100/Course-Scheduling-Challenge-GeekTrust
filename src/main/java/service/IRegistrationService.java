package service;

import dto.RegistrationDto;
import exception.DataValidationException;

public interface IRegistrationService {
    String create(RegistrationDto registrationDto) throws DataValidationException;
}
