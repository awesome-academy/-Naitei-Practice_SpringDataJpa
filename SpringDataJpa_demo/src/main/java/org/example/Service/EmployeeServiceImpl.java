package org.example.Service;

import org.example.Entity.Employee;
import org.example.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository e){
        this.employeeRepository=e;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee e) {
        return this.employeeRepository.save(e);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee e) {
        return this.employeeRepository.save(e);
    }

    @Override
    @Transactional
    public Long deleteEmployee(Employee e) {
        this.employeeRepository.delete(e);
        return e.getId();
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow();
    }




}
