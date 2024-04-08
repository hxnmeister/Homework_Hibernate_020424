package com.ua.spring_project.Homework_Hibernate_020424.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String contactPhone;
}
