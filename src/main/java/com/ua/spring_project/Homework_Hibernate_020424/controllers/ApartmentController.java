package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.dto.ApartmentDTO;
import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import com.ua.spring_project.Homework_Hibernate_020424.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class ApartmentController {
    final private ApartmentService apartmentService;

    @RequestMapping(value = "/apartments", method = RequestMethod.GET)
    public ResponseEntity<List<Apartment>> getApartments(ApartmentDTO apartmentDTO) {
        List<Apartment> apartments;
        ApartmentDTO emptyDto = new ApartmentDTO();

        apartments = (apartmentDTO.equals(emptyDto) ?
                apartmentService.findAll(PageRequest.of(apartmentDTO.getPage(), apartmentDTO.getPageSize())).toList() :
                apartmentService.findByApartmentFilters(apartmentDTO).toList());

        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }
}
