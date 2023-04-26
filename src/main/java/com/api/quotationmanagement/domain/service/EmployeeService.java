package com.api.quotationmanagement.domain.service;

import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseVo createEmployee(EmployeeRequestVo employeeRequestVo);

    EmployeeResponseVo getByEmployeeId(Long employeeId);

    List<EmployeeResponseVo> getAllEmployees();

    void updateEmployee(Long employeeId, EmployeeRequestVo employeeRequest);

    void deleteEmployee(Long employeeId);
}
