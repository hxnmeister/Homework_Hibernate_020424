package com.ua.spring_project.Homework_Hibernate_020424.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class RentInitializer {
    private final RentDBInitializer rentDBInitializer;

    public void initDB() {
        try {
            rentDBInitializer.deleteAllRows();

            rentDBInitializer.createRandomLandlords(5);
            rentDBInitializer.createRandomClients(5);
            rentDBInitializer.createRandomApartments(4, 6, 1000.0);
            rentDBInitializer.createRandomRentingHistory(7);
            rentDBInitializer.createRandomRequestForRentingApartment(5);
        }
        catch (RuntimeException | IOException e) {
            log.warn(e.getMessage());
        }
    }
}
