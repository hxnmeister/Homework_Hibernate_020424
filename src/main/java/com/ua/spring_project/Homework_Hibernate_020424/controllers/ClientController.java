package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import com.ua.spring_project.Homework_Hibernate_020424.models.PersonalInfo;
import com.ua.spring_project.Homework_Hibernate_020424.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getClients(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Client> clients = clientService.findAll(PageRequest.of(page, pageSize)).toList();

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity<?> insertClient(@RequestBody Client client) {

        return clientService.save(client) == null ?
                new ResponseEntity<>("Incorrect data!", HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClient(@PathVariable(name = "id") Long id) {
        try {
            clientService.delete(clientService.findClientById(id));
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Client not deleted!\nCheck your input on ID!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Client deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@PathVariable(name = "id") Long id,
                                          @RequestBody Client client) {
        try {
            Client currentCLient = clientService.findClientById(id);

            currentCLient.setPersonalInfo(extractPersonalInfo(client, currentCLient));
            return new ResponseEntity<>(clientService.update(currentCLient), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect Data!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/searching/by-name", method = RequestMethod.GET)
    public ResponseEntity<?> findClientsByFirstAndLastNames(@RequestParam(name = "firstName", defaultValue = "") String firstName,
                                                            @RequestParam(name = "lastName", defaultValue = "") String lastName) {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            return new ResponseEntity<>(clientService.findClientsByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
        }

        return new ResponseEntity<>("Enter correct searching params!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/clients/searching/by-phone", method = RequestMethod.GET)
    public ResponseEntity<?> findClientsByContactPhone(@RequestParam(name = "contactPhone", defaultValue = "") String contactPhone) {
        if (!contactPhone.isEmpty()) {
            return new ResponseEntity<>(clientService.findClientsByContactPhone(contactPhone), HttpStatus.OK);
        }

        return new ResponseEntity<>("Enter correct searching params!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/clients/searching/by-apartment", method = RequestMethod.GET)
    public ResponseEntity<?> findClientsByRequestForRenting(@RequestParam(name = "apartmentId", defaultValue = "0") Long apartmentId) {
        try {
            if (apartmentId != 0) {
                return new ResponseEntity<>(clientService.findClientsByApartmentId(apartmentId), HttpStatus.OK);
            }

            return new ResponseEntity<>("Incorrect apartment ID!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Something went wrong during executing searching by request for renting!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/searching/by-rented-during-month")
    public ResponseEntity<List<Client>> findClientsThatRentedApartmentDuringMonth() {
        return new ResponseEntity<>(clientService.findClientsByRentingDateBeginDuringMonth(), HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/searching/by-rent-expiring-during-month")
    public ResponseEntity<List<Client>> findClientsWithRentExpiredDuringMonth() {
        return new ResponseEntity<>(clientService.findClientsByRentingDateEndDuringMonth(), HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/searching/by-rent-duration-less-month")
    public ResponseEntity<List<Client>> findClientsWithRentDurationLessThanMonth() {
        return new ResponseEntity<>(clientService.findClientsByRentingDateRangeLessThanMonth(), HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/searching/by-rent-duration-more-year")
    public ResponseEntity<List<Client>> findClientsWithRentDurationMoreThanYear() {
        return new ResponseEntity<>(clientService.findClientsByRentingDateMoreThanYear(), HttpStatus.OK);
    }

    private static PersonalInfo extractPersonalInfo(Client client, Client currentCLient) {
        PersonalInfo personalInfo = currentCLient.getPersonalInfo();

        if (!client.getPersonalInfo().getFirstName().isEmpty()) {
            personalInfo.setFirstName(client.getPersonalInfo().getFirstName());
        }
        if (!client.getPersonalInfo().getLastName().isEmpty()) {
            personalInfo.setLastName(client.getPersonalInfo().getLastName());
        }
        if (!client.getPersonalInfo().getContactPhone().isEmpty()) {
            personalInfo.setContactPhone(client.getPersonalInfo().getContactPhone());
        }

        return personalInfo;
    }
}
