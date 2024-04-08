package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.models.Landlord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LandlordService extends CRUDInterface<Landlord> {
    Page<Landlord> findAll(Pageable pageable);
}
