package com.davydov;

import com.davydov.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DavydovApplication implements CommandLineRunner {

    @Autowired
    CardService cardService;

    public static void main(String[] args) {
        SpringApplication.run(DavydovApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                cardService.doCommand(sc.nextLine());
            }
        }).start();
    }
}
