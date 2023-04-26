package com.api.quotationmanagement.domain.mapper;

import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.request.EmployeeRequest;
import com.api.quotationmanagement.domain.response.EmployeeResponse;
import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-26T15:56:09-0300",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeRequestVo requestToVo(EmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        EmployeeRequestVo employeeRequestVo = new EmployeeRequestVo();

        employeeRequestVo.setName( employeeRequest.getName() );
        employeeRequestVo.setRole( employeeRequest.getRole() );
        employeeRequestVo.setSalary( employeeRequest.getSalary() );
        employeeRequestVo.setTelephone( employeeRequest.getTelephone() );
        employeeRequestVo.setAddress( employeeRequest.getAddress() );

        return employeeRequestVo;
    }

    @Override
    public EmployeeResponseVo employeeToResponseVo(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseVo.EmployeeResponseVoBuilder employeeResponseVo = EmployeeResponseVo.builder();

        employeeResponseVo.id( employee.getId() );
        employeeResponseVo.name( employee.getName() );
        employeeResponseVo.role( employee.getRole() );
        employeeResponseVo.salary( employee.getSalary() );
        employeeResponseVo.telephone( employee.getTelephone() );
        employeeResponseVo.address( employee.getAddress() );

        return employeeResponseVo.build();
    }

    @Override
    public EmployeeResponse responseVoToResponse(EmployeeResponseVo employeeResponseVo) {
        if ( employeeResponseVo == null ) {
            return null;
        }

        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setId( employeeResponseVo.getId() );
        employeeResponse.setName( employeeResponseVo.getName() );
        employeeResponse.setRole( employeeResponseVo.getRole() );
        employeeResponse.setSalary( employeeResponseVo.getSalary() );
        employeeResponse.setTelephone( employeeResponseVo.getTelephone() );
        employeeResponse.setAddress( employeeResponseVo.getAddress() );

        return employeeResponse;
    }

    @Override
    public List<EmployeeResponse> responseListVoToResponseList(List<EmployeeResponseVo> employeeResponseVoList) {
        if ( employeeResponseVoList == null ) {
            return null;
        }

        List<EmployeeResponse> list = new ArrayList<EmployeeResponse>( employeeResponseVoList.size() );
        for ( EmployeeResponseVo employeeResponseVo : employeeResponseVoList ) {
            list.add( responseVoToResponse( employeeResponseVo ) );
        }

        return list;
    }

    @Override
    public Employee requestVoToEmployee(EmployeeRequestVo employeeRequestVo) {
        if ( employeeRequestVo == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setName( employeeRequestVo.getName() );
        employee.setRole( employeeRequestVo.getRole() );
        employee.setSalary( employeeRequestVo.getSalary() );
        employee.setTelephone( employeeRequestVo.getTelephone() );
        employee.setAddress( employeeRequestVo.getAddress() );

        return employee;
    }

    @Override
    public List<EmployeeResponseVo> employeeListToResponseListVo(List<Employee> employeeList) {
        if ( employeeList == null ) {
            return null;
        }

        List<EmployeeResponseVo> list = new ArrayList<EmployeeResponseVo>( employeeList.size() );
        for ( Employee employee : employeeList ) {
            list.add( employeeToResponseVo( employee ) );
        }

        return list;
    }
}
