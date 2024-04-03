package com.ua.spring_project.Homework_Hibernate_020424.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "apartments")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"landlord"})
public class Apartment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rooms_count")
    private int roomsCount;

    @Column(name = "district")
    private String district;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "landlord_id", nullable = false)
    Landlord landlord;

    @Override
    public String toString() {
        return " Record ID: " + id + "\n" +
                " Rooms Count: " + roomsCount + "\n" +
                " District: " + district + "\n" +
                " Price: " + price + "$\n" +
                " Landlord: " + landlord.getPersonalInfo().getFirstName() +
                " " + landlord.getPersonalInfo().getLastName() + " (" +
                landlord.getPersonalInfo().getContactPhone() + ")\n" +
                "=".repeat(25) + "\n";
    }
}
