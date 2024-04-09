package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import com.ua.spring_project.Homework_Hibernate_020424.models.PersonalInfo;
import com.ua.spring_project.Homework_Hibernate_020424.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Client> clients = clientService.findAll(PageRequest.of(page, pageSize)).toList();

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping(value = "/client")
    public ResponseEntity<?> insert(@RequestBody Client client) {

        return clientService.save(client) == null ?
                new ResponseEntity<>("Incorrect data!", HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            clientService.delete(clientService.findClientById(id).orElseThrow());
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Incorrect ID!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Client not deleted!\nCheck your input on ID!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Client deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                          @RequestBody Client client) {
        try {
            Optional<Client> currentClient = clientService.findClientById(id);

            if (currentClient.isPresent()) {
                currentClient.get().setPersonalInfo(PersonalInfo.personalInfoExtractor(
                        client.getPersonalInfo(),
                        currentClient.get().getPersonalInfo()));

                return new ResponseEntity<>(clientService.update(currentClient.get()), HttpStatus.OK);
            }

            throw new EntityNotFoundException();
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Such entity not exists!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect Data!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-name")
    public ResponseEntity<?> findAllByFirstAndLastNames(@RequestParam(name = "firstName", defaultValue = "") String firstName,
                                                            @RequestParam(name = "lastName", defaultValue = "") String lastName) {
        try {
            if (!firstName.isEmpty() && !lastName.isEmpty()) {
                return new ResponseEntity<>(clientService.findClientsByFirstNameAndLastName(firstName, lastName).orElseThrow(), HttpStatus.OK);
            }

            throw new RuntimeException("Both searching params is empty!");
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Enter correct searching params!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-phone")
    public ResponseEntity<?> findAllByContactPhone(@RequestParam(name = "contactPhone", defaultValue = "") String contactPhone) {
        try {
            if (!contactPhone.isEmpty()) {
                return new ResponseEntity<>(clientService.findClientsByContactPhone(contactPhone).orElseThrow(), HttpStatus.OK);
            }

            throw new RuntimeException("Searching param is empty!");
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Enter correct searching params!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-apartment")
    public ResponseEntity<?> findAllByRequestForRenting(@RequestParam(name = "apartmentId", defaultValue = "0") Long apartmentId) {
        try {
            if (apartmentId != 0) {
                return new ResponseEntity<>(clientService.findClientsByApartmentId(apartmentId).orElseThrow(), HttpStatus.OK);
            }

            throw new NoSuchElementException();
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Enter correct apartment ID!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-rented-during-month")
    public ResponseEntity<?> findAllThatRentedApartmentDuringMonth() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateBeginDuringMonth().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients that rented apartments during this month!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/clients/searching/by-rent-expiring-during-month")
    public ResponseEntity<?> findAllWithRentExpiredDuringMonth() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateEndDuringMonth().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients with expiring rent during last month!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-rent-duration-less-month")
    public ResponseEntity<?> findAllWithRentDurationLessThanMonth() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateRangeLessThanMonth().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients that rented apartments for less than month!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/clients/searching/by-rent-duration-more-year")
    public ResponseEntity<?> findAllWithRentDurationMoreThanYear() {
        try {
            return new ResponseEntity<>(clientService.findClientsByRentingDateMoreThanYear().orElseThrow(), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("No clients that rented apartments for more than year!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
