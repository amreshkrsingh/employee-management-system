package com.example.employee.repository;

import com.example.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);

    List<Employee> findByDepartmentIgnoreCase(String department);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByNameContainingIgnoreCase(String name);
}
