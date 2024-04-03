package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.models.*;

import java.util.List;

public interface RentDBInitService {
    void deleteAllRowsInDB();

    List<Client> findAllClients();
    List<Landlord> findAllLandlords();
    List<Apartment> findAllApartments();
    List<RentingHistory> findAllRentingHistories();
    List<RequestForRentingApartment> findAllRequestsForRentingApartment();

    void saveClients(List<Client> clients);
    void saveLandlords(List<Landlord> landlords);
    void saveApartments(List<Apartment> apartments);
    void saveRentingHistories(List<RentingHistory> rentingHistories);
    void saveRequestsForRentingApartment(List<RequestForRentingApartment> requestsForRentingApartment);
}
