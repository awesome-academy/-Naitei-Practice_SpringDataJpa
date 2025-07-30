package org.example.Service;

import org.example.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();

    public Employee createEmployee(Employee e);
    public Employee updateEmployee(Employee e);
    public Long deleteEmployee(Employee e);
    public Employee findById(Long id);
}
