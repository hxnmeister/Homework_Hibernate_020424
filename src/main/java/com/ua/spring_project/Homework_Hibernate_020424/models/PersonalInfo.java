package com.ua.spring_project.Homework_Hibernate_020424.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Override
    public String toString() {
        return " Name: " + firstName + " " + lastName + "\n" +
                " Contact Phone: " + contactPhone + "\n" +
                "=".repeat(25) + "\n";
    }
}
