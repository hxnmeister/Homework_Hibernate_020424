package com.ua.spring_project.Homework_Hibernate_020424.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
public class ApartmentDTO {
    private int roomsCount;
    private String district;
    private BigDecimal price;
    private String landlordFirstName;
    private String landlordLastName;
    private Integer page = 0;
    private Integer pageSize = 5;
}
