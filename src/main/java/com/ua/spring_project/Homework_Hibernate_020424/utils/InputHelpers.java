package com.ua.spring_project.Homework_Hibernate_020424.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Log4j2
@Component
public class InputHelpers {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String getStringInputByLength(int maxLength, String inputMessage) {
        while (true) {
            try {
                System.out.print(inputMessage);
                final String inputString = SCANNER.nextLine();

                if (inputString.length() > maxLength) {
                    throw new InputMismatchException();
                }

                return inputString;
            }
            catch (InputMismatchException e) {
                log.warn(" Incorrect line length!\n Must be max " + maxLength + " chars!\n");
            }
            catch (RuntimeException e) {
                log.warn(e.getMessage());
            }
        }
    }

    public long getCorrectIdInput(List<Long> longList, String inputMessage) {
        while (true) {
            try {
                System.out.print(inputMessage);
                final long id = Long.parseLong(SCANNER.nextLine());

                if (!longList.contains(id)) {
                    throw new InputMismatchException();
                }

                return id;
            }
            catch (InputMismatchException e) {
                log.info(" You entered incorrect number!\n Allowed ID`s: " + longList.stream().map((item) -> item + " "));
            }
            catch (RuntimeException e) {
                log.warn(e.getMessage());
            }
        }
    }
}
