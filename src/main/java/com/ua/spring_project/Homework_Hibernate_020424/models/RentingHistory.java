package com.ua.spring_project.Homework_Hibernate_020424.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Builder
@Table(name = "renting_history")
@AllArgsConstructor
@NoArgsConstructor
public class RentingHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rent_begin")
    private Date rentBegin;

    @Column(name = "rent_end")
    private Date rentEnd;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Override
    public String toString() {
        return " Record ID: " + id + "\n" +
                " Begin Rent Date: " + rentBegin + "\n" +
                " End Rent Date: " + rentEnd + "\n" +
                " Client:\n" + client.getPersonalInfo().getFirstName() + " " +
                client.getPersonalInfo().getLastName() + "\n" +
                " Apartment:\n" +
                "  District: " + apartment.getDistrict() +
                "  Rooms Count: " + apartment.getRoomsCount();
    }
}
