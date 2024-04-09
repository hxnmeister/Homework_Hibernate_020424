package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.dto.ApartmentDTO;
import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface ApartmentService extends CRUDInterface<Apartment> {
    Page<Apartment> findAll(Pageable pageable);

    Optional<Apartment> findApartmentById(Long id);

    Page<Apartment> findByApartmentFilters(ApartmentDTO apartmentDTO);

    BigDecimal getAvgPrice();

    BigDecimal getMaxPrice();

    BigDecimal getMinPrice();
}
