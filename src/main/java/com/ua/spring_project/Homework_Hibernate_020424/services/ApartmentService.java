package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.dto.ApartmentDTO;
import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApartmentService extends CRUDInterface<Apartment> {
    Page<Apartment> findAll(Pageable pageable);

    Page<Apartment> findByApartmentFilters(ApartmentDTO apartmentDTO);
}
