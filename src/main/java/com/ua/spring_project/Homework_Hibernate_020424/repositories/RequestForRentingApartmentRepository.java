package com.ua.spring_project.Homework_Hibernate_020424.repositories;

import com.ua.spring_project.Homework_Hibernate_020424.models.RequestForRentingApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RequestForRentingApartmentRepository extends JpaRepository<RequestForRentingApartment, Long> {
    boolean existsRequestForRentingApartmentByApartmentId(long apartmentId);
}
