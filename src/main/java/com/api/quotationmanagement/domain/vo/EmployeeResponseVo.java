package com.api.quotationmanagement.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseVo {

    private Long id;
    private String name;
    private String role;
    private Double salary;
    private String telephone;
    private String address;
}
