
package com.example.employee.service;

import com.example.employee.dto.EmployeeRequest;
import com.example.employee.dto.EmployeePatchRequest;
import com.example.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(EmployeeRequest request);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, EmployeeRequest request);

    Employee patchEmployee(Long id, EmployeePatchRequest request);

    void deleteEmployee(Long id);

    List<Employee> getEmployeesByDepartment(String department);

    List<Employee> getEmployeesBySalary(double amount);

    List<Employee> searchEmployeesByName(String name);
}
