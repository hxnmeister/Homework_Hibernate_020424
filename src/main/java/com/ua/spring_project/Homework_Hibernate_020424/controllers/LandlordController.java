package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.models.Landlord;
import com.ua.spring_project.Homework_Hibernate_020424.services.LandlordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class LandlordController {
    private final LandlordService landlordService;

    @RequestMapping(value = "/landlords")
    public ResponseEntity<List<Landlord>> getLandlords(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return new ResponseEntity<>(landlordService.findAll(PageRequest.of(page, pageSize)).toList(), HttpStatus.OK);
    }
}
