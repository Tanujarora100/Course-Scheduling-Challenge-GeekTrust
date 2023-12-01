package repositoryservice.impl;

import dto.EmployeeDto;
import entity.Employee;
import exception.DataValidationException;
import repository.IEmployeeRepository;
import repositoryservice.IEmployeeRepositoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeRepositoryServiceImpl implements IEmployeeRepositoryService {
    private final IEmployeeRepository employeeRepository;

    public EmployeeRepositoryServiceImpl(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto save(EmployeeDto emp) throws DataValidationException {
        Employee employee = mapToEmployee(emp);
        employeeRepository.save(employee);
        Optional<Employee> empObject = employeeRepository.findByEmail(emp.getEmailAddress());
        return empObject.map(this::mapToDto).orElseThrow(() -> new DataValidationException("Failed to save employee"));
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(this::mapToDtoWithExceptionHandling)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDto> findById(String s) {
        Optional<Employee> byId = employeeRepository.findById(s);
        if (byId.isPresent()) {
            EmployeeDto employeeDto = mapToDto(byId.get());
            return Optional.of(employeeDto);

        }
        return Optional.empty();
    }

    private Optional<EmployeeDto> mapToDtoWithExceptionHandling(Employee employee) {
        try {
            return Optional.of(mapToDto(employee));
        } catch (DataValidationException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return Optional.empty();
        }
    }

    @Override
    public boolean existByID(String email) {
        return validateEmailID(email);
    }

    @Override
    public void delete(EmployeeDto object) throws DataValidationException {
        if (object != null) {
            employeeRepository.delete(mapToEmployee(object));
        }
    }

    @Override
    public void deleteByID(String email) {
        if (validateEmailID(email)) {
            employeeRepository.deleteByID(email);
        }
    }

    @Override
    public boolean validateID(String s) {
        return employeeRepository.existById(s);
    }

    @Override
    public Employee mapToEmployee(EmployeeDto employeeDto) throws DataValidationException {
        return new Employee(employeeDto.getEmailAddress());
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        if (validateEmailID(email)) {
            return employeeRepository.findByEmail(email)
                    .map(this::mapToDto)
                    .orElseThrow(() -> new DataValidationException("Could not find employee"));
        }
        return null;
    }

    public EmployeeDto mapToDto(Employee employee) throws DataValidationException {
        return new EmployeeDto(employee.getEmailId());
    }

    // If needed, you can also create a reverse mapping method
    public Employee mapToEntity(EmployeeDto employeeDto) throws DataValidationException {
        return new Employee(employeeDto.getEmailAddress());
    }

    private boolean validateEmailID(String email) {
        return email != null && employeeRepository.existById(email);
    }


}
