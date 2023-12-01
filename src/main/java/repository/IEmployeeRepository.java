package repository;

import entity.Employee;

import java.util.Optional;

public interface IEmployeeRepository extends CRUDRepository<Employee,String>{
    Optional<Employee> findByEmail(String email);
}
