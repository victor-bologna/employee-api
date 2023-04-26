package com.api.quotationmanagement.domain.mother;

import com.api.quotationmanagement.domain.entity.Employee;
import com.api.quotationmanagement.domain.request.EmployeeRequest;
import com.api.quotationmanagement.domain.response.EmployeeResponse;
import com.api.quotationmanagement.domain.vo.EmployeeRequestVo;
import com.api.quotationmanagement.domain.vo.EmployeeResponseVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMother {

    private static final Long ID = 1L;
    private static final String NAME = "Joao Ferreira da Silva";
    private static final String ROLE = "Gerente";
    private static final Double SALARY = 1200.00;
    private static final String TELEPHONE = "+55 (11) 9 5555-4020";
    private static final String ADDRESS = "Rua Costa e Silva, 555 - Jardim Triangulo - Poa - SP";

    public static EmployeeRequest getEmployeeRequest() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName(NAME);
        employeeRequest.setRole(ROLE);
        employeeRequest.setAddress(ADDRESS);
        employeeRequest.setSalary(SALARY);
        employeeRequest.setTelephone(TELEPHONE);
        return employeeRequest;
    }

    public static EmployeeRequestVo getEmployeeRequestVo() {
        EmployeeRequestVo employeeRequestVo = new EmployeeRequestVo();
        employeeRequestVo.setName(NAME);
        employeeRequestVo.setRole(ROLE);
        employeeRequestVo.setAddress(ADDRESS);
        employeeRequestVo.setSalary(SALARY);
        employeeRequestVo.setTelephone(TELEPHONE);
        return employeeRequestVo;
    }

    public static EmployeeResponseVo getEmployeeResponseVo() {
        EmployeeResponseVo employeeResponseVo = new EmployeeResponseVo();
        employeeResponseVo.setId(ID);
        employeeResponseVo.setName(NAME);
        employeeResponseVo.setRole(ROLE);
        employeeResponseVo.setAddress(ADDRESS);
        employeeResponseVo.setSalary(SALARY);
        employeeResponseVo.setTelephone(TELEPHONE);
        return employeeResponseVo;
    }

    public static EmployeeResponse getEmployeeResponse() {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(ID);
        employeeResponse.setName(NAME);
        employeeResponse.setRole(ROLE);
        employeeResponse.setAddress(ADDRESS);
        employeeResponse.setSalary(SALARY);
        employeeResponse.setTelephone(TELEPHONE);
        return employeeResponse;
    }

    public static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(ID);
        employee.setName(NAME);
        employee.setRole(ROLE);
        employee.setAddress(ADDRESS);
        employee.setSalary(SALARY);
        employee.setTelephone(TELEPHONE);
        return employee;
    }

    public static Map<Long, Employee> getDatabase() {
        Map<Long, Employee> employeeList = new HashMap<>();
        Employee employee = new Employee();

        employee.setId(1L);
        employee.setName("Andre dos Santos");
        employee.setAddress("Rua Tapucai, 23 - São Paulo - SP");
        employee.setSalary(15000.00);
        employee.setRole("Gerente");
        employee.setTelephone("+55 (11) 9 2621-0006");
        employeeList.put(1L, employee);

        employee.setId(2L);
        employee.setName("Pedro Cipoli");
        employee.setAddress("Rua dos Anjos, 23 - Santo André - SP");
        employee.setSalary(12000.00);
        employee.setRole("Apresentador");
        employee.setTelephone("+55 (11) 9 5783-3006");
        employeeList.put(2L, employee);
        return employeeList;
    }
}
