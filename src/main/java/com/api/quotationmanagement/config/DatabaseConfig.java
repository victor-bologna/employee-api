package com.api.quotationmanagement.config;

import com.api.quotationmanagement.domain.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DatabaseConfig {

    private Map<Long, Employee> employeeList = new HashMap<>();

    @Bean
    public Map<Long, Employee> initialData() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Andre dos Santos");
        employee.setAddress("Rua Tapucai, 23 - São Paulo - SP");
        employee.setSalary(15000.00);
        employee.setRole("Gerente");
        employee.setTelephone("+55 (11) 9 2621-0006");
        employeeList.put(1L, employee);
        return employeeList;
    }

    @Bean
    public Map<Long, Employee> getDatabase() {
        return employeeList;
    }
}
