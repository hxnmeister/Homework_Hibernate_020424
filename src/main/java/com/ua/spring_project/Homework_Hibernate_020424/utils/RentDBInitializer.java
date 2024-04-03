package com.ua.spring_project.Homework_Hibernate_020424.utils;

import com.ua.spring_project.Homework_Hibernate_020424.services.RentDBInitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class RentDBInitializer {
    @Value("${data.first_names}")
    private String pathToFirstNames;

    @Value("${data.last_names}")
    private String pathToLastNames;

    private final TxtFileReader txtFileReader;
    private final RentDBInitService rentDBInitService;
    private final Random RANDOM = new Random();
}
