package com.api.quotationmanagement.repository;

import com.api.quotationmanagement.config.DatabaseConfig;
import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.exception.EmployeeNotFoundException;
import com.api.quotationmanagement.domain.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private DatabaseConfig databaseConfig;

    @Override
    public Employee save(Employee employee) {
        log.info("Saving employee.");
        Map<Long, Employee> database = databaseConfig.getDatabase();
        if(employee.getId() != null) {
            database.put(employee.getId(), employee);
        } else if(database.isEmpty()) {
            employee.setId(1L);
            database.put(1L, employee);
        } else {
            long newID = database.size() + 1L;
            employee.setId(newID);
            database.put(newID, employee);
        }
        return employee;
    }

    @Override
    public Optional<Employee> findById(Long employeeId) {
        Map<Long, Employee> database = databaseConfig.getDatabase();
        return Optional.ofNullable(database.get(employeeId));
    }

    @Override
    public List<Employee> findAll() {
        Map<Long, Employee> database = databaseConfig.getDatabase();
        List<Employee> employeeList = new ArrayList<>();
        database.forEach((aLong, employee) -> employeeList.add(employee));
        log.info("Found all employees.");
        return employeeList;
    }

    @Override
    public void update(Long employeeId, Employee employee) {
        Map<Long, Employee> database = databaseConfig.getDatabase();
        Optional<Employee> employeeOptional = findById(employeeId);
        if(employeeOptional.isPresent()) {
            log.info("Updating employee id: {} with following data: {}", employeeId, employeeOptional.get());
            database.remove(employeeId);
            employee.setId(employeeId);
            save(employee);
            return;
        }
        String message = "Could not find employee in database to update.";
        log.error(message);
        throw new EmployeeNotFoundException(message);
    }

    @Override
    public void deleteById(Long employeeId) {
        Map<Long, Employee> database = databaseConfig.getDatabase();
        database.remove(employeeId);
        for(Integer count = employeeId.intValue(); count <= database.size(); count ++)
        {
            Optional<Employee> employeeOptional = findById(count.longValue() + 1L);
            if(employeeOptional.isPresent()) {
                employeeOptional.get().setId(count.longValue());
                database.put(count.longValue(), employeeOptional.get());
                database.remove(count.longValue() + 1L);
            }
        }
    }
}
