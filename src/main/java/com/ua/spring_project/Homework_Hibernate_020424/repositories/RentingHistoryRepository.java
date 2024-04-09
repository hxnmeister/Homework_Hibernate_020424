package com.ua.spring_project.Homework_Hibernate_020424.repositories;

import com.ua.spring_project.Homework_Hibernate_020424.models.RentingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentingHistoryRepository extends PagingAndSortingRepository<RentingHistory, Long>,
        JpaRepository<RentingHistory, Long> {
    Optional<RentingHistory> findRentingHistoryById(Long id);
}
