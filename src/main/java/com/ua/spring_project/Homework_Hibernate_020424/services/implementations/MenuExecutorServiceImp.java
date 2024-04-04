package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.models.Apartment;
import com.ua.spring_project.Homework_Hibernate_020424.models.Client;
import com.ua.spring_project.Homework_Hibernate_020424.models.PersonalInfo;
import com.ua.spring_project.Homework_Hibernate_020424.services.*;
import com.ua.spring_project.Homework_Hibernate_020424.utils.InputHelpers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MenuExecutorServiceImp implements MenuExecutorService {
    private final InputHelpers inputHelpers;
    private final ClientService clientService;
    private final LandlordService landlordService;
    private final ApartmentService apartmentService;
    private final MenuPublisherService menuPublisherService;
    private final RentingHistoryService rentingHistoryService;
    private final RequestForRentingApartmentService requestForRentingApartmentService;

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
                    case 5:
                        addingClientToDB();
                        break;
                    case 6:
                        deletingClientFromDB();
                        break;
                    case 7:
                        editingClientData();
                        break;
                    case 8:
                        findClientsByFirstAndLastNames().forEach((item) -> {
                            System.out.println();
                            System.out.println(item);
                        });
                        break;
                    case 9:
                        findClientsByContactPhone().forEach((item) -> {
                            System.out.println();
                            System.out.println(item);
                        });
                        break;
                    case 10:
                        findClientsByRequestForRentingApartment().forEach((item) -> {
                            System.out.println();
                            System.out.println(item);
                        });
                        break;
                    case 11:
                        System.out.println(" Clients that rented apartments during this month:\n");
                        clientService.findClientsByRentingDateDuringMonth().forEach(System.out::println);
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

    public Client addingClientToDB() {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setFirstName(inputHelpers.getStringInputByLength(20, "  Enter first name: "));
        personalInfo.setLastName(inputHelpers.getStringInputByLength(30, "  Enter last name: "));
        personalInfo.setContactPhone(inputHelpers.getStringInputByLength(13, "  Enter contact phone: "));

        return clientService.save(Client.builder().personalInfo(personalInfo).build());
    }

    public Client deletingClientFromDB() {
        System.out.println(" From list below choose client ID to delete:\n");
        clientService.findAll().forEach(System.out::println);

        final long id = inputHelpers.getCorrectIdInput(clientService.findAll().stream().map(Client::getId).collect(Collectors.toList()),
                " Enter ID to delete client: ");
        Client client = clientService.findClientById(id);

        clientService.delete(client);
        return client;
    }

    public Client editingClientData() {
        Client client;
        PersonalInfo personalInfo = new PersonalInfo();

        System.out.println(" From list below choose client ID to edit:\n");
        clientService.findAll().forEach(System.out::println);

        final long id = inputHelpers.getCorrectIdInput(clientService.findAll().stream().map(Client::getId).collect(Collectors.toList()),
                " Enter ID to edit client: ");

        client = clientService.findClientById(id);

        personalInfo.setFirstName(inputHelpers.getStringInputByLength(20, " Enter new first name (" + client.getPersonalInfo().getFirstName() + "): "));
        personalInfo.setLastName(inputHelpers.getStringInputByLength(30, " Enter new last name (" + client.getPersonalInfo().getLastName() + "): "));
        personalInfo.setContactPhone(inputHelpers.getStringInputByLength(13, " Enter new contact phone (" + client.getPersonalInfo().getContactPhone() + "): "));
        client.setPersonalInfo(personalInfo);

        return clientService.update(client);
    }

    public List<Client> findClientsByFirstAndLastNames() {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setFirstName(inputHelpers.getStringInputByLength(20, " Enter first name: "));
        personalInfo.setLastName(inputHelpers.getStringInputByLength(30, " Enter last name: "));

        return clientService.findClientsByFirstNameAndLastName(personalInfo.getFirstName(), personalInfo.getLastName());
    }

    public List<Client> findClientsByContactPhone() {
        String contactPhone = inputHelpers.getStringInputByLength(13, " Enter contact phone: ");

        return clientService.findClientsByContactPhone(contactPhone);
    }

    public List<Client> findClientsByRequestForRentingApartment() {
        long apartmentId;
        List<Apartment> apartments = apartmentService.findAll();

        System.out.println(" Choose apartment ID to search client:\n");
        apartmentService.findAll().forEach(System.out::println);

        apartmentId = inputHelpers.getCorrectIdInput(apartments.stream().map(Apartment::getId).collect(Collectors.toList()),
                " Enter ID: ");

        if (requestForRentingApartmentService.existsRequestForRentingApartmentByApartmentId(apartmentId)) {
            return clientService.findClientsByApartmentId(apartmentId);
        }

        return new ArrayList<>();
    }
}
