package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.models.Landlord;
import com.ua.spring_project.Homework_Hibernate_020424.repositories.LandlordRepository;
import com.ua.spring_project.Homework_Hibernate_020424.services.LandlordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandlordServiceImp implements LandlordService {
    private final LandlordRepository landlordRepository;

    @Override
    public Landlord save(Landlord item) {
        return landlordRepository.save(item);
    }

    @Override
    public Landlord update(Landlord item) {
        return landlordRepository.save(item);
    }

    @Override
    public void delete(Landlord item) {
        landlordRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        landlordRepository.deleteAll();
    }

    @Override
    public List<Landlord> findAll() {
        return landlordRepository.findAll();
    }

    @Override
    public List<Landlord> saveMany(List<Landlord> itemsList) {
        return landlordRepository.saveAll(itemsList);
    }
}
