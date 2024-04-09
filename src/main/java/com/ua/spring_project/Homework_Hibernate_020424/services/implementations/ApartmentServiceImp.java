package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.dto.ApartmentDTO;
import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import com.ua.spring_project.Homework_Hibernate_020424.repositories.ApartmentRepository;
import com.ua.spring_project.Homework_Hibernate_020424.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Apartment> findAll(Pageable pageable) {
        return apartmentRepository.findAll(pageable);
    }

    @Override
    public Page<Apartment> findByApartmentFilters(ApartmentDTO apartmentDTO) {
        return apartmentRepository.findByApartmentFilters(
                apartmentDTO.getRoomsCount(),
                apartmentDTO.getDistrict(),
                apartmentDTO.getPrice(),
                apartmentDTO.getLandlordFirstName(),
                apartmentDTO.getLandlordLastName(),
                PageRequest.of(apartmentDTO.getPage(), apartmentDTO.getPageSize())
        );
    }

    @Override
    public Optional<Apartment> findApartmentById(Long id) {
        return apartmentRepository.findApartmentById(id);
    }

    @Override
    public BigDecimal getAvgPrice() {
        return apartmentRepository.getAvgPrice();
    }

    @Override
    public BigDecimal getMaxPrice() {
        return apartmentRepository.getMaxPrice();
    }

    @Override
    public BigDecimal getMinPrice() {
        return apartmentRepository.getMinPrice();
    }
}
