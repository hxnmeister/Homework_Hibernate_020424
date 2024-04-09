package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import com.ua.spring_project.Homework_Hibernate_020424.models.PersonalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientService extends CRUDInterface<Client> {
    Optional<Client> findClientById(long id);

    Page<Client> findAll(Pageable pageable);

    Optional<List<Client>> findClientsByFirstNameAndLastName(String firstName, String lastName);

    Optional<List<Client>> findClientsByContactPhone(String contactPhone);

    Optional<List<Client>> findClientsByApartmentId(long apartmentId);

    Optional<List<Client>> findClientsByRentingDateBeginDuringMonth();

    Optional<List<Client>> findClientsByRentingDateEndDuringMonth();

    Optional<List<Client>> findClientsByRentingDateRangeLessThanMonth();

    Optional<List<Client>> findClientsByRentingDateMoreThanYear();

}
