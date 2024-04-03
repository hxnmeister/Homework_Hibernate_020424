package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.models.*;
import com.ua.spring_project.Homework_Hibernate_020424.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentDBInitServiceImp implements RentDBInitService {
    private final ClientService clientService;
    private final LandlordService landlordService;
    private final ApartmentService apartmentService;
    private final RentingHistoryService rentingHistoryService;
    private final RequestForRentingApartmentService requestForRentingApartmentService;

    @Override
    public void deleteAllRowsInDB() {
        clientService.deleteAll();
        landlordService.deleteAll();
        apartmentService.deleteAll();
        rentingHistoryService.deleteAll();
        requestForRentingApartmentService.deleteAll();
    }

    @Override
    public List<Client> findAllClients() {
        return clientService.findAll();
    }

    @Override
    public List<Landlord> findAllLandlords() {
        return landlordService.findAll();
    }

    @Override
    public List<Apartment> findAllApartments() {
        return apartmentService.findAll();
    }

    @Override
    public List<RentingHistory> findAllRentingHistories() {
        return rentingHistoryService.findAll();
    }

    @Override
    public List<RequestForRentingApartment> findAllRequestsForRentingApartment() {
        return requestForRentingApartmentService.findAll();
    }

    @Override
    public void saveClients(List<Client> clients) {
        clientService.saveMany(clients);
    }

    @Override
    public void saveLandlords(List<Landlord> landlords) {
        landlordService.saveMany(landlords);
    }

    @Override
    public void saveApartments(List<Apartment> apartments) {
        apartmentService.saveMany(apartments);
    }

    @Override
    public void saveRentingHistories(List<RentingHistory> rentingHistories) {
        rentingHistoryService.saveMany(rentingHistories);
    }

    @Override
    public void saveRequestsForRentingApartment(List<RequestForRentingApartment> requestsForRentingApartment) {
        requestForRentingApartmentService.saveMany(requestsForRentingApartment);
    }
}
