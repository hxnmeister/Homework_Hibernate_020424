package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.models.RentingHistory;
import com.ua.spring_project.Homework_Hibernate_020424.repositories.RentingHistoryRepository;
import com.ua.spring_project.Homework_Hibernate_020424.services.RentingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentingHistoryServiceImp implements RentingHistoryService {
    private final RentingHistoryRepository rentingHistoryRepository;

    @Override
    public RentingHistory save(RentingHistory item) {
        return rentingHistoryRepository.save(item);
    }

    @Override
    public RentingHistory update(RentingHistory item) {
        return rentingHistoryRepository.save(item);
    }

    @Override
    public void delete(RentingHistory item) {
        rentingHistoryRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        rentingHistoryRepository.deleteAll();
    }

    @Override
    public List<RentingHistory> findAll() {
        return rentingHistoryRepository.findAll();
    }

    @Override
    public List<RentingHistory> saveMany(List<RentingHistory> itemsList) {
        return rentingHistoryRepository.saveAll(itemsList);
    }

    @Override
    public Page<RentingHistory> findAll(Pageable pageable) {
        return rentingHistoryRepository.findAll(pageable);
    }

    @Override
    public Optional<RentingHistory> findRentingHistoryById(Long id) {
        return rentingHistoryRepository.findRentingHistoryById(id);
    }
}
