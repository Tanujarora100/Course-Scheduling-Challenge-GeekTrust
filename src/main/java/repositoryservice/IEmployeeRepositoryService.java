package repositoryservice;

import dto.EmployeeDto;
import entity.Employee;
import exception.DataValidationException;
import repository.CRUDRepository;

public interface IEmployeeRepositoryService extends CRUDRepositoryService<EmployeeDto,String> {
   Employee mapToEmployee(EmployeeDto employee) throws DataValidationException;

   EmployeeDto findByEmail(String email);
}
