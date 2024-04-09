package com.ua.spring_project.Homework_Hibernate_020424.controllers;

import com.ua.spring_project.Homework_Hibernate_020424.models.Landlord;
import com.ua.spring_project.Homework_Hibernate_020424.models.PersonalInfo;
import com.ua.spring_project.Homework_Hibernate_020424.services.LandlordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class LandlordController {
    private final LandlordService landlordService;

    @GetMapping(value = "/landlords")
    public ResponseEntity<List<Landlord>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return new ResponseEntity<>(landlordService.findAll(PageRequest.of(page, pageSize)).toList(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/landlord/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        try {
            landlordService.delete(landlordService.findLandlordById(id));
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Landlord not deleted!\nCheck your input on ID!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Landlord deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/landlord/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                            @RequestBody Landlord landlord) {
        try {
            Landlord currentLandlord = landlordService.findLandlordById(id);

            currentLandlord.setPersonalInfo(PersonalInfo.personalInfoExtractor(
                    landlord.getPersonalInfo(),
                    currentLandlord.getPersonalInfo()));

            return new ResponseEntity<>(landlordService.update(currentLandlord), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>("Incorrect Data!", HttpStatus.BAD_REQUEST);
        }
    }
}
