package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import com.ua.spring_project.Homework_Hibernate_020424.models.PersonalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientService extends CRUDInterface<Client> {
    Client findClientById(long id);

    Page<Client> findAll(Pageable pageable);

    List<Client> findClientsByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findClientsByContactPhone(String contactPhone);

    List<Client> findClientsByApartmentId(long apartmentId);

    List<Client> findClientsByRentingDateBeginDuringMonth();

    List<Client> findClientsByRentingDateEndDuringMonth();

    List<Client> findClientsByRentingDateRangeLessThanMonth();

    List<Client> findClientsByRentingDateMoreThanYear();

}
