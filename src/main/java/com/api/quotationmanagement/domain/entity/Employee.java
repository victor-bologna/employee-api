package com.api.quotationmanagement.domain.entity;

import lombok.Data;

@Data
public class Employee {

    private Long id;
    private String name;
    private String role;
    private Double salary;
    private String telephone;
    private String address;
}
