package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import com.ua.spring_project.Homework_Hibernate_020424.models.RentingHistory;
import com.ua.spring_project.Homework_Hibernate_020424.services.RentingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class RentingHistoryController {
    private final RentingHistoryService rentingHistoryService;

    @GetMapping(value = "/renting-histories")
    public ResponseEntity<List<RentingHistory>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return new ResponseEntity<>(rentingHistoryService.findAll(PageRequest.of(page, pageSize)).toList(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/renting-history/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            rentingHistoryService.delete(rentingHistoryService.findRentingHistoryById(id).orElseThrow());
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>("Incorrect ID!", HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Apartment not deleted!\nCheck your input on ID!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Apartment deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/renting-history/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody RentingHistory rentingHistory) {
        try {
            RentingHistory currentRentingHistory = rentingHistoryService.findRentingHistoryById(id).orElseThrow();

            return new ResponseEntity<>(rentingHistoryService.update(extractRentingHistoryData(rentingHistory, currentRentingHistory)), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect Data!", HttpStatus.BAD_REQUEST);
        }
    }

    private static RentingHistory extractRentingHistoryData(RentingHistory current, RentingHistory previous) {
        if (current.getRentBegin() != null && current.getRentBegin().getTime() >= previous.getRentBegin().getTime()) {
            previous.setRentBegin(current.getRentBegin());
        }
        if (current.getRentEnd() != null && current.getRentEnd().getTime() >= previous.getRentEnd().getTime()) {
            previous.setRentEnd(current.getRentEnd());
        }

        return previous;
    }
}
