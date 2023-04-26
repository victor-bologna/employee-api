package com.api.quotationmanagement.repository;

import com.api.quotationmanagement.config.DatabaseConfig;
import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.exception.EmployeeNotFoundException;
import com.api.quotationmanagement.domain.mother.EmployeeMother;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryImplTest {

    @Mock
    private DatabaseConfig databaseConfig;

    @InjectMocks
    private EmployeeRepositoryImpl employeeRepository;

    private Map<Long, Employee> employeeList = new HashMap<>();

    @BeforeEach
    void setup() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Andre dos Santos");
        employee.setAddress("Rua Tapucai, 23 - São Paulo - SP");
        employee.setSalary(15000.00);
        employee.setRole("Gerente");
        employee.setTelephone("+55 (11) 9 2621-0006");
        employeeList.put(1L, employee);

        employee = new Employee();
        employee.setId(2L);
        employee.setName("Pedro Cipoli");
        employee.setAddress("Rua dos Anjos, 23 - Santo André - SP");
        employee.setSalary(12000.00);
        employee.setRole("Apresentador");
        employee.setTelephone("+55 (11) 9 5783-3006");
        employeeList.put(2L, employee);

        given(databaseConfig.getDatabase()).willReturn(employeeList);
    }

    @Test
    void save() {
        Employee employee = EmployeeMother.getEmployee();
        employee.setId(null);

        employeeRepository.save(employee);

        verify(databaseConfig).getDatabase();
    }

    @Test
    void saveWithDatabaseEmpty() {
        employeeList.clear();
        Employee employee = EmployeeMother.getEmployee();
        employee.setId(null);

        employeeRepository.save(employee);

        verify(databaseConfig).getDatabase();
    }

    @Test
    void findById() {
        Employee employeeExample = new Employee();
        employeeExample.setId(1L);
        employeeExample.setName("Andre dos Santos");
        employeeExample.setAddress("Rua Tapucai, 23 - São Paulo - SP");
        employeeExample.setSalary(15000.00);
        employeeExample.setRole("Gerente");
        employeeExample.setTelephone("+55 (11) 9 2621-0006");

        Optional<Employee> employee = employeeRepository.findById(1L);

        then(employee.get()).usingRecursiveComparison().isEqualTo(employeeExample);

        verify(databaseConfig).getDatabase();
    }

    @Test
    void findAll() {
        employeeRepository.findAll();

        verify(databaseConfig).getDatabase();
    }

    @Test
    void update() {
        Employee employee = EmployeeMother.getEmployee();
        employee.setId(null);
        employeeRepository.update(1L, employee);

        verify(databaseConfig, times(3)).getDatabase();
    }

    @Test
    void updateNotFound() {
        EmployeeNotFoundException employeeNotFoundException =
                Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                        employeeRepository.update(5L, new Employee()));

        then(employeeNotFoundException.getMessage()).isEqualTo("Could not find employee in database to update.");

        verify(databaseConfig, times(2)).getDatabase();
    }

    @Test
    void deleteById() {
        employeeRepository.deleteById(1L);

        verify(databaseConfig, times(2)).getDatabase();
    }
}