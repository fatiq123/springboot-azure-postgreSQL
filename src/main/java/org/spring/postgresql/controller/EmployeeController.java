package org.spring.postgresql.controller;

import org.spring.postgresql.model.Employee;
import org.spring.postgresql.repository.EmployeeRepository;
import org.spring.postgresql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring Boot PostgreSQL";
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployeeInDatabase(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getEmployee() {
        try {
            List<Employee> employees = employeeService.getALlEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // To find employee by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Long id) {
        try {
            employeeService.getEmployeeById(id);
            return ResponseEntity.ok().body(Collections.singletonMap("employee", employeeService.getEmployeeById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAllEmployees() {
        try {
            employeeService.deleteAllEmployees();
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        try {
            employeeService.updateEmployee(id, employee);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
