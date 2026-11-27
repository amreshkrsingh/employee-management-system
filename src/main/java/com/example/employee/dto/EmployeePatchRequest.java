
package com.example.employee.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EmployeePatchRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @Positive(message = "Salary must be greater than zero")
    private Double salary;

    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
