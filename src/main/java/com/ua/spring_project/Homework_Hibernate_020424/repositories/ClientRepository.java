package com.ua.spring_project.Homework_Hibernate_020424.repositories;

import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaRepository<Client, Long> {
    String SELECT_ALL_CLIENTS_BY_FIRST_AND_LAST_NAMES = """
        SELECT *
        FROM clients
        WHERE first_name=:firstName AND last_name=:lastName
    """;

    String SELECT_ALL_CLIENTS_BY_CONTACT_PHONE = """
        SELECT *
        FROM clients
        WHERE contact_phone=:contactPhone
    """;

    String SELECT_ALL_CLIENTS_BY_APARTMENT_ID = """
        SELECT c.*
        FROM clients c JOIN requests_for_renting_apartment rra
        ON c.id=rra.client_id
        JOIN apartments a
        ON a.id=rra.apartment_id
        WHERE rra.apartment_id=:apartmentId
    """;

    String SELECT_ALL_CLIENTS_BY_RENTING_DATE_DURING_MONTH = """
        SELECT c.*
        FROM renting_history rh JOIN clients c
        ON c.id=rh.client_id
        WHERE rh.rent_begin BETWEEN CURRENT_DATE - INTERVAL '1 MONTH' AND CURRENT_DATE
    """;

    String SELECT_ALL_CLIENTS_BY_RENTING_END_DURING_MONTH = """
        SELECT c.*
        FROM renting_history rh JOIN clients c
        ON c.id=rh.client_id
        WHERE rh.rent_end BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '1 MONTH'
    """;

    String SELECT_ALL_CLIENTS_BY_RENTING_DATE_RANGE_LESS_THAN_MONTH = """
        SELECT c.*
        FROM renting_history rh JOIN clients c
        ON c.id=rh.client_id
        WHERE (rh.rent_end - rh.rent_begin) < 30
    """;

    String SELECT_ALL_CLIENTS_BY_RENTING_DATE_MORE_THAN_YEAR = """
        SELECT c.*
        FROM renting_history rh JOIN clients c
        ON c.id=rh.client_id
        WHERE (EXTRACT(YEAR FROM rh.rent_end) - EXTRACT(YEAR FROM rh.rent_begin)) > 1
    """;

    Optional<Client> findClientById(Long id);

    @Query(value = SELECT_ALL_CLIENTS_BY_FIRST_AND_LAST_NAMES, nativeQuery = true)
    Optional<List<Client>> findClientsByFirstNameAndLastName(@Param("firstName") String firstName,
                                                             @Param("lastName") String lastName);

    @Query(value = SELECT_ALL_CLIENTS_BY_CONTACT_PHONE, nativeQuery = true)
    Optional<List<Client>> findClientsByContactPhone(@Param("contactPhone") String contactPhone);

    @Query(value = SELECT_ALL_CLIENTS_BY_APARTMENT_ID, nativeQuery = true)
    Optional<List<Client>> findClientsByApartmentId(@Param("apartmentId") long apartmentId);

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_DATE_DURING_MONTH, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateBeginDuringMonth();

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_END_DURING_MONTH, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateEndDuringMonth();

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_DATE_RANGE_LESS_THAN_MONTH, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateRangeLessThanMonth();

    @Query(value = SELECT_ALL_CLIENTS_BY_RENTING_DATE_MORE_THAN_YEAR, nativeQuery = true)
    Optional<List<Client>> findClientsByRentingDateMoreThanYear();

}
