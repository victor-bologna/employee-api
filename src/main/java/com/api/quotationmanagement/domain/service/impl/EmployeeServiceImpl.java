package com.api.quotationmanagement.domain.service.impl;

import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.exception.EmployeeNotFoundException;
import com.api.quotationmanagement.domain.mapper.EmployeeMapper;
import com.api.quotationmanagement.domain.repository.EmployeeRepository;
import com.api.quotationmanagement.domain.service.EmployeeService;
import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Create employee to persist in database.
     *
     * @param employeeRequestVo Employee Data to be inserted.
     */
    @Override
    public EmployeeResponseVo createEmployee(EmployeeRequestVo employeeRequestVo) {
        Employee employee = employeeMapper.requestVoToEmployee(employeeRequestVo);

        employeeRepository.save(employee);
        log.info("Employee saved successfully.");
        return employeeMapper.employeeToResponseVo(employee);
    }

    /**
     * Get Employee by ID from database.
     *
     * @param employeeID is the id of the employee
     * @return Employee information data.
     * @throws EmployeeNotFoundException if employee not found then return error message to user.
     */
    @Override
    public EmployeeResponseVo getByEmployeeId(Long employeeID) {
        Optional<Employee> employeeIdResult = employeeRepository.findById(employeeID);
        if(employeeIdResult.isPresent()) {
            log.info("Found employee {}", employeeIdResult.get());
            return employeeMapper.employeeToResponseVo(employeeIdResult.get());
        }
        String message = "Employee not found in the database.";
        log.error(message);
        throw new EmployeeNotFoundException(message);
    }

    /**
     * Get all stocks from database.
     *
     * @return all Stocks information data.
     */
    @Override
    public List<EmployeeResponseVo> getAllEmployees() {
        return employeeMapper.employeeListToResponseListVo(new ArrayList<>(employeeRepository.findAll()));
    }

    @Override
    public void updateEmployee(Long employeeId, EmployeeRequestVo employeeRequestVo) {
        employeeRepository.update(employeeId, employeeMapper.requestVoToEmployee(employeeRequestVo));
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        log.info("Deleting employee id {}", employeeId);
        employeeRepository.deleteById(employeeId);
        log.info("Employee id {} deleted successfully.", employeeId);
    }
}
