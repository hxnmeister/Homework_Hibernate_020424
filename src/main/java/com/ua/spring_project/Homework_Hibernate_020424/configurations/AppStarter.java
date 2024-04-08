package com.ua.spring_project.Homework_Hibernate_020424.configurations;

import com.ua.spring_project.Homework_Hibernate_020424.services.MenuExecutorService;
import com.ua.spring_project.Homework_Hibernate_020424.utils.RentInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class AppStarter {
    private final RentInitializer rentInitializer;

    @Bean
    public ApplicationRunner init() {
        log.debug("Application is running!");

        return args -> {
            rentInitializer.initDB();
        };
    }
}
