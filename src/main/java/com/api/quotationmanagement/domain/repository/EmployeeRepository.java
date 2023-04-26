package com.api.quotationmanagement.domain.repository;

import com.api.quotationmanagement.domain.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository {

    Optional<Employee> findById(Long employeeId);

    List<Employee> findAll();

    Employee save(Employee employee);

    void deleteById(Long employeeId);

    void update(Long employeeId, Employee employee);
}
