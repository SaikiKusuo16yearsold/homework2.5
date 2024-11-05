package com.homework.homework25.service;

import com.homework.homework25.model.Employee;
import com.homework.homework25.exceptions.EmployeeNotFoundException;
import com.homework.homework25.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    public static int maximumEmployees = 5;

    private Map<String, Employee> numbers = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (numbers.size() == maximumEmployees) {
            throw new EmployeeStorageIsFullException("Превышен размер");
        }
        Employee worker = new Employee(firstName, lastName);
        Employee employee = numbers.get(firstName + " " + lastName);
        if (employee != null) {
            throw new EmployeeStorageIsFullException("Уже есть ");
        } else {
            numbers.put(firstName + " " + lastName, worker);
            return employee;
        }

    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = numbers.get(firstName + " " + lastName);
        if (employee != null) {
            numbers.remove(firstName + " " + lastName);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    public Employee getEmployeeByName(String firstName, String lastName) {
        String fullname = firstName + " " + lastName;
        Employee employee = numbers.get(fullname);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        } else {
            return employee;
        }

    }

    public Collection<Employee> getAllEmployees() {
        return numbers.values();
    }
}
