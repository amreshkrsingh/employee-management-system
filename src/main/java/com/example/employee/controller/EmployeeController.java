
package com.example.employee.controller;

import com.example.employee.dto.ApiResponse;
import com.example.employee.dto.EmployeePatchRequest;
import com.example.employee.dto.EmployeeRequest;
import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        Employee employee = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "Employee created successfully",
                        employee,
                        201));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employees fetched successfully",
                        employees,
                        200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(
            @PathVariable Long id) {

        Employee employee = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employee fetched successfully",
                        employee,
                        200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        Employee employee = employeeService.updateEmployee(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employee updated successfully",
                        employee,
                        200));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> patchEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeePatchRequest request) {

        Employee employee = employeeService.patchEmployee(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employee partially updated successfully",
                        employee,
                        200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employee deleted successfully",
                        null,
                        200));
    }

    @GetMapping("/department/{dept}")
    public ResponseEntity<ApiResponse<List<Employee>>> getByDepartment(
            @PathVariable("dept") String department) {

        List<Employee> employees =
                employeeService.getEmployeesByDepartment(department);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employees fetched by department",
                        employees,
                        200));
    }

    @GetMapping("/salary/{amount}")
    public ResponseEntity<ApiResponse<List<Employee>>> getBySalary(
            @PathVariable double amount) {

        List<Employee> employees =
                employeeService.getEmployeesBySalary(amount);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employees fetched by salary",
                        employees,
                        200));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Employee>>> searchByName(
            @RequestParam String name) {

        List<Employee> employees =
                employeeService.searchEmployeesByName(name);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Employees searched successfully",
                        employees,
                        200));
    }
}
