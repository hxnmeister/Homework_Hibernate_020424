package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.models.RequestForRentingApartment;
import com.ua.spring_project.Homework_Hibernate_020424.repositories.RequestForRentingApartmentRepository;
import com.ua.spring_project.Homework_Hibernate_020424.services.RequestForRentingApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestForRentingApartmentServiceImp implements RequestForRentingApartmentService {
    private final RequestForRentingApartmentRepository requestForRentingApartmentRepository;

    @Override
    public RequestForRentingApartment save(RequestForRentingApartment item) {
        return requestForRentingApartmentRepository.save(item);
    }

    @Override
    public RequestForRentingApartment update(RequestForRentingApartment item) {
        return requestForRentingApartmentRepository.save(item);
    }

    @Override
    public void delete(RequestForRentingApartment item) {
        requestForRentingApartmentRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        requestForRentingApartmentRepository.deleteAll();
    }

    @Override
    public List<RequestForRentingApartment> findAll() {
        return requestForRentingApartmentRepository.findAll();
    }

    @Override
    public List<RequestForRentingApartment> saveMany(List<RequestForRentingApartment> itemsList) {
        return requestForRentingApartmentRepository.saveAll(itemsList);
    }

    @Override
    public boolean existsRequestForRentingApartmentByApartmentId(long apartmentId) {
        return requestForRentingApartmentRepository.existsRequestForRentingApartmentByApartmentId(apartmentId);
    }
}
