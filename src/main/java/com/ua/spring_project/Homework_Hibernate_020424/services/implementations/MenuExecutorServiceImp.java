package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
@Log4j2
@RequiredArgsConstructor
public class MenuExecutorServiceImp implements MenuExecutorService {
    private final ClientService clientService;
    private final LandlordService landlordService;
    private final ApartmentService apartmentService;
    private final MenuPublisherService menuPublisherService;
    private final RentingHistoryService rentingHistoryService;

    @Override
    public void executeMenu() {
        final Scanner SCANNER = new Scanner(System.in);

        while (true) {
            try {
                System.out.println(menuPublisherService.getMenu());
                System.out.print("\n Enter choice here: ");
                final int CHOICE = Integer.parseInt(SCANNER.nextLine());

                switch (CHOICE) {
                    case 1:
                        clientService.findAll().forEach(System.out::println);
                        break;
                    case 2:
                        landlordService.findAll().forEach(System.out::println);
                        break;
                    case 3:
                        apartmentService.findAll().forEach(System.out::println);
                        break;
                    case 4:
                        rentingHistoryService.findAll().forEach(System.out::println);
                        break;
                    case 0:
                        log.info(" Shutting down program...");
                        System.exit(0);
                    default:
                        throw new InputMismatchException();
                }
            }
            catch (InputMismatchException e) {
                log.warn(" Incorrect number input!");
            }
            catch (RuntimeException e) {
                log.warn(e.getMessage());
            }
        }
    }
}
