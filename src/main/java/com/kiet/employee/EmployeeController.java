package com.kiet.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // Temporary in-memory storage; no database is required.
    private final List<Employee> employees = new ArrayList<>();

    // (b) Add a new employee
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        if (findEmployee(employee.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Employee ID already exists.");
        }

        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    // (c) Retrieve all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employees);
    }

    // (c) Retrieve an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        Employee employee = findEmployee(id);

        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found.");
        }

        return ResponseEntity.ok(employee);
    }

    // (d) Update employee details
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        Employee existingEmployee = findEmployee(id);

        if (existingEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found.");
        }

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return ResponseEntity.ok(existingEmployee);
    }

    // (e) Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        Employee employee = findEmployee(id);

        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found.");
        }

        employees.remove(employee);
        return ResponseEntity.ok("Employee deleted successfully.");
    }

    // Helper method to search employee using ID
    private Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
}
