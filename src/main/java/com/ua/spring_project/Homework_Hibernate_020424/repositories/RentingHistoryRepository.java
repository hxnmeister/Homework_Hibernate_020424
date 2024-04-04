package com.ua.spring_project.Homework_Hibernate_020424.repositories;

import com.ua.spring_project.Homework_Hibernate_020424.models.RentingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RentingHistoryRepository extends JpaRepository<RentingHistory, Long> {
}
