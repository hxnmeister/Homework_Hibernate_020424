package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.models.RentingHistory;
import com.ua.spring_project.Homework_Hibernate_020424.services.RentingHistoryService;
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
public class RentingHistoryController {
    private final RentingHistoryService rentingHistoryService;

    @RequestMapping(value = "/renting-histories")
    public ResponseEntity<List<RentingHistory>> getRentingHistories(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return new ResponseEntity<>(rentingHistoryService.findAll(PageRequest.of(page, pageSize)).toList(), HttpStatus.OK);
    }
}
