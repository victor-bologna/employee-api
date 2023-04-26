package com.api.quotationmanagement.domain.service.impl;

import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.exception.EmployeeNotFoundException;
import com.api.quotationmanagement.domain.mapper.EmployeeMapper;
import com.api.quotationmanagement.domain.mother.EmployeeMother;
import com.api.quotationmanagement.domain.repository.EmployeeRepository;
import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @DisplayName("Service Test to create Employee")
    @Test
    void givenEmployeeRequestVoWhenCreateEmployeeThenReturnEmployeeResponseVo() {
        EmployeeRequestVo employeeRequestVo = EmployeeMother.getEmployeeRequestVo();
        Employee employee = EmployeeMother.getEmployee();
        EmployeeResponseVo employeeResponseVo = EmployeeMother.getEmployeeResponseVo();

        given(employeeMapper.requestVoToEmployee(employeeRequestVo)).willReturn(employee);
        given(employeeRepository.save(employee)).willReturn(employee);
        given(employeeMapper.employeeToResponseVo(employee)).willReturn(employeeResponseVo);

        EmployeeResponseVo result = employeeService.createEmployee(employeeRequestVo);

        then(result).usingRecursiveComparison().isEqualTo(employeeResponseVo);

        verify(employeeMapper).requestVoToEmployee(employeeRequestVo);
        verify(employeeRepository).save(employee);
        verify(employeeMapper).employeeToResponseVo(employee);
    }

    @DisplayName("Service Test to get Employee by Id")
    @Test
    void givenIdWhenGetByEmployeeIdThenReturnEmployeeResponseVo() {
        Long employeeId = 10L;
        Optional<Employee> employeeList = Optional.of(EmployeeMother.getEmployee());
        EmployeeResponseVo employeeResponseVoList = EmployeeMother.getEmployeeResponseVo();

        given(employeeRepository.findById(employeeId)).willReturn(employeeList);
        given(employeeMapper.employeeToResponseVo(employeeList.get())).willReturn(employeeResponseVoList);

        EmployeeResponseVo result = employeeService.getByEmployeeId(employeeId);

        then(result).usingRecursiveComparison().isEqualTo(employeeResponseVoList);

        verify(employeeRepository).findById(employeeId);
        verify(employeeMapper).employeeToResponseVo(employeeList.get());
    }

    @DisplayName("Service Test to get Employee by Id not finding in database")
    @Test
    void givenWrongIdWhenGetByEmployeeIdThenThrowEmployeeNotFoundException() {
        Long employeeId = 10L;

        given(employeeRepository.findById(employeeId)).willReturn(Optional.empty());

        EmployeeNotFoundException employeeNotFoundException =
                Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.getByEmployeeId(employeeId));

        then(employeeNotFoundException.getMessage()).isEqualTo("Employee not found in the database.");

        verify(employeeRepository).findById(employeeId);
    }

    @DisplayName("Service Test to get all Employee")
    @Test
    void givenNothingWhenGetAllEmployeesThenReturnAllEmployees() {
        List<Employee> employees = Collections.singletonList(EmployeeMother.getEmployee());
        List<EmployeeResponseVo> employeeResponseVo = Collections.singletonList(EmployeeMother.getEmployeeResponseVo());

        given(employeeRepository.findAll()).willReturn(employees);
        given(employeeMapper.employeeListToResponseListVo(employees)).willReturn(employeeResponseVo);

        List<EmployeeResponseVo> result = employeeService.getAllEmployees();

        then(result).usingRecursiveComparison().isEqualTo(employeeResponseVo);

        verify(employeeRepository).findAll();
        verify(employeeMapper).employeeListToResponseListVo(employees);
    }

    @DisplayName("Service Test to update an Employee by Id")
    @Test
    void givenValidDataWhenUpdateEmployeeByIDThenReturnUpdatedEmployee() {
        Long employeeId = 1L;
        Employee employee = EmployeeMother.getEmployee();
        EmployeeRequestVo employeeRequestVo = EmployeeMother.getEmployeeRequestVo();

        given(employeeMapper.requestVoToEmployee(employeeRequestVo)).willReturn(employee);
        willDoNothing().given(employeeRepository).update(employeeId, employee);

        employeeService.updateEmployee(employeeId, employeeRequestVo);

        verify(employeeMapper).requestVoToEmployee(employeeRequestVo);
        verify(employeeRepository).update(employeeId, employee);
    }

    @DisplayName("Service Test to Delete Employee by ID")
    @Test
    void givenEmployeeIDWhenDeleteEmployeesThenReturnNothing() {
        Long employeeId = 1L;

        willDoNothing().given(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository).deleteById(employeeId);
    }
}