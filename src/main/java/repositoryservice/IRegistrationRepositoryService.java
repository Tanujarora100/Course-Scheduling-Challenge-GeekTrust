package repositoryservice;

import dto.RegistrationDto;
import entity.Registration;

public interface IRegistrationRepositoryService extends CRUDRepositoryService<RegistrationDto,String> {
    Registration mapToRegistration(RegistrationDto registrationDto);

}
