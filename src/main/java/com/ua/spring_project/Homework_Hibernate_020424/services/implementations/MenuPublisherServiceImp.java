package com.ua.spring_project.Homework_Hibernate_020424.services.implementations;

import com.ua.spring_project.Homework_Hibernate_020424.services.MenuPublisherService;
import org.springframework.stereotype.Service;

@Service
public class MenuPublisherServiceImp implements MenuPublisherService {
    @Override
    public String getMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("  1. Display clients;\n");
        menu.append("  2. Display landlords;\n");
        menu.append("  3. Display apartments;\n");
        menu.append("  4. Display renting history;\n");
        menu.append("  5. Add client;\n");
        menu.append("  6. Delete client;\n");
        menu.append("  7. Edit client;\n");
        menu.append("  0. Exit program;");

        return menu.toString();
    }
}
