package com.ua.spring_project.Homework_Hibernate_020424.services;

import java.util.List;

public interface CRUDInterface<E> {
    E save(E item);
    E update(E item);
    void delete(E item);
    void deleteAll();
    List<E> findAll();
    List<E> saveMany(List<E> itemsList);
}
