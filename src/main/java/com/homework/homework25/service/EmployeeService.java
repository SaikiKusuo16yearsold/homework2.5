package com.homework.homework25.service;

import com.homework.homework25.model.Employee;
import com.homework.homework25.exceptions.EmployeeNotFoundException;
import com.homework.homework25.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    public static int maximumEmployees = 5;

    public List<Employee> numbers = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (numbers.size() == maximumEmployees) {
            throw new EmployeeStorageIsFullException("Превышен размер");
        }
        if (isEmployeePresent(firstName, lastName)) {
            throw new EmployeeStorageIsFullException("Уже есть ");
        } else {
            Employee employee = new Employee(firstName, lastName);
            numbers.add(employee);
            return employee;
        }

    }

    public Employee deleteEmployee(String firstName, String lastName) {
        if (isEmployeePresent(firstName, lastName)) {
            Employee employee = new Employee(firstName, lastName);
            numbers.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    public Employee getEmployeeByName(String firstName, String lastName) {
        if (isEmployeePresent(firstName, lastName)) {
            return new Employee(firstName, lastName);
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    public boolean isEmployeePresent(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        return numbers.contains(employee);
    }

    public List<Employee> getAllEmployees() {
        return numbers;
    }
}
