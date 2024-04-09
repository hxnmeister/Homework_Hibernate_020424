package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.models.RentingHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RentingHistoryService extends CRUDInterface<RentingHistory> {
    Page<RentingHistory> findAll(Pageable pageable);

    Optional<RentingHistory> findRentingHistoryById(Long id);
}
