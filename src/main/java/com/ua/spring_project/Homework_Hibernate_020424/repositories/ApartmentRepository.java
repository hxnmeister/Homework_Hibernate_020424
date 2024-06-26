package com.ua.spring_project.Homework_Hibernate_020424.repositories;

import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@Transactional
public interface ApartmentRepository extends PagingAndSortingRepository<Apartment, Long>, JpaRepository<Apartment, Long> {
    String SELECT_APARTMENTS_BY_FILTERS = """
        SELECT ap.*
        FROM apartments ap JOIN landlords lan
        ON lan.id=ap.landlord_id
        WHERE (ap.rooms_count=:roomsCount OR :roomsCount IS NULL) AND
        (ap.district=:district OR :district IS NULL) AND
        (ap.price=CAST(:price AS MONEY) OR :price IS NULL) AND
        (lan.first_name=:landlordFN OR :landlordFN IS NULL) AND
        (lan.last_name=:landlordLN OR :landlordLN IS NULL)
    """;

    String SELECT_AVG_PRICE = """
        SELECT AVG(CAST(price AS NUMERIC))
        FROM apartments
    """;

    String SELECT_MAX_PRICE = """
        SELECT MAX(price)
        FROM apartments
    """;

    String SELECT_MIN_PRICE = """
        SELECT MIN(price)
        FROM apartments
    """;

    Optional<Apartment> findApartmentById(Long id);

    @Query(value = SELECT_APARTMENTS_BY_FILTERS, nativeQuery = true)
    Page<Apartment> findByApartmentFilters(@Param("roomsCount") int roomsCount,
                                           @Param("district") String district,
                                           @Param("price") BigDecimal price,
                                           @Param("landlordFN") String landlordFirstName,
                                           @Param("landlordLN") String landlordLastName,
                                           Pageable pageable);

    @Query(value = SELECT_AVG_PRICE, nativeQuery = true)
    BigDecimal getAvgPrice();

    @Query(value = SELECT_MAX_PRICE, nativeQuery = true)
    BigDecimal getMaxPrice();

    @Query(value = SELECT_MIN_PRICE, nativeQuery = true)
    BigDecimal getMinPrice();
}
