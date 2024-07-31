package org.spring.postgresql.service;

import org.spring.postgresql.model.Employee;
import org.spring.postgresql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    // TO Save Employee In Database
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }



    // Get ALL Employees
    public List<Employee> getALlEmployees() {
        return employeeRepository.findAll();
    }

    // Get Employee By ID
    public List<Employee> getEmployeeById(Long id) {
        if (employeeRepository.existsById(id)) {
            return employeeRepository.findAllById(Collections.singleton(id));
        } else {
            return Collections.emptyList();
        }
    }


    // TO Delete Employee By ID
    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }
    // TO Delete all Employees
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }



    // Update Employee in Database By ID
    public void updateEmployee(Long id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employeeToUpdate = employeeOptional.get();
            employeeToUpdate.setId(id);
            employeeToUpdate.setFirstName(employee.getFirstName());
            employeeToUpdate.setLastName(employee.getLastName());
            employeeToUpdate.setEmail(employee.getEmail());
            employeeRepository.save(employeeToUpdate);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }



}
