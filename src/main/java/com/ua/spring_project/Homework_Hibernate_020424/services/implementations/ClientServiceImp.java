package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import com.ua.spring_project.Homework_Hibernate_020424.repositories.ClientRepository;
import com.ua.spring_project.Homework_Hibernate_020424.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client save(Client item) {
        return clientRepository.save(item);
    }

    @Override
    public Client update(Client item) {
        return clientRepository.save(item);
    }

    @Override
    public void delete(Client item) {
        clientRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> saveMany(List<Client> itemsList) {
        return clientRepository.saveAll(itemsList);
    }

    @Override
    public Optional<Client> findClientById(long id) {
        return clientRepository.findClientById(id);
    }

    @Override
    public Optional<List<Client>> findClientsByFirstNameAndLastName(String firstName, String lastName) {
        return clientRepository.findClientsByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Optional<List<Client>> findClientsByContactPhone(String contactPhone) {
        return clientRepository.findClientsByContactPhone(contactPhone);
    }

    @Override
    public Optional<List<Client>> findClientsByApartmentId(long apartmentId) {
        return clientRepository.findClientsByApartmentId(apartmentId);
    }

    @Override
    public Optional<List<Client>> findClientsByRentingDateBeginDuringMonth() {
        return clientRepository.findClientsByRentingDateBeginDuringMonth();
    }

    @Override
    public Optional<List<Client>> findClientsByRentingDateEndDuringMonth() {
        return clientRepository.findClientsByRentingDateEndDuringMonth();
    }

    @Override
    public Optional<List<Client>> findClientsByRentingDateRangeLessThanMonth() {
        return clientRepository.findClientsByRentingDateRangeLessThanMonth();
    }

    @Override
    public Optional<List<Client>> findClientsByRentingDateMoreThanYear() {
        return clientRepository.findClientsByRentingDateMoreThanYear();
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }
}
