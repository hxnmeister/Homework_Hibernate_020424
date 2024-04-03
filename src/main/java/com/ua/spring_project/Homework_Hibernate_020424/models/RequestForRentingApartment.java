package com.ua.spring_project.Homework_Hibernate_020424.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "requests_for_renting_apartment")
@AllArgsConstructor
@NoArgsConstructor
public class RequestForRentingApartment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @Override
    public String toString() {
        return " Record ID: " + id + "\n" +
                " Client: " + client.getPersonalInfo().getFirstName() + " " +
                client.getPersonalInfo().getLastName() + "\n" +
                " Apartment:\n" +
                "  District: " + apartment.getDistrict() + "\n" +
                "  Apartment owner: " + apartment.getLandlord().getPersonalInfo().getFirstName() +
                apartment.getLandlord().getPersonalInfo().getLastName() +
                " (" + apartment.getLandlord().getPersonalInfo().getContactPhone() + ")\n" +
                "=".repeat(25) + "\n";
    }
}
