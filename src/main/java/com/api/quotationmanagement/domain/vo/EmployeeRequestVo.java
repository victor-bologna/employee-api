package com.api.quotationmanagement.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequestVo {

    private String name;
    private String role;
    private Double salary;
    private String telephone;
    private String address;
}
