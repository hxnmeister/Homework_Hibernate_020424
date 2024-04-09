package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.dto.ApartmentDTO;
import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import com.ua.spring_project.Homework_Hibernate_020424.models.PersonalInfo;
import com.ua.spring_project.Homework_Hibernate_020424.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ApartmentController {
    final private ApartmentService apartmentService;

    @GetMapping(value = "/apartments")
    public ResponseEntity<List<Apartment>> getAll(ApartmentDTO apartmentDTO) {
        List<Apartment> apartments;
        ApartmentDTO emptyDto = new ApartmentDTO();

        apartments = (apartmentDTO.equals(emptyDto) ?
                apartmentService.findAll(PageRequest.of(apartmentDTO.getPage(), apartmentDTO.getPageSize())).toList() :
                apartmentService.findByApartmentFilters(apartmentDTO).toList());

        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }

    @DeleteMapping(value = "/apartment/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            apartmentService.delete(apartmentService.findApartmentById(id).orElseThrow());
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Incorrect ID!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Apartment not deleted!\nCheck your input on ID!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Apartment deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/apartment/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody Apartment apartment) {
        try {
            Apartment currentApartment = apartmentService.findApartmentById(id).orElseThrow();

            return new ResponseEntity<>(apartmentService.update(extractApartmentData(apartment, currentApartment)), HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Incorrect ID!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect Data!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/apartment/searching/avg-price")
    public ResponseEntity<?> findAvgPrice() {
        try {
            return new ResponseEntity<>(apartmentService.getAvgPrice(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/apartment/searching/max-price")
    public ResponseEntity<?> findMaxPrice() {
        try {
            return new ResponseEntity<>(apartmentService.getMaxPrice(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/apartment/searching/min-price")
    public ResponseEntity<?> findMinPrice() {
        try {
            return new ResponseEntity<>(apartmentService.getMinPrice(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private static Apartment extractApartmentData(Apartment current, Apartment previous) {
        if (current.getRoomsCount() > 0) {
            previous.setRoomsCount(current.getRoomsCount());
        }
        if (current.getDistrict() != null && !current.getDistrict().isEmpty()) {
            previous.setDistrict(current.getDistrict());
        }
        if (current.getPrice() != null && current.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            previous.setPrice(current.getPrice());
        }

        return previous;
    }
}
