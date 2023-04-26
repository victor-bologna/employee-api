package com.api.quotationmanagement.domain.mapper;

import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.mother.EmployeeMother;
import com.api.quotationmanagement.domain.request.EmployeeRequest;
import com.api.quotationmanagement.domain.response.EmployeeResponse;
import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

class EmployeeMapperTest {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @DisplayName("Mapper Test EmployeeRequest to EmployeeRequestVo")
    @Test
    void requestToRequestVo() {
        EmployeeRequest employeeRequest = EmployeeMother.getEmployeeRequest();
        EmployeeRequestVo employeeRequestVo = EmployeeMother.getEmployeeRequestVo();

        EmployeeRequestVo result = employeeMapper.requestToVo(employeeRequest);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(employeeRequestVo);
    }

    @DisplayName("Mapper Test Employee to EmployeeResponseVo")
    @Test
    void employeeToEmployeeResponseVo() {
        Employee employee = EmployeeMother.getEmployee();
        EmployeeResponseVo employeeResponseVo = EmployeeMother.getEmployeeResponseVo();

        EmployeeResponseVo result = employeeMapper.employeeToResponseVo(employee);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(employeeResponseVo);
    }

    @DisplayName("Mapper Test EmployeeResponseVo to EmployeeResponse")
    @Test
    void responseVoToResponse() {
        EmployeeResponseVo employeeResponseVo = EmployeeMother.getEmployeeResponseVo();
        EmployeeResponse employeeResponse = EmployeeMother.getEmployeeResponse();

        EmployeeResponse result = employeeMapper.responseVoToResponse(employeeResponseVo);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(employeeResponse);
    }

    @DisplayName("Mapper Test List of EmployeeResponseVos to List of EmployeeResponses")
    @Test
    void responseListVoToResponseList() {
        List<EmployeeResponseVo> employeeResponseVoList = Collections.singletonList(EmployeeMother.getEmployeeResponseVo());
        List<EmployeeResponse> employeeResponseList = Collections.singletonList(EmployeeMother.getEmployeeResponse());

        List<EmployeeResponse> result = employeeMapper.responseListVoToResponseList(employeeResponseVoList);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(employeeResponseList);
    }

    @DisplayName("Mapper Test EmployeeRequestVo to Employee")
    @Test
    void requestVoToEmployee() {
        EmployeeRequestVo employeeRequestVo = EmployeeMother.getEmployeeRequestVo();
        Employee employee = EmployeeMother.getEmployee();

        Employee result = employeeMapper.requestVoToEmployee(employeeRequestVo);

        BDDAssertions.then(result).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(employee);
    }

    @DisplayName("Mapper Test List of Employees to List of EmployeeResponseVos")
    @Test
    void employeeListToResponseListVo() {
        List<Employee> employeeList = Collections.singletonList(EmployeeMother.getEmployee());
        List<EmployeeResponseVo> employeeResponseVoList = Collections.singletonList(EmployeeMother.getEmployeeResponseVo());

        List<EmployeeResponseVo> result = employeeMapper.employeeListToResponseListVo(employeeList);

        BDDAssertions.then(result).usingRecursiveComparison().isEqualTo(employeeResponseVoList);
    }
}