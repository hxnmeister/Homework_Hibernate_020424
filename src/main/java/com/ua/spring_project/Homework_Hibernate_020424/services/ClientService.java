package com.ua.spring_project.Homework_Hibernate_020424.services;

import com.ua.spring_project.Homework_Hibernate_020424.models.Client;

public interface ClientService extends CRUDInterface<Client> {
    Client findClientById(long id);
}
