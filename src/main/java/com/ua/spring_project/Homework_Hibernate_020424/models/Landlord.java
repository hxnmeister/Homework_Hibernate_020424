package com.ua.spring_project.Homework_Hibernate_020424.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "landlords")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"apartments"})
public class Landlord {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalInfo personalInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "landlord")
    private List<Apartment> apartments;

    @Override
    public String toString() {
        return " ID: " + id + "\n" +
                personalInfo;
    }
}
