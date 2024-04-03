package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import com.ua.spring_project.Homework_Hibernate_020424.repositories.ApartmentRepository;
import com.ua.spring_project.Homework_Hibernate_020424.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImp implements ApartmentService {
    private final ApartmentRepository apartmentRepository;

    @Override
    public Apartment save(Apartment item) {
        return apartmentRepository.save(item);
    }

    @Override
    public Apartment update(Apartment item) {
        return apartmentRepository.save(item);
    }

    @Override
    public void delete(Apartment item) {
        apartmentRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        apartmentRepository.deleteAll();
    }

    @Override
    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public List<Apartment> saveMany(List<Apartment> itemsList) {
        return apartmentRepository.saveAll(itemsList);
    }
}
