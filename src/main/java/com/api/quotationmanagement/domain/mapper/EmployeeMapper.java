package com.api.quotationmanagement.domain.mapper;

import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.request.EmployeeRequest;
import com.api.quotationmanagement.domain.response.EmployeeResponse;
import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeRequestVo requestToVo(EmployeeRequest employeeRequest);

    EmployeeResponseVo employeeToResponseVo(Employee employee);

    EmployeeResponse responseVoToResponse(EmployeeResponseVo employeeResponseVo);

    List<EmployeeResponse> responseListVoToResponseList(List<EmployeeResponseVo> employeeResponseVoList);

    Employee requestVoToEmployee(EmployeeRequestVo employeeRequestVo);

    List<EmployeeResponseVo> employeeListToResponseListVo(List<Employee> employeeList);
}
