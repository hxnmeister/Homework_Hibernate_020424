package com.ua.spring_project.Homework_Hibernate_020424.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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

    public static PersonalInfo personalInfoExtractor(PersonalInfo currentPI, PersonalInfo previousPI) {
        if (currentPI.getFirstName() != null && !currentPI.getFirstName().isEmpty()) {
            previousPI.setFirstName(currentPI.getFirstName());
        }
        if (currentPI.getLastName() != null && !currentPI.getLastName().isEmpty()) {
            previousPI.setLastName(currentPI.getLastName());
        }
        if (currentPI.getContactPhone() != null && !currentPI.getContactPhone().isEmpty()) {
            previousPI.setContactPhone(currentPI.getContactPhone());
        }

        return previousPI;
    }

    @Override
    public String toString() {
        return " Name: " + firstName + " " + lastName + "\n" +
                " Contact Phone: " + contactPhone + "\n" +
                "=".repeat(25) + "\n";
    }
}
