package repository.impl;

import entity.Employee;
import repository.IEmployeeRepository;

import java.util.*;

public class EmployeeRepositoryImpl implements IEmployeeRepository {
    private final Map<String, Employee> employeeMap;

    public EmployeeRepositoryImpl(Map<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    public EmployeeRepositoryImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public void save(Employee object) {
        employeeMap.put(object.getEmailId().toUpperCase(), object);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Optional<Employee> findById(String emailID) {
        return Optional.of(employeeMap.get(emailID));
    }

    @Override
    public boolean existById(String emailID) {
        Optional<Employee> byId = findById(emailID);
        return byId.isPresent();
    }

    @Override
    public void delete(Employee object) {
        if (existById(object.getEmailId()))
            employeeMap.remove(object.getEmailId());

    }

    @Override
    public void deleteByID(String emailID) {
        if (existById(emailID))
            employeeMap.remove(emailID);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return Optional.empty();
    }
}
