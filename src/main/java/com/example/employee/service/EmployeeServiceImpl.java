
package com.example.employee.service;

import com.example.employee.dto.EmployeePatchRequest;
import com.example.employee.dto.EmployeeRequest;
import com.example.employee.entity.Employee;
import com.example.employee.exception.DuplicateEmailException;
import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.repository.EmployeeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(EmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(
                    "Email already exists: " + request.getEmail());
        }

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        employee.setJoiningDate(request.getJoiningDate());

        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with ID: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = getEmployeeById(id);

        if (!employee.getEmail().equalsIgnoreCase(request.getEmail())
                && employeeRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateEmailException(
                    "Email already exists: " + request.getEmail());
        }

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        employee.setJoiningDate(request.getJoiningDate());

        return employeeRepository.save(employee);
    }

    @Override
    public Employee patchEmployee(Long id, EmployeePatchRequest request) {

        Employee employee = getEmployeeById(id);

        if (request.getName() != null) {
            employee.setName(request.getName());
        }

        if (request.getEmail() != null) {

            if (!employee.getEmail().equalsIgnoreCase(request.getEmail())
                    && employeeRepository.existsByEmail(request.getEmail())) {

                throw new DuplicateEmailException(
                        "Email already exists: " + request.getEmail());
            }

            employee.setEmail(request.getEmail());
        }

        if (request.getDepartment() != null) {
            employee.setDepartment(request.getDepartment());
        }

        if (request.getSalary() != null) {
            employee.setSalary(request.getSalary());
        }

        if (request.getJoiningDate() != null) {
            employee.setJoiningDate(request.getJoiningDate());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesBySalary(double amount) {
        return employeeRepository.findBySalaryGreaterThan(amount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }
}
