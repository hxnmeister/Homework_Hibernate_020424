package com.ua.spring_project.Homework_Hibernate_020424.utils;

import com.ua.spring_project.Homework_Hibernate_020424.models.*;
import com.ua.spring_project.Homework_Hibernate_020424.services.RentDBInitService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@Service
@RequiredArgsConstructor
public class RentDBInitializer {
    @Value("${data.first_names}")
    private String pathToFirstNames;

    @Value("${data.last_names}")
    private String pathToLastNames;

    private static List<String> firstNames;
    private static List<String> lastNames;
    private final TxtFileReader txtFileReader;
    private final RentDBInitService rentDBInitService;

    @PostConstruct
    public void init() {
        try {
            txtFileReader.setFileName(pathToFirstNames);
            firstNames = txtFileReader.readFile();
            txtFileReader.setFileName(pathToLastNames);
            lastNames = txtFileReader.readFile();
        }
        catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    public void deleteAllRows() {
        rentDBInitService.deleteAllRowsInDB();
        log.debug("All rows was erased!");
    }

    public void createRandomClients(int countOfRecords) throws IOException {
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            clients.add(Client
                    .builder()
                    .personalInfo(createRandomPersonalInfo())
                    .build());
        }

        rentDBInitService.saveClients(clients);
    }

    public void createRandomLandlords(int countOfRecords) throws IOException {
        List<Landlord> landlords = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            landlords.add(Landlord
                    .builder()
                    .personalInfo(createRandomPersonalInfo())
                    .build());
        }

        rentDBInitService.saveLandlords(landlords);
    }

    public void createRandomApartments(int maxRooms, int countOfRecords, double maxPrice) {
        final int MIN_ROOMS = 1;
        final BigDecimal MAX_PRICE = BigDecimal.valueOf(maxPrice);
        final BigDecimal MIN_PRICE = BigDecimal.valueOf(20.5);
        List<Landlord> landlords = rentDBInitService.findAllLandlords();
        List<Apartment> apartments = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            apartments.add(Apartment
                    .builder()
                    .roomsCount(ThreadLocalRandom.current().nextInt(MIN_ROOMS ,maxRooms))
                    .district("DISTRICT #" + ThreadLocalRandom.current().nextInt(100))
                    .price(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble()).multiply(MAX_PRICE.subtract(MIN_PRICE)))
                    .landlord(landlords.get(ThreadLocalRandom.current().nextInt(landlords.size())))
                    .build());
        }

        rentDBInitService.saveApartments(apartments);
    }

    public void createRandomRentingHistory() {
        List<Client> clients = rentDBInitService.findAllClients();
        List<Apartment> apartments = rentDBInitService.findAllApartments();
        List<RentingHistory> rentingHistories = new ArrayList<>();

        rentingHistories.add(RentingHistory
                .builder()
                .rentBegin(Date.valueOf(LocalDate.now()))
                .rentEnd(Date.valueOf(LocalDate.now().plusMonths(1)))
                .client(clients.remove(ThreadLocalRandom.current().nextInt(clients.size())))
                .apartment(apartments.remove(ThreadLocalRandom.current().nextInt(apartments.size())))
                .build());

        for (int i = 0; i < clients.size(); i++) {
            int randomDays = ThreadLocalRandom.current().nextInt(10, 365);
            LocalDate beginDate = getRandomDate();
            LocalDate endDate = beginDate.plusDays(randomDays);

            rentingHistories.add(RentingHistory
                    .builder()
                    .rentBegin(Date.valueOf(beginDate))
                    .rentEnd(Date.valueOf(endDate))
                    .client(clients.remove(ThreadLocalRandom.current().nextInt(clients.size())))
                    .apartment(apartments.remove(ThreadLocalRandom.current().nextInt(apartments.size())))
                    .build());
        }

        rentDBInitService.saveRentingHistories(rentingHistories);
    }

    public void createRandomRequestForRentingApartment(int countOfRecords) {
        List<Client> clients = rentDBInitService.findAllClients();
        List<Apartment> apartments = rentDBInitService.findAllApartments();
        List<RequestForRentingApartment> requestsForRentingApartment = new ArrayList<>();

        for (int i = 0; i < countOfRecords; i++) {
            requestsForRentingApartment.add(RequestForRentingApartment
                    .builder()
                    .client(clients.remove(ThreadLocalRandom.current().nextInt(clients.size())))
                    .apartment(apartments.remove(ThreadLocalRandom.current().nextInt(apartments.size())))
                    .build());
        }

        rentDBInitService.saveRequestsForRentingApartment(requestsForRentingApartment);
    }

    private LocalDate getRandomDate() {
        int year = LocalDate.now().getYear();
        int dayOfYear = ThreadLocalRandom.current().nextInt(1, 366);

        return LocalDate.ofYearDay(year, dayOfYear);
    }

    private static PersonalInfo createRandomPersonalInfo() {
        return PersonalInfo
                .builder()
                .firstName(firstNames.get(ThreadLocalRandom.current().nextInt(firstNames.size())))
                .lastName(lastNames.get(ThreadLocalRandom.current().nextInt(lastNames.size())))
                .contactPhone("PhoneNumber" + ThreadLocalRandom.current().nextInt(99))
                .build();
    }
}
